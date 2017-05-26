package com.aaront.exercise.basic;

import java.io.File;

public class FileList {
    public void list(File f, int deep) {
        if(f == null) return;
        System.out.println(_leftPad(deep) + f.getName());
        if(f.isFile()) return;
        File[] files = f.listFiles();
        for (int i = 0, len = files.length; i < len; i++) {
            list(files[i], deep + 1);
        }
    }

    private String _leftPad(int deep) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < deep; i++) {
            sb.append("----");
        }
        return sb.toString();
    }
}