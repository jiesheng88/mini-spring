package com.jie.spring.test;

import java.util.logging.Logger;

/**
 * @author jie
 * @date 2023/11/23 21:44
 */
public class UserService {
    private final Logger log = Logger.getLogger(String.valueOf(UserService.class));

    private String name;

    private UserDao userDao;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getUserInfo() {
        Integer age = userDao.getUserAge(name);
        String userInfo = name + " " + age;
        log.info("user info: " + userInfo);
        return userInfo;
    }
}
