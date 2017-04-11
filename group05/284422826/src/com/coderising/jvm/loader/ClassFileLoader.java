package com.coderising.jvm.loader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ClassFileLoader {

    private List<String> clzPaths = new ArrayList<>();

    public byte[] readBinaryCode(String className) {
        String name = this.getClassPath() + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        File file = new File(name);
        byte[] bytes = new byte[(int)file.length()];
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            while (bis.read(bytes) != -1) {
                System.out.println(Arrays.toString(bytes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }


    public void addClassPath(String path) {
        clzPaths.add(path);
    }


    public String getClassPath() {
        StringBuilder path = new StringBuilder();
        for (String str : clzPaths) {
            path.append(str).append(";");
        }
        return path.substring(0, path.length() - 1);
    }


}
