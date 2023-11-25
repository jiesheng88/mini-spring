package com.jie.spring.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个类中需要保存的属性会有好几个
 *
 * @author jie
 * @date 2023/11/25 22:44
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : this.propertyValueList) {
            if (propertyValue.getName().equals(propertyValue)) {
                return propertyValue;
            }
        }
        return null;
    }
}
