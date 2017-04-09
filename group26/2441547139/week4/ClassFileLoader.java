package week4;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zndbl on 2017/4/9.
 */
public class ClassFileLoader {

    private static final int BUFFER_SIZE = 1024;

    private List<String> clzPaths = new ArrayList<String>();

    public byte[] readBinaryCode(String className) {
        for (String classPath : clzPaths) {
            String fileName = classPath + className.replace(".", "\\") + ".class";
            BufferedInputStream bis = null;
            ByteArrayOutputStream baos = null;
            try {
                bis = new BufferedInputStream(new FileInputStream(fileName), BUFFER_SIZE);
                baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[BUFFER_SIZE];
                int len = 0;
                while ((len = bis.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return baos.toByteArray();
        }
        return null;
    }

    public void addClassPath(String classPath) {
        if (classPath == null) {
            return;
        }
        clzPaths.add(classPath);
    }

    public String getClassPath() {
        StringBuilder sb = new StringBuilder();
        int size = clzPaths.size();
        for (int i = 0; i < size; i++) {
            sb.append(clzPaths.get(i));
            if (i + 1 < size) {
                sb.append(";");
            }
        }
        return sb.toString();
    }
}
