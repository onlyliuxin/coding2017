package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by James on 6/16/2017.
 */
public class Product {


    private String productID = null;
    private String productDesc = null;

    public Product(String id, String desc) {
        this.productID = id;
        this.productDesc = desc;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public static Product buildProduct(File file) throws IOException {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String temp = br.readLine();
            String[] data = temp.split(" ");

            return new Product(data[0], data[1]);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            br.close();
        }

    }
}
