package jvm_1;

import clz.ClassFile;
import parse.ClassFilePaser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by william on 2017/4/5.
 */
public class ClassFileLoader {
    private List<String> clzPaths = new ArrayList<String>();

    public byte[] readBinaryCode(String className) {
        InputStream fis = null;
        String filePath = clzPaths.get(0) + "\\\\" + className.replaceAll("\\.", "\\\\") + ".class";
        byte[] buffer = new byte[(int) new File(filePath).length()];
        try {
            if (clzPaths.size() > 0 && className != null && !className.trim().equals("")) {
                fis = new FileInputStream(filePath);
                while (fis.read(buffer) != -1) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    fis = null;
                }
        }
        return buffer;
    }


    public void addClassPath(String path) {
        clzPaths.add(path);
    }


    public String getClassPath() {
        StringBuilder sb = new StringBuilder();
        for (String clzPath : clzPaths) {
            sb.append(clzPath).append(";");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public ClassFile loadClass(String className) {
        byte[] bytes = readBinaryCode(className);
        ClassFilePaser classFilePaser = new ClassFilePaser();
        return classFilePaser.parse(bytes);
    }
}
