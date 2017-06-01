package com.github.HarryHook.coding2017.tree;

import java.io.File;

/*
 * 给定一个目录，递归的列出下面所有的子目录和文件
 */
public class FileList {

    public static void list(File f) {
	File[] arr = f.listFiles();// 先列出当前文件夹下的文件及目录
	
	for (File file : arr) {
	    if (file.isDirectory()) {// 列出的东西是目录吗
		System.out.println(file.getName());
		
		list(file);// 是就继续获得子文件夹，执行操作
		System.out.println();
	    } else {
		// 不是就把文件名输出
		System.out.println(file.getName());
	    }
	}
    }

    public static void main(String[] args) {
	File file = new File("f:/CodeRising");// 创建目录对象
	list(file);// 打开目录下的所有文件及文件夹
    }

}
