package com.coderising.jvm.loader;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by luoziyihao on 4/27/17.
 */
public class ClassFileLoader {

    private Set<String> clzPaths;

    public void addClassPath(String path) {
        if (clzPaths == null) {
            clzPaths = new HashSet<>(5);
        }
        if (StringUtils.isBlank(path)) {
            return;
        }
        clzPaths.add(path);

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
        return new byte[0];
    }
}
