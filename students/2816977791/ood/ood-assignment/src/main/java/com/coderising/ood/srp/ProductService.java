package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nvarchar
 *         date 2017/6/29
 */
public class ProductService {

    public List<Product> getProductLists(String filePath) throws IOException {
        List<Product> products = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String temp;
            while ((temp = br.readLine()) != null) {
                String[] data = temp.split(" ");
                products.add(new Product(data[0], data[1]));
            }
            return products;
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            br.close();
        }
    }
}
