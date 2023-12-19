package com.jie.spring.context;

import com.jie.spring.context.support.ClassPathXmlApplicationContext;
import com.jie.spring.test.event.CustomEvent;
import org.junit.Test;

/**
 * 自定义监听器，进行业务处理
 *
 * @author jie
 * @date 2023/12/19 23:43
 */
public class ApplicationEventTest {

    @Test
    public void testEvent() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_event.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 123L, "成功了！"));

        applicationContext.registerShutdownHook();
    }
}
