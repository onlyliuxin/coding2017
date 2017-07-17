package com.coderising.ood.atmSimulation.serialization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author nvarchar
 *         date 2017/7/16
 */
public class JsonConvert {

    public static String encode(NetPackage np) {
        return JSON.toJSONString(np);
    }

    public static NetPackage decode(String code) {
        return JSONObject.parseObject(code, NetPackage.class);
    }
}
