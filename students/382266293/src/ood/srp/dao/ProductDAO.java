package ood.srp.dao;

import ood.srp.bean.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by onlyLYJ on 2017/6/12.
 */
public class ProductDAO {

    public List<Product> list(File productFile) throws IOException // @02C
    {

        System.out.println(productFile);
        List<Product> products = new ArrayList<>();

        System.out.println("开始导入产品清单");
        try (BufferedReader br = new BufferedReader(new FileReader(productFile))) {
            String temp = null;
            while (null != (temp = br.readLine())) {
                String[] data = temp.split(" ");

                String productID = data[0];
                String productDesc = data[1];

                Product p = new Product();
                p.setProductID(productID);
                p.setProductDesc(productDesc);
                System.out.println("产品ID = " + productID);
                System.out.println("产品描述 = " + productDesc + "\r\n");

                products.add(p);
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }


        return products;
    }


}
