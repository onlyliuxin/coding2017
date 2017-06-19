package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by kenhuang on 2017/6/15.
 */
public class FileUtil {
    private static final String FILE_PATH="/Users/kenhuang/Desktop/ood-assignment/src/main/java/com/coderising/ood/srp/product_promotion.txt";
    private static File f = new File(FileUtil.FILE_PATH);
    protected static Product readFile() throws IOException // @02C
    {
        BufferedReader br = null;
        Product resultProduct;
        try {
            br = new BufferedReader(new FileReader(f));
            String temp = br.readLine();
            String[] data = temp.split(" ");
            resultProduct = new Product(data[0],data[1]);
            System.out.println("产品ID = " + resultProduct.productID + "\n");
            System.out.println("产品描述 = " + resultProduct.productDesc + "\n");

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            br.close();
        }
        return resultProduct;
    }
}
