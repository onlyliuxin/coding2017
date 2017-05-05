package basic.dataStructure;

/**
 * Created by macvi on 2017/4/2.
 */
public interface List {
    void add(Object o);
    void add(int index, Object o);
    Object get(int index);
    Object remove(int index);
    int size();
}
