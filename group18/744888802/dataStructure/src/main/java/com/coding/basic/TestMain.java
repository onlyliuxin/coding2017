package com.coding.basic;

import com.coding.domain.UserInfo;

/**
 * Created by hushuai on 2017/2/20.
 */
public class TestMain {

    public static void main(String[] args) {


        ArrayList arrayList = new ArrayList();
        for(int i=0;i<5;i++)
        {
            arrayList.add(new UserInfo("name-"+i));
        }


        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()){
            UserInfo userInfo = (UserInfo)iterator.next();
            System.out.println(userInfo.getName());

        }

    }

}
