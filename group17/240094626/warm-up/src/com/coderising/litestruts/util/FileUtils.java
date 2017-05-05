package com.coderising.litestruts.util;

import java.io.File;

public class FileUtils {

	public static  boolean createDir(String dir){
		File f = null;
		f = new File(dir);
		boolean ok = true;
		if(!f.exists()){
			ok = f.mkdirs();
		}
		return ok;
	}
	
	public static String getProjectPath(){
		return System.getProperty("user.dir");
	}
	
	public static void main(String[] args) {
		System.out.println(createDir("src/com/coderising/litestruts/"));
		System.out.println(getProjectPath());
	}
}
