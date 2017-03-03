package org.apn.coding2017.basic;


/**
 * Created by QiPan on 2017/2/23.
 */
public interface List {

    boolean add(Object o);

    boolean add(int index, Object o);

    Object set(int index, Object element);

    Object get(int index);

    Object remove(int index);

    int size();

    boolean isEmpty();

    Iterator iterator();
}
