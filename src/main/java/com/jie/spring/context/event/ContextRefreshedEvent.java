package com.jie.spring.context.event;

import com.jie.spring.context.ApplicationEvent;

/**
 * @author jie
 * @date 2023/12/19 22:41
 */
public class ContextRefreshedEvent extends ApplicationEvent {

    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
