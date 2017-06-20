package com.coderising.ood.srp.service.impl;

import com.coderising.ood.srp.entity.Product;
import com.coderising.ood.srp.service.IReadProductConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Enan on 17/6/18.
 */
public class ReadProductConfigImpl implements IReadProductConfig {
    @Override
    public Collection<Product> read(File file) throws IOException {
        List<Product> products = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String temp;
            while ((temp = br.readLine()) != null) {
                String[] data = temp.split(" ");

                System.out.println("产品ID = " + data[0] + "\n");
                System.out.println("产品描述 = " + data[1] + "\n");

                products.add(Product.builder().productID(data[0]).productDesc(data[1]).build());
            }
            return products;
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            br.close();
        }
    }
}
