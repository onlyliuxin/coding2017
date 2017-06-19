package com.coderising.ood.srp.dao;

import com.coderising.ood.srp.util.DBUtil;
import com.coderising.ood.srp.bean.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wang on 2017/6/17.
 */
public class UserInfoDAO {

    private String sendMailQuery;

    private static final String NAME_KEY = "NAME";
    private static final String EMAIL_KEY = "EMAIL";

    private List<UserInfo> infos = new ArrayList<>();

    public UserInfoDAO() {}


    /**
     * 查询用户信息，封装UserInfo对象 然后返回 List<UserInfo>
     * @param productID
     * @return
     */
    public List queryUserInfo(String productID){

        sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + productID +"' "
                + "and send_mail=1 ";


        System.out.println("loadQuery set");
        return getUserInfos();
    }


    private List loadMailingList(){
        return DBUtil.query(sendMailQuery);
    }

    private List getUserInfos(){
        List<HashMap> datas = this.loadMailingList();

        for (HashMap<String,String> users: datas) {
            UserInfo u = new UserInfo();
            u.setName(users.get(NAME_KEY));
            u.setEmail(users.get(EMAIL_KEY));
            infos.add(u);
        }
        return infos;
    }

}
