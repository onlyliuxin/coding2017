package src;

/**
 * Created by Yang on 2/25/2017.
 */
public interface MyList {
    public void add(Object obj);
    public void add(int index, Object obj);
    public Object get(int index);
    public Object remove(int index);
    public int size();
}
