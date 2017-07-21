package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by szf on 6/20/17.
 */
public class ProductFactory {
    List getNewProdcuts(File file) throws IOException {
            BufferedReader br = null;
            List newProducts = new ArrayList<>();
            try {
                br = new BufferedReader(new FileReader(file));
                String temp = br.readLine();
                String[] data = temp.split(" ");

                Product product = new Product(data[0], data[1]);
                newProducts.add(product);

                System.out.println("产品ID = " + product.getProductId() + "\n");
                System.out.println("产品描述 = " + product.getProductDesc() + "\n");

            } catch (IOException e) {
                throw new IOException(e.getMessage());
            } finally {
                br.close();
            }
            return newProducts;
    }

}
