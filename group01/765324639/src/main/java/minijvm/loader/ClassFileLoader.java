package minijvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import minijvm.clz.ClassFile;



public class ClassFileLoader {

    private List<String> clzPaths = new ArrayList<String>();

    public byte[] readBinaryCode(String className) throws ClassNotFoundException {
        File classFile = getClassFile(className);
        return readFileToByteArray(classFile);
    }

    private File getClassFile(String className) throws ClassNotFoundException {
        File classFile = null;
        String classFilePath = convertClassNameToFilePath(className);
        for (String path : clzPaths) {
            File file = new File(path, classFilePath);
            if (file.exists()) {
                classFile = file;
                break;
            }
        }

        if (classFile == null) { // 文件不存在
            throw new ClassNotFoundException();
        }
        return classFile;
    }

    private byte[] readFileToByteArray(File classFile) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (InputStream in = new FileInputStream(classFile);) {
            byte[] data = new byte[1024];
            int len = -1;
            while ((len = in.read(data)) != -1) {
                out.write(data, 0, len);
            }
        } catch (FileNotFoundException e) { // 调用此函数时，已经确定文件是存在的
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return out.toByteArray();
    }

    private String convertClassNameToFilePath(String fillClassName) {
        return fillClassName.replace(".", File.separator) + ".class";
    }

    public void addClassPath(String path) {
        if (clzPaths.contains(path)) {
            return;
        }
        clzPaths.add(path);
    }

    public String getClassPath() {
        return String.join(";", clzPaths);
    }

    public ClassFile loadClass(String className) throws ClassNotFoundException {
        byte[] codes = this.readBinaryCode(className);
        ClassFileParser parser = new ClassFileParser();
        return parser.parse(codes);
        
    }

}
