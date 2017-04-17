package io.github.vxzh.jvm.loader;

import io.github.vxzh.jvm.clz.ClassFile;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ClassFileLoader {

    private static final int BUFFER_MAX_SIZE = 1024;
    private List<String> clzPaths = new ArrayList<String>();

    public byte[] readBinaryCode(String className) {

        className = className.replaceAll("\\.", "/");
        File file = findFile(className);
        if (file == null) {
            return new byte[0];
        }

        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte buffer[] = new byte[BUFFER_MAX_SIZE];
            int len = -1;
            while ((len = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
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

        StringBuilder builder = new StringBuilder();
        for (String path : clzPaths) {
            builder.append(path).append(";");
        }

        return builder.toString().substring(0, builder.toString().length() - 1);
    }

    private File findFile(String className) {
        for (String path : clzPaths) {
            String filePath = path + "/" + className + ".class";
            File file = new File(filePath);
            if (file.exists()) {
                return file;
            }
        }
        return null;
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