package com.jie.spring.test;

import java.util.logging.Logger;

/**
 * @author jie
 * @date 2023/11/23 21:44
 */
public class User {
    private final Logger log = Logger.getLogger(String.valueOf(User.class));

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserInfo() {
        log.info("user info: " + name);
        return name;
    }
}
