package com.coderising.ood.srp.utils;

import java.util.List;

/**
 * ArgsUtil
 *
 * @author Chenpz
 * @package com.coderising.ood.srp.utils
 * @date 2017/6/17/11:56
 */
public final class ArgsUtil {

    private ArgsUtil(){
        throw new RuntimeException("illegal called!");
    }

    public static boolean isNotNull(Object object){

        return object != null;
    }

    public static boolean isNotEmpty(List list){

        return isNotNull(list) && !list.isEmpty();
    }

}
