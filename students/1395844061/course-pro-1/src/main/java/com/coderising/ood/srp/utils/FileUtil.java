package com.coderising.ood.srp.utils;

import com.coderising.ood.srp.ProductInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FileUtils
 *
 * @author Chenpz
 * @package com.coderising.ood.srp
 * @date 2017/6/14/22:43
 */
public final class FileUtil {

    private FileUtil(){
        throw new RuntimeException("illegal called!");
    }

    public static List<ProductInfo> readProductInfoFromFile(String filePath) throws IOException{

        if (!ArgsUtil.isNotNull(filePath))
            throw new IllegalArgumentException("illegal arguments");

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
