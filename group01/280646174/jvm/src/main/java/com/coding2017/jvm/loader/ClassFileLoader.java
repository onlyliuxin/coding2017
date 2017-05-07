package com.coding2017.jvm.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.coding2017.jvm.clz.ClassFile;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class ClassFileLoader {
    private static Joiner SEMICOLON_JOINER = Joiner.on(";").skipNulls();
    private static Splitter DOT_SPLITTER = Splitter.on(".").trimResults();
    private static Joiner SLASH_JOINER = Joiner.on("/").skipNulls();

    private static String CLASS_SUFFIX = ".class";

    private List<String> clzPaths = new ArrayList<String>();

    public byte[] readBinaryCode(String className) {
        List<String> list = DOT_SPLITTER.splitToList(className);
        String childDirectory = SLASH_JOINER.join(list);
        for (String clzPath : clzPaths) {
            String fullPath = makeFullPath(clzPath, childDirectory);
            if (fileExist(fullPath)) {
                return readFileBytes(fullPath);
            }
        }
        System.out.println("no this class file: " + className);
        return null;
    }

    private byte[] readFileBytes(String filePath) {
        try {
            File file = new File(filePath);
            long length = file.length();
            byte[] fileBytes = new byte[(int) length];
            int readLength = new FileInputStream(filePath).read(fileBytes);
            if (readLength != length) {
                System.out.println("read file error. read length: " + readLength + ", full length : " + length);
                return null;
            }
            return fileBytes;
        } catch (IOException e) {
            System.out.println("read file error. " + filePath);
            return null;
        }
    }

    private boolean fileExist(String fullPath) {
        File classFile = new File(fullPath);
        return classFile.exists() && classFile.isFile();
    }

    private String makeFullPath(String clzPath, String childDirectory) {
        if (clzPath.endsWith("/") || clzPath.endsWith("\\")) {
            return clzPath + childDirectory + CLASS_SUFFIX;
        } else {
            return clzPath + "/" + childDirectory + CLASS_SUFFIX;
        }
    }

    public void addClassPath(String path) {
        if (!clzPaths.contains(path)) {
            clzPaths.add(path);
        }
    }

    public String getClassPath() {
        return SEMICOLON_JOINER.join(clzPaths);
    }

    public ClassFile loadClass(String className) {
        byte[] codes = this.readBinaryCode(className);
        ClassFileParser parser = new ClassFileParser();
        return parser.parse(codes);
    }

}
