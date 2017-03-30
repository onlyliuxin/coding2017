package com.coderising.jvm.loader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();

	public byte[] readBinaryCode(String className) throws ClassNotFoundException, IOException {
		if (clzPaths.size() == 0) {
			return new byte[0];
		}
		String actualPath = getActualPath(className);
		
		File f = new File(actualPath);  
		
        if (!f.exists()) {  
            throw new ClassNotFoundException(actualPath);  
        }  
  
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());  
        BufferedInputStream is = null;  
        try {  
            is = new BufferedInputStream(new FileInputStream(f));  
            
            byte[] buffer = new byte[1024];  
            int len = 0;  
            
            while (-1 != (len = is.read(buffer))) {  
                bos.write(buffer, 0, len);  
            } 

            return bos.toByteArray();  
            
        } catch (IOException e) {  
            e.printStackTrace();  
            throw e;  
        } finally {  
            is.close();
            bos.close();  
        }  
	}

	private String getActualPath(String className) {
		
		String fileName = className.substring(className.lastIndexOf(".") + 1) + ".class";
		String dirPath = className.substring(0, className.lastIndexOf(".")).replace(".", "\\");
		
		return clzPaths.get(clzPaths.size() - 1) + "\\" + dirPath + "\\" + fileName;   //classPath  取最近添加的一个
		
	}

	public void addClassPath(String path) {

		if (path == null) {
			return;
		}

		clzPaths.add(path);

	}

	public String getClassPath() {

		if (clzPaths.size() == 0) {
			return "";
		}

		StringBuffer buffer = new StringBuffer("");

		for (String str : clzPaths) {
			buffer.append(str);
			buffer.append(";");
		}

		return buffer.substring(0, buffer.length() - 1);// 去除最后一个分号

	}

}
