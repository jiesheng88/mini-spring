package com.jie.spring.test.aop;

import java.util.Random;

/**
 * @author jie
 * @date 2023/12/25 22:27
 */
public class AopProxyUserService implements AopProxyIUserService {
    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "jie, 100001, 南京";
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return userName + " 注册成功！";
    }
}
