package com.coderising.jvm.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		try {
		    String path = className.replaceAll("\\.","/");
            System.out.println(path);
//            String base = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
            String base = "D:\\src\\java\\study\\coding2017\\group13\\2931408816\\lesson4\\build\\classes\\main";
            System.out.println(base);
            InputStream inputStream = new FileInputStream(base+"/"+path+".class");
			byte[] bytes = new byte[inputStream.available()];
			inputStream.read(bytes);
			return bytes;
		} catch (IOException e) {
            e.printStackTrace();
        }
        return null;
		
	}
	
	
	public void addClassPath(String path) {
	    clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
	    StringBuilder builder = new StringBuilder();
        for (String s :
                clzPaths) {
            builder.append(s).append(";");
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
//		return null;
	}

	

	

}
