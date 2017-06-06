package com.coding.basic.tree;

import java.io.File;

public class FileList {
	public void list(File f) {	
        if (f.exists()) {
            File[] files = f.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file : files) {
                    if (file.isDirectory()) {
                        System.out.println("文件夹:" + file.getAbsolutePath()); 
                        list(file);
                    } else {
                        System.out.println("文件:" + file.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
	}
	public static void main(String[] args) {
		FileList fileList=new FileList();
		File f=new File("F:\\使用手册");
		fileList.list(f);
	}

	
}
