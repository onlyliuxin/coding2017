package jvm.loader;

import jvm.clz.ClassFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiaxun
 */
public class ClassFileLoader {


    private List<String> clzPaths = new ArrayList<>();

    public byte[] readBinaryCode(String className) {
        className = className.replace(".", "/") + ".class";
        File classFile = null;
        for (String classPath : clzPaths) {
            File file = new File(classPath, className);
            if (file.exists()) {
                classFile = file;
                break;
            }
        }
        if (classFile == null) return null;
        try {
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(classFile));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            int len;
            byte[] b = new byte[1024];
            while ((len = input.read(b)) != -1) {
                output.write(b, 0, len);
            }
            return output.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addClassPath(String path) {
        if (!clzPaths.contains(path)) {
            clzPaths.add(path);
        }
    }


    public String getClassPath() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, len = clzPaths.size(); i < len; i++) {
            builder.append(clzPaths.get(i));
            if (i != len - 1) {
                builder.append(";");
            }
        }
        return builder.toString();
    }

    public ClassFile loadClass(String className) {
        byte[] codes = this.readBinaryCode(className);
        ClassFileParser parser = new ClassFileParser();
        return parser.parse(codes);
    }

    public String getClassPath_V1() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < this.clzPaths.size(); i++) {
            buffer.append(this.clzPaths.get(i));
            if (i < this.clzPaths.size() - 1) {
                buffer.append(";");
            }
        }
        return buffer.toString();
    }

    private byte[] loadClassFile_V1(String clzFileName) {
        BufferedInputStream bis = null;
        try {
            File f = new File(clzFileName);
            bis = new BufferedInputStream(new FileInputStream(f));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }
            byte[] codes = bos.toByteArray();
            return codes;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
