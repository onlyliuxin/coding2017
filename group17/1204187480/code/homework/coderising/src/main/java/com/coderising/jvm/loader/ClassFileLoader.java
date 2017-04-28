package com.coderising.jvm.loader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by luoziyihao on 4/27/17.
 */
public class ClassFileLoader {

    private Set<String> clzPaths;

    private Map<String, byte[]> clzContext;

    public void addClassPath(String path) {
        if (clzPaths == null) {
            clzPaths = new HashSet<>(5);
        }
        if (StringUtils.isBlank(path)) {
            return;
        }
        File file = new File(path);
        if(file.isDirectory()) {
         clzPaths.add(path);
        }

    }


    private static final String SPLIT = ";";

    public String getClassPath() {
        StringBuilder classPath = new StringBuilder();

        for (String e : clzPaths) {
            classPath.append(e)
                    .append(SPLIT);
        }
        if (classPath.length() > 1) {
            classPath.deleteCharAt(classPath.length() - 1);
        }
        return classPath.toString();
    }

    public byte[] readBinaryCode(String className) {
        if (StringUtils.isBlank(className)) {
            throw new IllegalStateException("className is blank");
        }
        byte[] binaryCode = getClzContext().get(className);
        if (binaryCode == null) {
            throw new IllegalStateException("className is not found in classpath");
        }
        return binaryCode;
    }

    private Map<String, byte[]> getClzContext() {
        if (clzContext == null) {
            clzContext = createClzContextWithClzPaths(clzPaths);
        }
        return clzContext;
    }

    private Map<String, byte[]> createClzContextWithClzPaths(Set<String> clzPaths) {
        Map<String, byte[]> clzContext = new ConcurrentHashMap<>(60);
        for (String e : clzPaths){
            File file = new File(e);
            if (file.isDirectory()) {
                List<File> files = FileUtils.listFiles(file)
            }
        }
        return null;
    }
}
