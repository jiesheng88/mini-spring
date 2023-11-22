package com.jie.spring.test;

/**
 * @author jie
 * @date 2023/11/23 21:44
 */
public class User {
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
}
