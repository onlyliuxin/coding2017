package jvm.loader;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nvarchar
 *         date 2017/4/24
 */
public class ClassFileLoader {
    private List<String> clzPaths = new ArrayList<String>();

    public byte[] readBinaryCode(String className) {
        try {
            File file;
            for (String path : clzPaths) {
                className = className.replace(".", File.separator);
                file = new File(path + File.separator + className + ".class");
                if (!file.exists()) {
                    continue;
                }
                return Files.readAllBytes(file.toPath());
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


    public void addClassPath(String path) {
        clzPaths.add(path);
    }


    public String getClassPath() {
        return String.join(";", clzPaths);
    }

}
