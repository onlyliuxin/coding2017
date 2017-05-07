package task4.loader;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class ClassFileLoader {

    private List<String> clzPaths = new ArrayList<>();

    public byte[] readBinaryCode(String className) {
        boolean validClassName = className.endsWith(".class");
        className = className.replaceAll("\\.", "/");
        if (!validClassName) {
            className += ".class";
        } else {
            className = className.replace("/class", ".class");
        }
        for (String clzPath : clzPaths) {
            if (!clzPath.endsWith("/"))
                clzPath += "/";
            try {
                FileInputStream stream = new FileInputStream(clzPath + className);
                byte[] buffer = new byte[stream.available()];
                while (stream.read(buffer) != -1) {
                }
                return buffer;
            } catch (IOException e) {
                continue;
            }
        }
        return null;
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

}