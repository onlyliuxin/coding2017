package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author chengyu
 * @version 17/6/20
 */
public class Product {
    protected String productID = null;
    protected String productDesc = null;

    public Product() {
    }

    public Product(String productID, String productDesc) {
        this.productID = productID;
        this.productDesc = productDesc;
    }

    public static Product of(File file) throws IOException {
        BufferedReader br;
        br = new BufferedReader(new FileReader(file));
        String temp = br.readLine();
        String[] data = temp.split(" ");
        System.out.println("产品ID = " + data[0] + "\n");
        System.out.println("产品描述 = " + data[1] + "\n");
        return new Product(data[0], data[1]);
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
}
