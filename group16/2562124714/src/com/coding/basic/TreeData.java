package com.coding.basic;

/**
 * Created by zhangwj on 2017/2/22.
 */
public class TreeData<T extends  Comparable<T>>  implements  Comparable<TreeData<T>>{
    private int s;
    private T t;

    public T getT()
    {
        return t;
    }

    public void setT(T o) { t = o;}

    @Override
    public int compareTo(TreeData<T> o) {
        return getT().compareTo(o.getT());
    }

//    public int compareTo(TreeData<T> o)
//    {
//
//    }


}
