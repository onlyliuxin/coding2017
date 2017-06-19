package com.coding2017.practice13;

import java.util.ArrayList;
import java.util.List;

// 促销订阅人职责类
public class PromotionSubscriber {

    /** 订阅人邮件地址 */
    private String toAddress;
    /** 订阅人姓名 */
    private String subscriber;

    public String getToAddress() {
        return toAddress;
    }

    public String getSubscriber() {
        return subscriber;
    }

    /**
     * 查询促销产品订阅人列表
     *
     * @param productId 产品ID
     * @return 订阅人列表
     */
    public static List<PromotionSubscriber> querySubscribers(String productId) {
        String sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + productId + "' "
                + "and send_mail=1 ";
        System.out.println("loadQuery set");
        return new ArrayList<PromotionSubscriber>();
    }
}
