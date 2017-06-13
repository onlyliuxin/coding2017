package com.coderising.jvm.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;

public class ClassFileLoader {

    private List<String> clzPaths = new ArrayList<String>();

    private static final Joiner JOINER = Joiner.on(";").skipNulls();

    public byte[] readBinaryCode(String className) throws FileNotFoundException {
        byte[] codeBytes;
        for (String path : clzPaths) {
            String clzPath = path + className.replace(".", "/") + ".class";
            try {
                byte[] buffer = new byte[1024];
                int size = 0;
                int index = 0;
                InputStream in = new FileInputStream(clzPath);
                codeBytes = new byte[in.available()];
                while ((size = in.read(buffer)) != -1) {
                    for (int i = 0; i < size; i++) {
                        codeBytes[index++] = buffer[i];
                    }
                }
                return codeBytes;
            } catch (Exception e) {

            }
        }
        return null;
    }

    public void addClassPath(String path) {
        clzPaths.add(path);
    }

    public String getClassPath() {
        return JOINER.join(clzPaths);
    }

}
