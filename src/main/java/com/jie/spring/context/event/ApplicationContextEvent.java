package com.jie.spring.context.event;

import com.jie.spring.context.ApplicationContext;
import com.jie.spring.context.ApplicationEvent;

/**
 * Base class for events raised for an <code>ApplicationContext</code>.
 *
 * @author jie
 * @date 2023/12/19 22:36
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * Get the <code>ApplicationContext</code> that the event was raised for.
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
