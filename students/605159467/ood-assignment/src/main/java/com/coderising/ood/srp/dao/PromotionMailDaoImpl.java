package com.coderising.ood.srp.dao;

import com.coderising.ood.srp.bean.Person;
import com.coderising.ood.srp.utils.DBUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IDEA
 * Created by fuyi.ren on 2017/6/17  14:48
 * Description:
 */
public class PromotionMailDaoImpl implements PromotionMailDao
{
    public List loadMailingList() throws Exception
    {
        List userList = new ArrayList<Person>();
        for (int i = 1; i <= 3; i++) {
            Person person=new Person();
            person.setId(Long.valueOf(i));
            person.setName("User" + i);
            person.setEmail("aa@bb.com");
            userList.add(person);
        }
        return userList;
    }
}
