package basic.linkedlist;

/**
 * 自己定义的List接口
 * Created by zhouliang on 2017-03-10.
 */
interface List<E> {
    void add(E e);
    void add(int index, E e);
    E get(int index);
    E remove(int index);
    int size();
}
