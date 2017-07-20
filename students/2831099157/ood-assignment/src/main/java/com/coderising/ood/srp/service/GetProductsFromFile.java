package com.coderising.ood.srp.service;

import com.coderising.ood.srp.interfaces.GetProductsFunction;
import com.coderising.ood.srp.model.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iden on 2017/6/14.
 */
public class GetProductsFromFile implements GetProductsFunction {
    public String filePath = "E:\\StudyProjects\\Java\\Workspace\\HomeWork\\coding2017\\liuxin\\ood\\ood-assignment\\" +
            "src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt";

    @Override
    public List<Product> getProducts() {
        BufferedReader br = null;
        List<Product> products = new ArrayList<>();
        try {
            File file = new File(filePath);
            br = new BufferedReader(new FileReader(file));
            String temp = null;
            while ((temp = br.readLine()) != null) {
                String[] data = temp.split(" ");
                Product product = new Product(data[0], data[1]);
                System.out.println("促销产品ID = " + product.getId());
                System.out.println("促销产品描述 = " + product.getDescription());
                products.add(product);
            }

        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return products;
    }
}
