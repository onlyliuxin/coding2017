package com.coderising.jvm.loader;


import com.coding.common.util.FileUtils2;
import org.apache.commons.lang3.StringUtils;
import strman.Strman;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.coding.common.util.FileUtils2.getCanonicalPath;
import static org.apache.commons.lang3.StringUtils.replace;
import static org.apache.commons.lang3.StringUtils.substringAfter;

/**
 * Created by luoziyihao on 4/27/17.
 */
public class ClassFileLoader {

    private List<String> clzPaths;

    private Map<String, byte[]> clzContext;

    public void addClassPath(String path) {
        if (clzPaths == null) {
            clzPaths = new ArrayList<>(5);
        }
        if (StringUtils.isBlank(path)) {
            return;
        }
        File file = new File(path);
        if (!file.isDirectory()) {
            return;
        }
        String canonicalName = getCanonicalPath(file);
        if (clzPaths.contains(canonicalName)) {
            return;
        }
        clzPaths.add(getCanonicalPath(file));
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

    private static final String CLZ_SUFFIX = ".class";

    public byte[] readBinaryCode(String className) {
        if (StringUtils.isBlank(className)) {
            throw new IllegalStateException("className is blank");
        }
        byte[] binaryCode = getClzContext().get(Strman.append(className, CLZ_SUFFIX));
        if (binaryCode == null) {
            throw new IllegalStateException(
                    Strman.format("className={0} is not found in classpath", className));
        }
        return binaryCode;
    }

    private Map<String, byte[]> getClzContext() {
        if (clzContext == null) {
            clzContext = createClzContextWithClzPaths(clzPaths);
        }
        return clzContext;
    }

    private Map<String, byte[]> createClzContextWithClzPaths(List<String> clzPaths) {
        Map<String, byte[]> clzContext = new ConcurrentHashMap<>(60);
        for (String e : clzPaths) {
            File file = new File(e);
            if (file.isDirectory()) {
                List<File> files = FileUtils2.listAllFiles(file);
                clzContext = addClassElements(clzContext, e, files);
            }
        }
        return clzContext;
    }

    private Map<String, byte[]> addClassElements(Map<String, byte[]> clzContext, String classpath, List<File> files) {
        for (File classFile : files) {
            String filePath = getCanonicalPath(classFile);
            String canonicalName = getCanonicalName(classpath, filePath);
            byte[] bytes = FileUtils2.getBytes(classFile);
            clzContext.put(canonicalName, bytes);
        }
        return clzContext;
    }

    /**
     * 将classpath 下的文件路径转成 a.b.c.class 的格式
     */
    private static final String POINT = ".";

    private String getCanonicalName(String classpath, String filePath) {
        String tmp = replace(substringAfter(filePath, classpath), File.separator, POINT);
        if (tmp.startsWith(POINT)) {
            tmp = StringUtils.removeStart(tmp, POINT);
        }
        return tmp;
    }
}
