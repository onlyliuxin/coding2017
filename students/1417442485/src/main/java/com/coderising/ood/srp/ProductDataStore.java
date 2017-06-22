package main.java.com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;

public class ProductDataStore {

    private final File mFileDataSource;

    public ProductDataStore(final File file) {
        mFileDataSource = file;
    }

    public Product getProduct() {
        //读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
        String[] data = null;
        try {
            data = FileUtil.readFile(mFileDataSource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = null;
        if(data != null && data.length >= 2) {
            product = new Product(data[0], data[1]);
            System.out.println("产品ID = " + product.productId + "\n");
            System.out.println("产品描述 = " + product.productDesc + "\n");
        }
        return product;
    }
}
