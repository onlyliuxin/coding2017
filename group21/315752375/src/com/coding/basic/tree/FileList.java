package com.coding.basic.tree;

import java.io.File;

public class FileList {
	public void list(File f) {
		if(f.isDirectory()){
			System.out.println(f.getAbsolutePath());
			File[]child=f.listFiles();
			for(File file:child){
				list(file);
			}
		}else{
			System.out.println(f.getAbsolutePath());
		}
	}
}
