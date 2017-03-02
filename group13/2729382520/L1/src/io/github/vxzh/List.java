package io.github.vxzh;

/**
 * Created by vxzh on 22/02/2017.
 */
public interface List {

    int size();

    boolean isEmpty();

    boolean add(Object o);

    boolean add(int index, Object o);

    boolean remove(Object o);

    boolean remove(int index);

    Object get(int index);

}
