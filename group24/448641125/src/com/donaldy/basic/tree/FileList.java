package com.donaldy.basic.tree;

import java.io.File;

public class FileList {

	public void list(File f) {

		System.out.println(f.getName() + " : ");

		for (File file : f.listFiles()) {

			System.out.println(file.getName());

			if (file.isDirectory()) {

				list(file);

			}

		}


	}

	
}
