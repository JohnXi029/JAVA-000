package com.xiqiang.week01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 自定义 {@link ClassLoader}
 * @author John Xi
 * @date 2020/10/20 22:58
 */
public class HelloClassLoader extends ClassLoader {

    private static final String POSTFIX_XLASS = ".xlass";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = this.getClass().getClassLoader().getResource(name + POSTFIX_XLASS).getPath();
        byte[] bytes = xlass2Bytes(path);
        return super.defineClass(name, bytes, 0, bytes.length);
    }

    /**
     * 读取 .xlass， 并按照约定的形式做转换 255-每个字节
     * @param path .xlass文件的路径
     * @return 转换后的字节数组
     */
    private byte[] xlass2Bytes(String path) {

        byte[] resultBytes = null;

        try (FileInputStream fis = new FileInputStream(path);
             ByteArrayOutputStream bos = new ByteArrayOutputStream(1024)) {
            byte[] b = new byte[1];
            byte[] restoreBytes = new byte[1];
            int n;
            while ((n = fis.read(b)) != -1) {
                restoreBytes[0] = (byte)((byte)255 - b[0]);
                bos.write(restoreBytes, 0, n);
            }
            resultBytes = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultBytes;
    }
}
