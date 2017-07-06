package com.coderising.ood.service;

import com.coderising.ood.pojo.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xyy
 * @create 2017-06-19 9:34
 **/
public class ProductService {


    public static List<Product> getAllProductFromFile(File file) throws IOException {
        List productList = new ArrayList();
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(file));
            String temp = null;
            while ((temp = br.readLine()) != null) {
                String[] data = temp.split(" ");
                Product product = new Product();
                product.setProductID(data[0]);
                product.setProductDesc(data[1]);
                System.out.println("产品ID = " + product.getProductID() + "\n");
                System.out.println("产品描述 = " + product.getProductDesc() + "\n");
                productList.add(product);
            }

            return productList;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

}
