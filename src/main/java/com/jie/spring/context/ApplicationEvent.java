package com.jie.spring.context;

import java.util.EventObject;

/**
 * Class to be extended by all application events. Abstract as it
 * doesn't make sense for generic events to be published directly.
 * <p>
 * 以继承 EventObject 定义出具体事件功能的抽象类，后续所有事件的类都要继承该类。
 *
 * @author jie
 * @date 2023/12/19 22:31
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
