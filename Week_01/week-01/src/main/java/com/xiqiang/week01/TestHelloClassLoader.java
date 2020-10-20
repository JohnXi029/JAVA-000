package com.xiqiang.week01;

import java.lang.reflect.Method;

/**
 * 测试自定义的 {@link HelloClassLoader}
 *
 * @author John Xi
 * @date 2020/10/21 0:01
 */
public class TestHelloClassLoader {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = new HelloClassLoader().loadClass("Hello");
        // 找到hello方法
        Method helloMethod = clazz.getMethod("hello");
        // 调用hello方法
        helloMethod.invoke(clazz.newInstance());
    }
}
