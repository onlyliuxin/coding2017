package com.ifengdzh.code2017.basic;

/**
 * Created by ajaxfeng on 2017/3/14.
 */
public class ListIterator implements Iterator {

    private List dataList;
    private int index = 0;

    public ListIterator(List dataList) {
        this.dataList = dataList;
    }

    @Override
    public boolean hasNext() {
        return index < dataList.size() - 1;
    }

    @Override
    public Object next() {
        Object o = dataList.get(index);
        index++;
        return o;
    }
}
