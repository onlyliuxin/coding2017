package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by zhenli on 17/6/11.
 */
public class ProductFileOperation {

    /**
     * 读取产品文件内容
     * @param file wenjian
     * @throws IOException
     */
    public String[] readFile(File file) throws IOException // @02C
    {
        BufferedReader br = null;
        String[] data = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String temp = br.readLine();
            data = temp.split(" ");
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            if(!br.equals(null)) {
                br.close();
            }else {
                System.out.println("没有读取文件");
            }

        }
        return data;
    }
}
