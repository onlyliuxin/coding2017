package ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author jiaxun
 */
public class ProductRepository {

    public static Product getProductFromFile(File file) throws IOException {
        Product product = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String[] data = line.split(" ");

            product = new Product();
            product.setProductID(data[0]);
            product.setProductDesc(data[1]);

            System.out.println("产品ID = " + product.getProductID() + "\n");
            System.out.println("产品描述 = " + product.getProductDesc() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return product;
    }

}
