package com.jie.spring.context;

import com.jie.spring.beans.factory.HierarchicalBeanFactory;
import com.jie.spring.beans.factory.ListableBeanFactory;
import com.jie.spring.core.io.ResourceLoader;

/**
 * 定义上下文接口
 * 继承ListableBeanFactory。拥有了获取Bean的能力
 * 该接口可以满足于自动识别、资源加载、容器事件、监听器等功能
 *
 * @author jie
 * @date 2023/11/27 22:50
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {

}
