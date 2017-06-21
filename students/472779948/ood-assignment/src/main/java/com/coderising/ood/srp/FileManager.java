package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {
    //读取配置文件方法，单独提出来，其他的类需要时可复用
    public static String[] readFile(File file) throws IOException // @02C
    {
        String data[] = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String temp = br.readLine();
            data = temp.split(" ");
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            br.close();
        }
        return data;
    }
}
