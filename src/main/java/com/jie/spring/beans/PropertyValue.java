package com.jie.spring.beans;

/**
 * bean 属性信息
 * 是 bean 属性键值对的封装，缓存了对 key-value 解析相关的信息，避免重复解析。
 */
public class PropertyValue {

    private final String name;

    private final Object value;


    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }


}
