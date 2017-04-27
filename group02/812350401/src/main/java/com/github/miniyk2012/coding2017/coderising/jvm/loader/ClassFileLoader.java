package com.github.miniyk2012.coding2017.coderising.jvm.loader;

import com.github.miniyk2012.coding2017.coderising.jvm.clz.ClassFile;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {

        String classPath = className.replace(".", File.separator) + ".class";
        for (String parentPath: clzPaths) {
            try {
                String fullPath = parentPath + File.separator + classPath;
                return FileUtils.readFileToByteArray(new File(fullPath));
            } catch (IOException e) {
                continue;
            }
        }
        return null;
    }
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	public String getClassPath(){
		return String.join(";", clzPaths);
	}

    public ClassFile loadClass(String className) {
        byte[] codes = this.readBinaryCode(className);
        ClassFileParser parser = new ClassFileParser();
        return parser.parse(codes);

    }

    // backup
    public byte[] readBinaryCodeV1(String className) {
        byte[] ret = null;
        String classPath = className.replace(".", File.separator) + ".class";
        for (String parentPath: clzPaths) {
            String fullPath = parentPath + File.separator + classPath;
            ret = readFileToByteArray(fullPath);
            if (null != ret) {
                return ret;
            }

        }
        return ret;

    }

    private byte[] readFileToByteArray(String fullPath) {
        InputStream is = null;
        ByteArrayOutputStream bas = null;
        byte[] ret = null;
        try {
            is = new BufferedInputStream(new FileInputStream(new File(fullPath)));
            bas = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = is.read(buf)) != -1) {
                bas.write(buf, 0, bytesRead);
            }
            ret = bas.toByteArray();
        } catch (IOException e) {
//            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (bas != null)
                    bas.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        new ClassFileLoader().readBinaryCode("");
    }
}
