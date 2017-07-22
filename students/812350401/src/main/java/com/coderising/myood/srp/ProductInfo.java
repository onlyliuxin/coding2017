package com.coderising.myood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by thomas_young on 20/6/2017.
 */
public class ProductInfo {
    private String productID = null;
    private String productDesc = null;

    private static File f = new File("/Users/thomas_young/Documents/code/liuxintraining/coding2017/students/812350401/src/main/java/com/coderising/myood/srp/product_promotion.txt");

    public ProductInfo() {
        try {
            readFileSetProperty();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
     * @throws IOException
     */
    private void readFileSetProperty() throws IOException
    {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));
            String temp = br.readLine();
            String[] data = temp.split(" ");

            setProductID(data[0]);
            setProductDesc(data[1]);

            System.out.println("产品ID = " + productID + "\n");
            System.out.println("产品描述 = " + productDesc + "\n");

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            br.close();
        }
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
