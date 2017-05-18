package jvm.loader;

import jvm.clz.ClassFile;
import org.apache.commons.lang3.StringUtils;

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
        for (String path : clzPaths) {
            className = className.replace(".", File.separator);
            String clzFileName = path + File.separator + className + ".class";
            byte[] codes = loadClassFile(clzFileName);
            if (codes == null) {
                continue;
            }
            return codes;
        }
        return null;
    }

    private byte[] loadClassFile(String clzFileName) {
        try {
            File file = new File(clzFileName);
            if (!file.exists()) {
                return null;
            }
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void addClassPath(String path) {
        if (this.clzPaths.contains(path)) {
            return;
        }

        this.clzPaths.add(path);

    }

    public String getClassPath() {
        return StringUtils.join(this.clzPaths, ";");
    }

    public ClassFile loadClass(String className) {
        byte[] codes = this.readBinaryCode(className);
        ClassFileParser parser = new ClassFileParser();
        return parser.parse(codes);

    }
}
