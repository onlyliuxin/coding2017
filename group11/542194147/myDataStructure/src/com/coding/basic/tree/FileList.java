package com.coding.basic.tree;

import java.io.File;

public class FileList {

	 public static void list(File file,int depth) {
	        if(file.isDirectory()){
	        	File[] files=file.listFiles();
	        	depth+=1;
	        	for(File f:files){
	        		for(int i=0;i<depth;i++){
	        			System.out.print("|-");
	        		}
	        		if(f.isDirectory()){
	        			System.out.println("directory:"+f.getName());
		        		list(f,depth);
	        		}
	        		else{
	        			System.out.println("file:"+f.getName());
	        		}
	        		
	        	}
	        }
	    }
	
	 public static void main(String[] args) {
		File file=new File("C:\\Users\\john\\Desktop\\Java学习资料");
		list(file,0);
	}
}
