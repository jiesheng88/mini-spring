package com.jie.spring.context;

/**
 * Interface that encapsulates event publication functionality.
 * Serves as super-interface for ApplicationContext.
 * <p>
 * 事件发布接口，所有的事件都需要从该接口发布出去。
 *
 * @author jie
 * @date 2023/12/19 23:18
 */
public interface ApplicationEventPublisher {
    /**
     * Notify all listeners registered with this application of an application event.
     * Events may be framework events (such as RequestHandledEvent) or application-specific events.
     *
     * @param event the event to publish
     */
    void publishEvent(ApplicationEvent event);
}
