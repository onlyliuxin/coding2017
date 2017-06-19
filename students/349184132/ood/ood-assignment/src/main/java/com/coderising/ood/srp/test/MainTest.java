package com.coderising.ood.srp.test;

import com.coderising.ood.srp.*;
import com.coderising.ood.srp.bean.ProductInfo;
import com.coderising.ood.srp.bean.UserInfo;
import com.coderising.ood.srp.dao.ProductInfoDAO;
import com.coderising.ood.srp.dao.UserInfoDAO;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by wang on 2017/6/17.
 */
public class MainTest {

    @Test
    public void testReadFile() {
        File f = new File("C:\\Users\\wang\\Documents\\ood\\coding2017\\students\\349184132\\ood\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt");
        ProductInfoDAO pDAO = new ProductInfoDAO();
        pDAO.readFile(f);
        Assert.assertEquals("P8756", pDAO.getProductList().get(0).getProductID());
        Assert.assertEquals("iPhone8", pDAO.getProductList().get(0).getProductDesc());

        Assert.assertEquals("P4955", pDAO.getProductList().get(3).getProductID());
    }

    @Test
    public void testUserInfo(){
        File f = new File("C:\\Users\\wang\\Documents\\ood\\coding2017\\students\\349184132\\ood\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt");
        ProductInfoDAO pDAO = new ProductInfoDAO();
        pDAO.readFile(f);

        List<ProductInfo> pInfo = pDAO.getProductList();

        UserInfoDAO uDAO = new UserInfoDAO();
        List<UserInfo> userInfos = uDAO.queryUserInfo(pInfo.get(0).getProductID());
        Assert.assertEquals("User1",userInfos.get(0).getName());

    }

    @Test
    public void testSendMail(){
        File f = new File("C:\\Users\\wang\\Documents\\ood\\coding2017\\students\\349184132\\ood\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt");
        ProductInfoDAO pDAO = new ProductInfoDAO();
        pDAO.readFile(f);

        List<ProductInfo> pInfo = pDAO.getProductList();

        UserInfoDAO uDAO = new UserInfoDAO();
        List<UserInfo> userInfos = uDAO.queryUserInfo(pInfo.get(0).getProductID());

        MailContent mCont = new MailContent();
        PromotionMail pMail = new PromotionMail(pInfo,userInfos,mCont);
        pMail.sendEMail();
    }

}
