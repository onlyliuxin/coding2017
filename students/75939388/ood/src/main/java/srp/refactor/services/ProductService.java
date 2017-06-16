package srp.refactor.services;

import org.apache.commons.lang3.StringUtils;
import srp.refactor.domain.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tee on 2017/6/16.
 */
public class ProductService {

    public Product setPromotionInfo(String productId, String productDesc){
        return new Product(productId, productDesc);
    }

    public String getLoadQuerySql(String productID){
        if(StringUtils.isBlank(productID)){
            throw new RuntimeException("没有获取到productID");
        }

        String sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + productID +"' "
                + "and send_mail=1 ";

        System.out.println("loadQuery set, productID -> " + productID);
        return sendMailQuery;
    }

    public List<Product> getPromotionInfoList(List<String> data){
        List<Product> list = new ArrayList<>();
        for(int i = 0; i < data.size(); i += 2){
            list.add(setPromotionInfo(data.get(i), data.get(i + 1)));
        }
        return list;
    }
}
