package com.jie.spring.test;

import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.factory.BeanClassLoaderAware;
import com.jie.spring.beans.factory.BeanFactory;
import com.jie.spring.beans.factory.BeanFactoryAware;
import com.jie.spring.beans.factory.BeanNameAware;
import com.jie.spring.beans.factory.DisposableBean;
import com.jie.spring.beans.factory.InitializingBean;
import com.jie.spring.context.ApplicationContext;
import com.jie.spring.context.ApplicationContextAware;

import java.util.logging.Logger;

/**
 * @author jie
 * @date 2023/11/23 21:44
 */
public class UserService implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {
    private final Logger log = Logger.getLogger(String.valueOf(UserService.class));

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    private String name;
    private UserDao userDao;
    // 新增两个属性，是为了测试 BeanPostProcessor、BeanFactoryPostProcessor 两个接口对 Bean 属性信息扩展的作用。
    private String company;
    private String location;

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

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

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader: " + classLoader);
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("BeanName: " + beanName);
    }
}
