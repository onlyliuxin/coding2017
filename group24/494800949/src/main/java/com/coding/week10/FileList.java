package com.coding.week10;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileList {

	private List<File> fileList = new ArrayList<>();

	public void list(File f) {

		/*
			1 如果f为空或不存在返回
			2 如果f是文件，则直接add并返回
			3 如果f是文件夹，则递归调用list
		 */
		if (f == null || !f.exists()) {
			return;
		}
		if (f.isFile()) {
			fileList.add(f);
			return;
		}
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			if (files != null) {
				for (File file : files) {
					list(file);
				}
			}
		}
	}

	public static void main(String[] args) {
		FileList list  =  new FileList();
		File f = new File("H:\\sourceCode\\coding2017\\group24\\494800949");

		list.list(f);

		System.out.println(list.fileList.size());
		for (File file : list.fileList) {
			System.out.println(file.getName());
		}
	}
	
}
