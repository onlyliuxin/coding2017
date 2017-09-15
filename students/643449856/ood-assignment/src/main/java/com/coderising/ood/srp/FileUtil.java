package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by nengneng on 2017/6/19.
 */
public class FileUtil {


    static void readFile(File file,Product product) throws IOException // @02C
    {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String temp = br.readLine();
            String[] data = temp.split(" ");

            product.setProductID(data[0]);
            product.setProductDesc(data[1]);

            System.out.println("产品ID = " + product.getProductID() + "\n");
            System.out.println("产品描述 = " + product.getProductDesc() + "\n");

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            br.close();
        }
    }

}
