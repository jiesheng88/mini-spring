package com.jie.spring.test.event;

import com.jie.spring.context.event.ApplicationContextEvent;

/**
 * 创建自定义事件
 *
 * @author jie
 * @date 2023/12/19 23:44
 */
public class CustomEvent extends ApplicationContextEvent {
    private Long id;
    private String message;

    /**
     * 在构造函数中添加想要的入参，这个事件最终会被完整的拿到监听器中，所以添加的属性都会被获取到。
     *
     * @param source
     * @param id
     * @param message
     */
    public CustomEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
