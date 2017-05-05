package com.coderising.litestruts.util;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by huitailang on 17/3/5.
 * @author zhangkun
 * @date 2017年03月05日17:45:06
 * array操作工具类
 */
public final class ArrayUtil<T> {
    @SuppressWarnings("unchecked")
    public static <T> T[] convertCollectionToArray(Collection<T> collection){
        Iterator<T> iterator = collection.iterator();
        Object[] array = new Object[collection.size()];
        int i = 0;

        while (iterator.hasNext()){
            T value = iterator.next();
            array[i++] = (Object)value;
        }

        return (T[])array;
    }
}
