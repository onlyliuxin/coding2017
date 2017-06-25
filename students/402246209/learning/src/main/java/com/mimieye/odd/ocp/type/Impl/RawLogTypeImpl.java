package com.mimieye.odd.ocp.type.Impl;

import com.mimieye.odd.ocp.config.LoggerConstant;
import com.mimieye.odd.ocp.type.TypeInterface;
import com.mimieye.odd.ocp.util.DateUtil;

/**
 * Created by Pierreluo on 2017/6/20.
 */
public class RawLogTypeImpl implements TypeInterface {

    @Override
    public String getMsg(String msg) {
        return msg;
    }
}
