package com.cbcc.bizboot.util;

public class BeanUtils extends org.springframework.beans.BeanUtils {

    public static <T> T newAndCopy(Object source, Class<T> targetClass) {
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("Error creating target bean and copying properties", e);
        }
    }
}
