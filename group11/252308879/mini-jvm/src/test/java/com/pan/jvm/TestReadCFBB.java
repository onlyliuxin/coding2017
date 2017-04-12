package com.pan.jvm;

import com.pan.jvm.loader.ClassFileLoader;
import org.junit.Test;

import java.io.IOException;

/**
 * 用于测试第一次JVM作业，读取.class作业 和 魔幻数字
 */
public class TestReadCFBB {

    @Test
    public void testClassPath(){
        ClassFileLoader classFileLoader = new ClassFileLoader();
        String path = ClassFileLoader.class.getClassLoader().getResource("").getPath();
        path = path.replace("test-classes", "classes");
        classFileLoader.addClassPath(path);
        classFileLoader.addClassPath("d://tmp");

        String clzPath = classFileLoader.getClassPath();
        System.out.println(clzPath);
    }



    @Test
    public void testReadCFBB() throws IOException {

        ClassFileLoader classFileLoader = new ClassFileLoader();
        String path = ClassFileLoader.class.getClassLoader().getResource("").getPath();
        path = path.replace("test-classes", "classes");
        classFileLoader.addClassPath(path);
        byte[] bytes = classFileLoader.readBinaryCode("com.pan.jvm.loader.ClassFileLoader");
        for (byte b : bytes) {
            String toHexString = Integer.toHexString(b & 0xFF).toUpperCase();
            System.out.print(toHexString + " ");
        }
    }

}
