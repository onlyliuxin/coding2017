package xyy.baselinked;

/**
 * Created by 14258 on 2017/3/14.
 */
public interface List {

    public void add(Object o);

    public void add(int index, Object o);

    public Object remove(int index);

    public int size();

    public Object get(int index);
}
