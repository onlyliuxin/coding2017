package com.coderising.ood.srp;

import com.coderising.ood.srp.DO.ProductDetail;
import com.coderising.ood.srp.DO.UserInfo;
import com.coderising.ood.srp.util.DBUtil;
import com.coderising.ood.srp.util.FileUtil;
import com.coderising.ood.srp.util.MailUtil;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * 程序入口点。
 * @since 06.19.2017
 */
public class PromotionMail {

    public static void main(String[] args){
        final String FILE_PATH = "test.txt";
        FileUtil fileUtil;

        //尝试打开促销文件
        try {
            fileUtil = new FileUtil(FILE_PATH);
        } catch (FileNotFoundException e){
            System.out.println("促销文件打开失败，请确认文件路径！");
            return;
        }

        sendAllEMails(fileUtil);

        fileUtil.close();
    }


    private static void sendAllEMails(FileUtil fileUtil){
        while (fileUtil.hasNext()) {
            ProductDetail productDetail = fileUtil.getNextProduct();
            List<UserInfo> usersList = DBUtil.query(productDetail);
            MailUtil.sendEmails(usersList);
        }
    }
}
