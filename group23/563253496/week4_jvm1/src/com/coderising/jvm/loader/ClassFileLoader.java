package com.coderising.jvm.loader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ClassFileLoader {

    private List<String> clzPaths = new ArrayList<String>();

    public byte[] readBinaryCode(String className) {
        String path = getClassFilePath(className);
        if (path != null) {
            try {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
                int count = bis.available();
                byte[] content = new byte[count];
                int len = bis.read(content,0,count);
                return content;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;


    }

    private byte[] loadClassFile(String clzFileName) {

        return null;
    }


    public void addClassPath(String path) {
        clzPaths.add(path);
    }

    public String getClassPath_V1() {

        return null;
    }

    public String getClassPath() {
        StringBuilder sb = new StringBuilder();
        for (String s : clzPaths) {
            sb.append(s);
            sb.append(";");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private String getClassFilePath(String className) {
        StringBuilder sb = new StringBuilder();
        for (String path : clzPaths
                ) {
            sb.append(path);
            sb.append("\\");
            char[] classname = className.toCharArray();
            for (int i = 0; i < classname.length; i++) {
                if (classname[i] == '.') {
                    sb.append("\\");

                } else {
                    sb.append(classname[i]);
                }
            }
            sb.append(".class");
            String classpath = sb.toString();
            File file = new File(classpath);
            if (file.exists()) {
                return classpath;
            }
        }
        return null;
    }
}
