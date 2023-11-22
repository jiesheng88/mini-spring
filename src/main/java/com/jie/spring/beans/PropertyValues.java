package com.jie.spring.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        for (int i = 0; i < propertyValueList.size(); i++) {
            PropertyValue curPv = propertyValueList.get(i);
            if (curPv.getName().equals(pv.getName())) {
                // 覆盖原有的属性值
                propertyValueList.set(i, pv);
                return;
            }
        }
        // 没有重复的，添加
        propertyValueList.add(pv);
    }

    // TODO：用数组存储是否比列表更好？
    public List<PropertyValue> getPropertyValues() {
        return propertyValueList;
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (int i = 0; i < propertyValueList.size(); i++) {
            PropertyValue pv = propertyValueList.get(i);
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }
}
