package com.coderising.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

    private List<String> clzPaths = new ArrayList<String>();

    public byte[] readBinaryCode(String className) {
        if (className == null || className.length() == 0) {
            return null;
        }

        File classFile = getClassFileFromClassPath(className);

        if (classFile == null) {
            return null;
        }

        return readFileBytes(classFile);
    }

    public void addClassPath(String path) {
        if (path == null || path.length() == 0) {
            return;
        }

        clzPaths.add(path);
    }

    public String getClassPath() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < clzPaths.size(); i++) {
            stringBuilder.append(clzPaths.get(i));
            if (i < clzPaths.size() - 1) {
                stringBuilder.append(";");
            }
        }

        return stringBuilder.toString();
    }

    private byte[] readFileBytes(File file) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private File getClassFileFromClassPath(String className) {
        className = className2FilePath(className);
        className += ".class";

        // 从当前路径读取
        File classFile = getFileFromDir("", className);
        if (classFile != null) {
            return classFile;
        }

        // 从环境变量路径读取
        for (String clzPath : clzPaths) {
            classFile = getFileFromDir(clzPath, className);
            if (classFile != null) {
                return classFile;
            }
        }

        return null;
    }

    private File getFileFromDir(String dir, String fileName) {
        File file = new File(dir);
        File destFile;
        if (file.exists() && file.isDirectory()) {
            destFile = new File(file.getAbsolutePath() + File.separator + fileName);
            if (destFile.isFile() && destFile.exists()) {
                return destFile;
            }
        }
        return null;
    }

    private String className2FilePath(String className) {
        return className.replaceAll("\\.", File.separator);
    }
}
