package ood.srp1.server;

import ood.srp1.entity.Product;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *产品服务
 * Created by lx on 2017/6/17.
 */
public class ProductServer {
    private static List<Product> pList = new ArrayList<>();
    static {
        try {
            initSpecialProductList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成优惠产品信息
     * @return
     * @throws java.io.IOException
     */
    private static void initSpecialProductList() throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/main/java/ood/srp1/product_promotion.txt";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String pInfo;
            while ((pInfo  = br.readLine()) != null) {
                String[] data = pInfo.split(" ");
                pList.add(new Product(data[0], data[1]));
            }
        } catch (IOException e) {
            throw new IOException( "读取文件内容失败 " + e.getMessage());
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    /**
     * 获取用户的产品
     * @return
     */
    public static List<Product> getUserProduct() {
        return pList;
    }
}
