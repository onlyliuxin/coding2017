package me.lzb.basic.list;

/**
 * list接口
 *
 * @author LZB
 */
public interface List {
    void add(Object o);

    void add(int index, Object o);

    Object get(int index);

    Object remove(int index);

    int size();
}
