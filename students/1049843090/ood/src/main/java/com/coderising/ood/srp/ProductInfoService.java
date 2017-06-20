package com.coderising.ood.srp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangdd
 */
public class ProductInfoService {

    public List<ProductInfo> getPromotionProducts() {
        List<ProductInfo> list = new ArrayList<>();
        //可以抽取出一个读取文件的Util
        BufferedReader br = null;
        try {
            String path = "/Users/yangdd/Documents/code/GitHub/coding2017-Q2/students/1049843090/ood/src/main/java/com/coderising/ood/srp/product_promotion.txt";
            File file = new File(path);
            br = new BufferedReader(new FileReader(file));
            String temp = null;
            String[] data = null;
            while ((temp = br.readLine()) != null) {
                data = temp.split(" ");
                ProductInfo productInfo = new ProductInfo();
                productInfo.setId(data[0]);
                productInfo.setDescription(data[1]);
                list.add(productInfo);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

}
