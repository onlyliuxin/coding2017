package com.aaront.exercise.jvm.loader;

import com.aaront.exercise.jvm.utils.string.FileUtils;
import com.aaront.exercise.jvm.utils.string.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ClassFileLoader {

    private List<String> clzPaths = new ArrayList<String>();

    public byte[] readBinaryCode(String className) throws IOException {
        String[] parts = _parseClassPath(className);
        List<File> files = _convertFromNameToFile(clzPaths);
        for (int i = 0, len = parts.length; i < len; i++) {
            files = _filterFileByName(files, parts[i]);
        }
        if (files.size() != 1) throw new IllegalArgumentException("className不合法");
        return _readFile(files.get(0));
    }

    public void addClassPath(String path) {
        if (StringUtils.isEmpty(path)) return;
        if (!FileUtils.isDictionary(path)) return;
        clzPaths.add(path);
    }

    public String getClassPath() {
        return StringUtils.join(clzPaths, ";");
    }

    private String[] _parseClassPath(String className) {
        String[] parts = className.split("\\.");
        parts[parts.length - 1] = parts[parts.length - 1] + ".class";
        return parts;
    }

    private List<File> _convertFromNameToFile(List<String> paths) {
        return paths.stream().map(File::new).collect(Collectors.toList());
    }

    private List<File> _filterFileByName(List<File> paths, String name) {
        return paths.stream().flatMap(path -> {
            File[] files = path.listFiles(file -> file.getName().equals(name));
            if (files == null) return Stream.of();
            return Arrays.stream(files);
        }).collect(Collectors.toList());
    }

    private byte[] _readFile(File file) throws IOException {
        byte[] content = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(content);
        return content;
    }
}
