package com.coderising.array;

/**
 * Created by hushuai on 2017/2/27.
 */
public class TestMain {

    public static void main(String[] args) {
        ArrayUtil arrayUtil = new ArrayUtil();
        int [] a4 = arrayUtil.getPerfectNumbers(1000);
        for(int i=0;i<a4.length;i++)
        {
            System.out.println(a4[i]);
        }
    }
}
