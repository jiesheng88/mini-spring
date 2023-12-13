package com.jie.spring.test;

import java.util.logging.Logger;

public class HelloService {
    private final Logger log = Logger.getLogger(HelloService.class.toString());

    private String msg;

    private Integer num;

    public HelloService() {
    }

    public HelloService(String msg) {
        this.msg = msg;
    }

    public HelloService(String msg, Integer num) {
        this.msg = msg;
        this.num = num;
    }

    public String getMsg() {
        return msg;
    }

    public String getHelloService() {
        return msg + ", " + num;
    }


    public String sayHello() {
        log.info("hello");
        return "hello";
    }


}
