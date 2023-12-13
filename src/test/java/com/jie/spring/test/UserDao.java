package com.jie.spring.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jie
 * @date 2023/11/25 23:13
 */
public class UserDao {
    private static Map<String, Integer> map = new HashMap<>();

    public void initDataMethod() {
        System.out.println("执行：UserDao 的 init-method");
        map.put("jie", 18);
        map.put("jack", 22);
        map.put("marry", 33);
    }

    public void destroyDataMethod() {
        System.out.println("执行：UserDao 的 destroy-method");
        map.clear();
    }


    public Integer getUserAge(String userName) {
        return map.get(userName);
    }
}
