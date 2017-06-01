package com.coding.basic.tree;

import java.io.File;

public class FileList {
	public void list(File f) {
		System.out.println(f.getName());
		File[] files=f.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				list(files[i]);
			}
			System.out.println(files[i].getName());
		}
	}
	
	public static void main(String args[]) {
		FileList fileList=new FileList();
		fileList.list(new File("."));
	}
}
