package com.github.miniyk2012.coding2017.basic.tree;

import java.io.File;
import java.io.FileNotFoundException;

public class FileList {
	public void list(File f) throws FileNotFoundException {
	    if (!f.exists()) {
	        throw new FileNotFoundException("该文件不存在");
        }
        list(f, 0);
	}

    private void list(File f, int depth) {
	    printName(f, depth);
	    if (f.isDirectory()) {
	        for (File subFile: f.listFiles()) {
	            list(subFile, depth+1);
            }
        }
    }

    private void printName(File f, int depth) {
	    String name = f.getName();
	    for (int i=0; i<depth; i++) {
            System.out.print("+");
        }
        if (f.isFile()) {
            System.out.println(name + " " + f.length());
        } else {
            System.out.println("Dir: " + name);
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
		File f = new File("/Users/thomas_young/Documents/code/coding2017/group02/812350401/src/main/java/com/github/miniyk2012/coding2017");
		FileList fileList = new FileList();
		fileList.list(f);
	}
	
}
