package com.github.miniyk2012.coding2017.basic.tree;

import java.io.File;
import java.io.FileNotFoundException;

public class FileList {
	public void list(File f) throws FileNotFoundException {
	    if (!f.exists()) {
	        throw new FileNotFoundException("该文件不存在");
        }
        showFiles(f, 0);
	}

    private void showFiles(File f, int i) {
	    if (f.isFile()) {
            System.out.printf(f.getName() + " ");
        } else {
            System.out.println();
            System.out.println(i+" depth "+f.getAbsolutePath());
            for (File subFile: f.listFiles()) {
	            showFiles(subFile, i+1);
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
		File f = new File("/Users/thomas_young/Documents/code/coding2017/group02/812350401/src/main/java/com/github/miniyk2012/coding2017");
		FileList fileList = new FileList();
		fileList.list(f);
	}
	
}
