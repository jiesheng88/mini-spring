package com.jie.spring.context;

import java.util.EventListener;

/**
 * Interface to be implemented by application event listeners.
 * Based on the standard <code>java.util.EventListener</code> interface
 * for the Observer design pattern.
 *
 * @author jie
 * @date 2023/12/19 22:46
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);
}
