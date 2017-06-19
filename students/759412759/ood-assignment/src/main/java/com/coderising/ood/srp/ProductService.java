package com.coderising.ood.srp;

import java.io.*;

/**
 * Created by Tudou on 2017/6/16.
 */
public class ProductService {

    public static Product loadProductFromFile(String filePath) throws IOException {
        Product product = new Product();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(filePath)));
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
        return product;
    }

}
