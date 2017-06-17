package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileUtils
 *
 * @author Chenpz
 * @package com.coderising.ood.srp
 * @date 2017/6/14/22:43
 */
public final class FileUtils {


    public static List<ProductInfo> readProductInfoFromFile(String filePath) throws IOException{

        File file = new File(filePath);
        BufferedReader br = null;
        List<ProductInfo> productInfoList = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(file));
            String temp;
            while((temp = br.readLine()) != null){
                String[] data = temp.split(" ");
                productInfoList.add(new ProductInfo(data[0], data[1]));
            }
            return productInfoList;
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            if (br != null)
                br.close();
        }
    }

}
