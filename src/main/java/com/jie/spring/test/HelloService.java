package com.jie.spring.test;

import java.util.logging.Logger;

public class HelloService {


    private final Logger log = Logger.getLogger(HelloService.class.toString());

    public String sayHello() {
        log.info("hello");
        return "hello";
    }


}
