package com.jie.spring.context.event;

import com.jie.spring.context.ApplicationEvent;
import com.jie.spring.context.ApplicationListener;

/**
 * 事件广播器
 *
 * @author jie
 * @date 2023/12/19 22:42
 */
public interface ApplicationEventMulticaster {
    /**
     * Add a listener to be notified of all events.
     *
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     *
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners.
     *
     * @param event the event to multicast
     */
    void multicasterEvent(ApplicationEvent event);
}
