package main.coding_170309;

/**
 * Created by peter on 2017/3/10.
 */
public interface List {
    public void add(Object o);
    public void add(Object o ,int index);
    public Object get(int index);
    public Object remove(int index);
    public int getSize();
}
