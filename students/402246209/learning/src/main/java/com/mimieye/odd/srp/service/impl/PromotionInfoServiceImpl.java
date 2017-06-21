package com.mimieye.odd.srp.service.impl;

import com.mimieye.odd.srp.service.PromotionInfoService;
import com.mimieye.odd.srp.util.FileReadUtil;

import java.io.IOException;
import java.util.*;

/**
 * Created by Pierreluo on 2017/6/15.
 */
public class PromotionInfoServiceImpl implements PromotionInfoService {

    @Override
    public List<Map<String, String>> listPromotions(String filePath) throws Exception {
        List<Map<String,String>> list = null;
        List<String> results = FileReadUtil.readFile(filePath);
        if(results != null && results.size()>0){
            list = new ArrayList<>();
            String temp = null;
            String[] data = null;
            Map<String,String> map = null;
            Iterator<String> iterator = results.iterator();
            int i=1;
            while(iterator.hasNext()){
                temp = iterator.next();
                data = temp.split(" ");
                map = new HashMap<>();
                map.put("productID",data[0]);
                map.put("productDesc",data[1]);
                list.add(map);
                System.out.println("产品"+(i)+"ID = " + data[0] );
                System.out.println("产品"+(i++)+"描述 = " + data[1] + "\n");
            }
        }else{
            throw new IOException("No Records.");
        }
        return list;
    }

}
