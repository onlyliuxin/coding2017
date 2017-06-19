package com.coderising.ood.srp.bean;

import com.coderising.ood.srp.utils.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String productID;
    private String productDesc;

    public static List<Product> getProductByFile(String f) throws IOException {
        List<Product> list = new ArrayList<Product>();
        File file = new File(f);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String s = "";
            while (!StringUtils.isBlank((s=br.readLine()))) {
                String[] data = s.split(" ");
                Product p = new Product(data[0], data[1]);
                System.out.println("产品ID = " + data[0] + "\n");
                System.out.println("产品描述 = " + data[1] + "\n");
                list.add(p);
            }

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return list;
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

    public Product() {

    }

    public Product(String productID, String productDesc) {

        this.productID = productID;
        this.productDesc = productDesc;
    }
}
