package djj;

/**
 * Created by jerry on 2017/2/26.
 */
public interface List {
    public void add(Object o);
    public void add(int index, Object o);
    public Object get(int index);
    public Object remove(int index);
    public int size();
}
