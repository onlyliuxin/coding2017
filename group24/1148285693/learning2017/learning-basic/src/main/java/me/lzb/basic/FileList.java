package me.lzb.basic;

import java.io.File;

/**
 * 给定一个目录，递归的列出下面所有的子目录和文件
 *
 * @author LZB
 */
public class FileList {

    public void list(File f) {
        System.out.println(f.getPath());

        list(f, 0);
    }


    private void list(File file, int level) {
        if (file.isDirectory()) {
            print(file, level);
        }
        level++;
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                list(files[i], level);
            } else {
                print(files[i], level);
            }
        }
    }

    private void print(File f, int level) {
        System.out.println(getFileFormat(level) + f.getName());

    }


    private String getFileFormat(int level) {
        StringBuffer sb = new StringBuffer();
        if (level > 1) {
            sb.append("|");
        }

        for (int i = 0; i < level - 1; i++) {
            sb.append("  ");
        }
        if (level > 0) {
            sb.append("|--");
        }
        return sb.toString();
    }
}
