package com.pan.tree;

import java.io.File;


public class FileList {

    /**
     * 给定一个目录，递归给出这个目录下的子目录和文件
     * @param file
     */
    public void list(File file) {
        String[] path = file.list();
        for (String pathItem : path) {
            File fileTmp = new File(file.getAbsolutePath()+"\\"+pathItem);
            if (fileTmp.isDirectory()){
                System.out.print("\t");
                System.out.println(fileTmp);
                list(fileTmp);
            }else {
                System.out.print("\t\t");
                System.out.println(pathItem);
            }
        }
    }


}
