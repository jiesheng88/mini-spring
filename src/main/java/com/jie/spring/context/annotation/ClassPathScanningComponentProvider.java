package com.jie.spring.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.jie.spring.beans.factory.config.BeanDefinition;
import com.jie.spring.context.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 处理对象扫描装配
 * 可以通过配置路径 basePackage=，解析出 classes 信息的工具方法，通过该方法就可以扫描到所有 @Component 注解的 Bean 对象
 *
 * @author jie
 * @date 2024/1/8 22:24
 */
public class ClassPathScanningComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        // 通过 hubtool 获取
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
