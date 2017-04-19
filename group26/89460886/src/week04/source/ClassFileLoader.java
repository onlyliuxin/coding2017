package jvm.loader;

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

}
