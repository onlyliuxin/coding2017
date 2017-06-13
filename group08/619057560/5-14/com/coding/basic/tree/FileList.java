package com.coding.basic.tree;

import java.io.File;

public class FileList {
	public void list(File f) {
		if (!f.exists()) {
			return;
		}
		
		System.out.println(f.getName());
		if (f.isDirectory()) {
			for (File sub:f.listFiles()) {
				list(sub);
			}
		}
	}

	
}
