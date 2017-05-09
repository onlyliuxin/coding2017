package com.coding.basic.tree;

import java.io.File;

public class FileList {
	
		
	public void list(File f) {	
		if(f==null){
			return ;
		}
		if(f.isDirectory()){
			System.out.println("Dir:"+f.getName());
			File[] fs = f.listFiles();
			for (File file : fs) {
				list(file);
			}
		}else{
			System.out.println(f.getName());
		}
	}

	public static void main(String[] args) {
		File f = new File("C:\\Users\\Administrator\\Desktop\\wechat");
		new FileList().list(f);
	}
	
}
