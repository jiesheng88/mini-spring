package com.jie.spring.test.event;

import com.jie.spring.context.ApplicationListener;

import java.util.Date;

/**
 * @author jie
 * @date 2023/12/19 23:47
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息；时间：" + new Date());
        System.out.println("消息内容：" + event.getId() + ", " + event.getMessage());
    }
}
