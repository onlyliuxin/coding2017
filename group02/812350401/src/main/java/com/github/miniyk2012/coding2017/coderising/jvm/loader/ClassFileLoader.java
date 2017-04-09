package com.github.miniyk2012.coding2017.coderising.jvm.loader;

import org.apache.commons.io.FileUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
//        InputStream is = null;
//        ByteArrayOutputStream bas = null;
//        byte[] ret = null;
//		try {
//            is = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream(className));
//            bas = new ByteArrayOutputStream();
//            byte[] buf = new byte[1024];
//            int bytesRead = 0;
//            while ((bytesRead = is.read(buf)) != -1) {
//                bas.write(buf, 0, bytesRead);
//            }
//            ret = bas.toByteArray();
//        } catch (IOException e) {
//		    e.printStackTrace();
//        } finally {
//		    try {
//                if (is != null)
//                    is.close();
//                if (bas != null)
//                    bas.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return ret;
        int lastIndexDot = className.lastIndexOf(".");
        String classPath = className.substring(0, lastIndexDot).replace(".", File.separator)
                + className.substring(lastIndexDot);
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

    public static void main(String[] args) {
        new ClassFileLoader().readBinaryCode("");
    }
}
