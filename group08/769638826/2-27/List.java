package com.coding.basic;

/**
 * Created by huitailang on 17/2/25.
 *
 * @author zhangkun
 * @date 2017年02月25日13:54:33
 */
public interface List {
    public void add(Object o);

    public void add(int index, Object o);

    public Object get(int index);

    public Object remove(int index);

    public int size();
}
