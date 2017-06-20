package ood.work.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17 0017.
 */
public class ProductInfoLoader {

    public static List<Product> readFile(String path) throws IOException // @02C
    {
        File file = new File(path);
        List<Product> products = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(" ");
                Product product = new Product();
                product.setProductId(data[0]);
                product.setProductDesc(data[1]);
                products.add(product);
                System.out.println("产品ID = " + product.getProductId() + "\n");
                System.out.println("产品描述 = " + product.getProductDesc() + "\n");
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        return products;
    }
}
