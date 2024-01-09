package com.jie.spring.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.jie.spring.beans.factory.config.BeanDefinition;
import com.jie.spring.beans.factory.support.BeanDefinitionRegistry;
import com.jie.spring.context.stereotype.Component;

import java.util.Set;

/**
 * 具体扫描包处理的类
 *
 * @author jie
 * @date 2024/1/8 22:31
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningComponentProvider {

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            // 获取到扫描的类信息
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                // 解析 Bean 的作用域 singleton、prototype
                String beanScope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotEmpty(beanScope)) {
                    beanDefinition.setScope(beanScope);
                }
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        // 读取类上的注解
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (null != scope) {
            // 获取注解中的值
            return scope.value();
        }
        return StrUtil.EMPTY;
    }

    /**
     * 取决于用户有没有在 @Component 注解中自定义类名
     *
     * @param beanDefinition
     * @return
     */
    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }
}
