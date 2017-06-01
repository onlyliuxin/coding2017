package org.xukai.coderising.tree;

import java.io.File;

public class FileList {



	public static void list(File root) {
		if (!root.exists()) {
			System.out.println("不存在该目录");
		}
		if (root.isFile()) {
			System.out.println(root.getName());
		} else {
			System.out.println(root.getName());
			File[] files = root.listFiles();
			if (files != null && files.length > 0) {
				for (File file : files) {
					list(file);
				}
			}
		}


	}

	public static void main(String[] args) {
		list(new File("D:\\java\\IDEA-Workspace\\coding2017\\liuxin\\data-structure\\assignment\\src\\com"));
	}

	
}
