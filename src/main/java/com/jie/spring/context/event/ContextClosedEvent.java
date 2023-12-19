package com.jie.spring.context.event;

import com.jie.spring.context.ApplicationEvent;

/**
 * @author jie
 * @date 2023/12/19 22:40
 */
public class ContextClosedEvent extends ApplicationEvent {

    public ContextClosedEvent(Object source) {
        super(source);
    }
}
