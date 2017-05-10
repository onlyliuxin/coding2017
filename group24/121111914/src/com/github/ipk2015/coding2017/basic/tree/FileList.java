package com.github.ipk2015.coding2017.basic.tree;



import java.io.File;
/*
 * 给定一个目录，递归的列出下面所有的子目录和文件
 * */
public class FileList {
	public void list(File f) {
		listOneFile(f,0);
	}
	
	private void listOneFile(File f,int space){
		if(f.exists()){
			String stringSpace = " ";
			
			File[] listFiles = f.listFiles();
			for(int i = 0;i<listFiles.length;i++){
				for(int j = 0;j<space;j++){
					System.out.print(stringSpace);
				}
				System.out.println(listFiles[i].getName());
				if(listFiles[i].isDirectory()){
					listOneFile(listFiles[i],space+1);
				}
			}
		}
	}
	
}
