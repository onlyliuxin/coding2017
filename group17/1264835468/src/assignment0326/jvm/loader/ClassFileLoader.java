package assignment0326.jvm.loader;

import assignment0326.jvm.clz.ClassFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */
public class ClassFileLoader {


    private List<String> clzPaths = new ArrayList<>();

    public byte[] readBinaryCode(String className) {
        String path = className.replace(".", File.separator);
        File classFile = null;
        for (String p : clzPaths) {
            classFile = new File(p + File.separator + path + ".class");
            if (classFile.exists())
                break;
        }
        if (classFile == null)
            throw new RuntimeException("no such class file");

        byte[] bytes = new byte[(int) classFile.length()];
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(classFile))) {
            bufferedInputStream.read(bytes, 0, bytes.length);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }


    public void addClassPath(String path) {
        if (this.clzPaths.contains(path)) {
            return;
        }
        clzPaths.add(path);
    }


    public String getClassPath() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < clzPaths.size() - 1; i++) {
            stringBuilder.append(clzPaths.get(i) + ";");
        }
        stringBuilder.append(clzPaths.get(clzPaths.size() - 1));
        return stringBuilder.toString();
    }


    private byte[] loadClassFile(String clzFileName) {

        try {

            return Files.readAllBytes(Paths.get(clzFileName));


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
