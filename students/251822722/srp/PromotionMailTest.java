package com.coderising.ood.srp;

import com.coderising.ood.srp.product.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PromotionMailTest {


    public static void main(String[] args) throws Exception {


        //load change info
        File file = new File("C:\\coderising\\workspace_ds\\ood-example\\src\\product_promotion.txt");
        List<Product> products = getUpdateProduct(file);


        //update message for user
        products.stream().forEach(p -> {
            p.notifyUserPriceChange();
        });


    }


    protected static List<Product> getUpdateProduct(File file) throws IOException // @02C
    {

        List<Product> productMap = new ArrayList<Product>();


        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));


            String temp = br.readLine();
            String[] data = temp.split(" ");

            Product product = DBUtil.queryProduct(data[0], data[1]);
            productMap.add(product);

            System.out.println("产品ID = " + data[0] + "\n");
            System.out.println("产品描述 = " + data[1] + "\n");

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            br.close();
        }

        return productMap;
    }


}
