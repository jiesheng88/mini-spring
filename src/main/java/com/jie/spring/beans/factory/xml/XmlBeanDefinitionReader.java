package com.jie.spring.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.PropertyValue;
import com.jie.spring.beans.factory.config.BeanDefinition;
import com.jie.spring.beans.factory.config.BeanReference;
import com.jie.spring.beans.factory.support.AbstractBeanDefinitionReader;
import com.jie.spring.beans.factory.support.BeanDefinitionRegistry;
import com.jie.spring.context.annotation.ClassPathBeanDefinitionScanner;
import com.jie.spring.core.io.Resource;
import com.jie.spring.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 解析XML，处理Bean注册
 * 外部至少需要传入 BeanDefinitionRegistry，ResourceLoader默认是DefaultResourceLoader
 * 【增加】解析 XML 中调用扫描
 * 【替换】XML 解析全部替换成 dom4j 的方式进行解析处理，为了方便加载和解析 XML
 *
 * @author jie
 * @date 2023/11/26 17:45
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException | DocumentException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String[] locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        Resource resource = getResourceLoader().getResource(location);
        loadBeanDefinitions(resource);
    }

    /**
     * 解析XML，并自动注册完整的Bean定义内容到Bean容器中
     * 增加对 init-method、destroy-method 的读取
     *
     * @param inputStream
     * @throws ClassNotFoundException
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();

        // 解析 context:component-scan 标签，扫描包中的类并提取相关信息，用于组装 BeanDefinition
        Element componentScan = root.element("component-scan");
        if (null != componentScan) {
            String scanPath = componentScan.attributeValue("base-package");
            if (StrUtil.isEmpty(scanPath)) {
                throw new BeansException("The value of base-package attribute can not be empty or null");
            }
            scanPackage(scanPath);
        }


        List<Element> beanLit = root.elements("bean");
        for (Element bean : beanLit) {

            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");

            // 增加对 init-method、destroy-method 的读取
            String initMethod = bean.attributeValue("init-method");
            String destroyMethod = bean.attributeValue("destroy-method");

            // 增加对 Bean 作用域的读取
            String beanScope = bean.attributeValue("scope");

            // 获取Class，方便获取类中的名称
            Class<?> clazz = Class.forName(className);
            // 优先级 id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);

            // 设置 init-method、destroy-method 到 BeanDefinition 中
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethod);

            if (StrUtil.isNotEmpty(beanScope)) {
                beanDefinition.setScope(beanScope);
            }

            // 读取属性，并填充
            List<Element> propertyList = bean.elements("property");
            for (Element property : propertyList) {

                String attrName = property.attributeValue("name");
                String attrValue = property.attributeValue("value");
                String attrRef = property.attributeValue("ref");
                // 获取属性值：引入对象、值对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }
}
