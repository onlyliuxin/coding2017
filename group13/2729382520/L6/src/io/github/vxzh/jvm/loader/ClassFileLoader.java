package io.github.vxzh.jvm.loader;

import io.github.vxzh.jvm.clz.ClassFile;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ClassFileLoader {

    private List<String> clzPaths = new ArrayList<String>();

    public byte[] readBinaryCode(String className) {
        className = className.replace('.', File.separatorChar) + ".class";
        for (String path : this.clzPaths) {
            String clzFileName = path + File.separatorChar + className;
            byte[] codes = loadClassFile(clzFileName);
            if (codes != null) {
                return codes;
            }
        }
        return null;
    }

    public void addClassPath(String path) {
        if (this.clzPaths.contains(path)) {
            return;
        }
        clzPaths.add(path);
    }

    public String getClassPath() {
        return StringUtils.join(this.clzPaths, ";");
    }

    private byte[] loadClassFile(String clzFileName) {
        File f = new File(clzFileName);
        try {
            return IOUtils.toByteArray(new FileInputStream(f));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ClassFile loadClass(String className) {
        byte[] codes = this.readBinaryCode(className);
        ClassFileParser parser = new ClassFileParser();
        return parser.parse(codes);

    }

}