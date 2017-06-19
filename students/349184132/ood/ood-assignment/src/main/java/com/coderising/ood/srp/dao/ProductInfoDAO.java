package com.coderising.ood.srp.dao;

import com.coderising.ood.srp.bean.ProductInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**产品信息数据访问类
 * Created by wang on 2017/6/17.
 */
public class ProductInfoDAO {

    private List<ProductInfo> productList = new ArrayList<>();


    public void readFile(File file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String newLine = br.readLine();

            while(newLine!=null && newLine!=" ") {
                String temp = newLine;
                String[] datas = temp.split(" ");

                addProductInfo(datas);
                newLine = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addProductInfo(String[] datas) {

        ProductInfo product = new ProductInfo();
        product.setProductID(datas[0]);
        product.setProductDesc(datas[1]);
        this.productList.add(product);
    }

    public List<ProductInfo> getProductList(){
        return this.productList;
    }
}
