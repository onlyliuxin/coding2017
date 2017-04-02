package com.github.miniyk2012.coding2017.coderising.jvm.loader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
        InputStream is = null;
        ByteArrayOutputStream bas = null;
        byte[] ret = null;
		try {
            is = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream(className));
            bas = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = is.read(buf)) != -1) {
                bas.write(buf, 0, bytesRead);
            }
            ret = bas.toByteArray();
        } catch (IOException e) {
		    e.printStackTrace();
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
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	public String getClassPath(){
	    StringBuilder sb = new StringBuilder();
		for (String path : clzPaths){
		    sb.append(path).append(";");
        }
		return sb.substring(0, sb.length()-1);
	}

}
