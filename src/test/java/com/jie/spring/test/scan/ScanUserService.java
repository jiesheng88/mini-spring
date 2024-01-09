package com.jie.spring.test.scan;

import com.jie.spring.context.stereotype.Component;
import com.jie.spring.test.aop.AopProxyIUserService;

import java.util.Random;

/**
 * @author jie
 * @date 2024/1/8 23:09
 */
@Component("userService")
public class ScanUserService implements AopProxyIUserService {

    private String token;

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

    @Override
    public String toString() {
        return "ScanUserService#token={" + token + '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
