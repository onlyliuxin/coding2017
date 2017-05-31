package com.coding.basic.tree;

import java.io.File;

public class FileList {

	/**
	 * 给定一个目录，递归的列出下面所有的子目录和文件
	 * @param f
	 */
	public void list(File f) {
		System.out.println(f.getName() + ":");
		for (File file : f.listFiles()) {
			System.out.println(file.getName());
			if (file.isDirectory()) {
				list(file);
			}
		}
	}
}
