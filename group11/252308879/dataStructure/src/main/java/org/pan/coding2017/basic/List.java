package org.pan.coding2017.basic;


/**
 * Created by QiPan on 2017/2/23.
 */
public interface List {

    void add(Object o);
    void add(int index, Object o);
    Object get(int index);
    Object remove(int index);
    int size();
}
