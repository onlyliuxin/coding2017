package ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by jimmy on 6/20/2017.
 */
public class Product {
    private String productId;
    private String productDesc;

    public static Product getPromotionProduct(File file) throws Exception {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String temp = br.readLine();
            String[] data = temp.split(" ");

            String productId = data[0];
            String productDesc = data[1];

            System.out.println("产品ID = " + productId + "\n");
            System.out.println("产品描述 = " + productDesc + "\n");

            Product product = new Product();
            product.productId = productId;
            product.productDesc = productDesc;
            return product;
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            br.close();
        }
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
