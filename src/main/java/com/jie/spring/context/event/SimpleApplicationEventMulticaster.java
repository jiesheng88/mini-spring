package com.jie.spring.context.event;

import com.jie.spring.beans.factory.BeanFactory;
import com.jie.spring.context.ApplicationEvent;
import com.jie.spring.context.ApplicationListener;

/**
 * @author jie
 * @date 2023/12/19 23:15
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unckecked")
    @Override
    public void multicasterEvent(final ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)) {
            // 监听者处理事件
            listener.onApplicationEvent(event);
        }
    }
}
