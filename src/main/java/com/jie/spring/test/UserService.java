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
    // 新增两个属性，是为了测试 BeanPostProcessor、BeanFactoryPostProcessor 两个接口对 Bean 属性信息扩展的作用。
    private String company;
    private String location;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserInfo() {
        Integer age = userDao.getUserAge(name);
        String userInfo = name + " 所在公司： " + company + ", 地点在：" + location + ", 年龄为：" + age;
        log.info("user info: " + userInfo);
        return userInfo;
    }
}
