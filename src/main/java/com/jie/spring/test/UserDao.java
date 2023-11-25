package com.jie.spring.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jie
 * @date 2023/11/25 23:13
 */
public class UserDao {
    private static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("jie", 18);
        map.put("jack", 22);
        map.put("marry", 33);
    }

    public Integer getUserAge(String userName) {
        return map.get(userName);
    }
}
