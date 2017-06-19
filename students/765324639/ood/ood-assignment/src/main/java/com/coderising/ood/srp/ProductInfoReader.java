package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoReader {

    protected static List<Product> readProductInfo() {
        File file = new File("E:\\coding2017\\students\\765324639\\ood\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt");
        List<Product> productList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file));) {
            String temp = null;
            while ((temp = br.readLine()) != null) {
                String[] data = temp.split(" ");
                Product product = new Product();
                product.setId(data[0]);
                product.setDesc(data[1]);
                productList.add(product);
                System.out.println("产品ID = " + data[0]);
                System.out.println("产品描述 = " + data[1] + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
