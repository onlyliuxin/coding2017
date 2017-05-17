package datastructure.basic;

public interface List {
    void add(Object o);

    void add(int index, Object o);

    Object get(int index);

    Object remove(int index);

    int indexOf(Object o);

    int size();

    boolean isEmpty();

    Iterator iterator();
}
