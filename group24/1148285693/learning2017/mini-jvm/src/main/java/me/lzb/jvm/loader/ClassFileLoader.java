package me.lzb.jvm.loader;


import me.lzb.common.utils.FileUtils;
import me.lzb.jvm.clz.ClassFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ClassFileLoader {

    private List<String> clzPaths = new ArrayList<>();

    public byte[] readBinaryCode(String className) throws IOException {
//        String fileName = className.replaceAll(".*\\.", "")  + ".class";
//        String pkg = className.replaceAll("\\.[^\\.]+$", "");
//        String packagePath = pkg.replaceAll("\\.", "\\\\");

        className = className.replace('.', File.separatorChar) + ".class";
        for (String s : clzPaths) {
            byte[] data = FileUtils.readByteCodes(s + className);
            if (data != null) {
                return data;
            }
        }

        throw new IOException(className + "is not exist");
    }


    public void addClassPath(String path) {
        clzPaths.add(path);
    }


    public String getClassPath() {
        StringBuilder buffer = new StringBuilder();
        for (Iterator<String> iterator = clzPaths.iterator(); iterator.hasNext(); ) {
            buffer.append(iterator.next() + (iterator.hasNext() ? ";" : ""));
        }
        return buffer.toString();
    }


    public ClassFile loadClass(String className) throws IOException {
        byte[] codes = this.readBinaryCode(className);
        ClassFileParser parser = new ClassFileParser(codes);
        return parser.parse();
    }


}
