package com.github.orajavac.coding2017.basic.tree;

import java.io.File;
public class FileList {

	public void list(File f) {
		if(f!=null){
            if(f.isDirectory()){
                File[] fileArray=f.listFiles();
                if(fileArray!=null){
                    for (int i = 0; i < fileArray.length; i++) {
                        //递归调用
                        list(fileArray[i]);
                    }
                }
            }
            else{
                System.out.println(f);
            }
        }
	}

}
