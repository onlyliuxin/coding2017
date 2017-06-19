package yangsongbao.utils;

import yangsongbao.model.ProductInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by songbao.yang on 2017/6/17.
 */
public class FileUtils {

    public static List<ProductInfo> readFile(File file){
        BufferedReader br = null;
        List<ProductInfo> productInfos = new ArrayList<ProductInfo>();
        try {
            br = new BufferedReader(new FileReader(file));
            String temp;
            while ((temp = br.readLine()) != null){
                String[] data = temp.split(" ");
                if (data.length == 2){
                    ProductInfo productInfo = new ProductInfo(data[0], data[1]);
                    productInfos.add(productInfo);
                }
            }
            return productInfos;
        } catch (IOException e) {
            System.out.println("读取文件异常，" + e.getMessage());
            return productInfos;
        } finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
