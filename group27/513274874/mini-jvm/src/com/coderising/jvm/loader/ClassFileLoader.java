package com.coderising.jvm.loader;

import com.coderising.jvm.clz.ClassFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ClassFileLoader {

    private List<String> clzPaths = new ArrayList<String>();
    private static final String CLASS_SUFFIX = ".class";
    private static final String PATH_SEPARATOR = System.getProperty("file.separator");

    public byte[] readBinaryCode(String className) {

        if (className == null || className.trim().equals("")) {
            throw new IllegalArgumentException("package and file name can't be blank!");
        }
        //扫描classpath，找到文件即停止扫描，找不到就报错
        String packageName = className.replace(".", PATH_SEPARATOR);
        String clazzURL = packageName + CLASS_SUFFIX;
        File file = null;
        for (String path : clzPaths) {
            file = new File(path + clazzURL);
            if (file.isDirectory() && file.length() > 0) break;
        }
        byte[] clazzByte = new byte[0];
        try {
            FileInputStream fis = new FileInputStream(file);
            DataInputStream data_in = new DataInputStream(fis);
            clazzByte = new byte[(int) file.length()];
            data_in.read(clazzByte, 0, (int) file.length());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clazzByte;
    }


    public void addClassPath(String path) {
        if (null == path || path.trim().equals("")) return;
        clzPaths.add(path);
    }


    public String getClassPath() {
        String clazzPaths = "";
        for (String clazzPath : clzPaths) {
            clazzPaths += clazzPath + ";";
        }
        return clazzPaths;
    }

    public ClassFile loadClass(String className) {
        byte[] codes = this.readBinaryCode(className);
        ClassFileParser parser = new ClassFileParser();
        return parser.parse(codes);

    }


  }
