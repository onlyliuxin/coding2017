package yangsongbao.persistence;

import yangsongbao.utils.FileUtils;
import yangsongbao.model.ProductInfo;

import java.io.File;
import java.util.List;

/**
 *
 * Created by songbao.yang on 2017/6/17.
 */
public class ProductManager {

    public static List<ProductInfo> getPromotionPruductFromFile(String path){

        File file = new File(path);
        List<ProductInfo> productInfos = FileUtils.readFile(file);
        return productInfos;
    }

}
