package com.coding2017.practice13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 促销产品职责类
public class PromotionProduct {

    /** 产品ID */
    private String productId;
    /** 产品描述 */
    private String productDesc;

    public String getProductId() {
        return productId;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public static List<PromotionProduct> readFromFile(String filepath) throws IOException {
        List<PromotionProduct> products = new ArrayList<PromotionProduct>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(filepath)));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(" ");
                PromotionProduct product = new PromotionProduct();
                product.productId = data[0];
                product.productDesc = data[1];
                products.add(product);
                System.out.println("产品ID = " + product.getProductId() + "\n");
                System.out.println("产品描述 = " + product.getProductDesc() + "\n");
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return products;
    }


}
