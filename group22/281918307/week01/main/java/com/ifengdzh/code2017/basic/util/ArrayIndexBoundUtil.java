package com.ifengdzh.code2017.basic.util;

import com.ifengdzh.code2017.basic.exception.ArrayIndexOutOfBoundException;

/**
 * 数组判断
 * Created by ajaxfeng on 2017/4/4.
 */
public class ArrayIndexBoundUtil {

    /**
     * 检查访问下标
     * @param index
     * @param maxIndex
     */
    public static void checkIndex(int index,int maxIndex){
        if(index<0 || index>maxIndex){
            throw new ArrayIndexOutOfBoundException("index out of bound ...");
        }
    }
}
