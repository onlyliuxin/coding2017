package base;
public interface List {
    public void add(Object obj);
    public int size();
    public Object get(int index);
    public Object remove(int index);
    public void add(int index, Object obj);
}
