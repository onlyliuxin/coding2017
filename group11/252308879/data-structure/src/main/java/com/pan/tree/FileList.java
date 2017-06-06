package com.pan.tree;

import java.io.File;


public class FileList {

    /**
     * 给定一个目录，递归给出这个目录下的子目录和文件
     *
     * @param file
     */
    public void list(File file) {
        list(file, 0);
    }

    public void list(File file, int depth) {
        printName(file, depth);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                list(f, depth + 1);
            }
        }
    }

    void printName(File file, int depth) {
        String name = file.getName();
        for (int i = 0; i < depth; i++) {
            System.out.print("|-");
        }
        if (file.isDirectory()) {
            System.out.println("Dir: " + name);
        } else {
            System.out.println(name + " " + file.length());
        }
    }
}
