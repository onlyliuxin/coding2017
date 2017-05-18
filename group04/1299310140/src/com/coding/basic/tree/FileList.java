package com.coding.basic.tree;

import java.io.File;

public class FileList {
	
	public static void list(File f,int l) {
		if(!f.isFile() && !f.isDirectory()){
			return;
		}
		
		for(int i = 0;i < l;i++){//对齐
			System.out.print("|--");
		}
		
		System.out.println(f.getName());
		if(f.isDirectory()){
			File[] files_L = f.listFiles();
			for(File file : files_L){
				list(file,l+1);
			}
		}
	}
	
	public static void main(String[] args){
		list(new File("C:/"),0);
	}
}
