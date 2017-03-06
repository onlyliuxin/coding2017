package task1;

/**
 * List 接口
 */
public interface MyList<T> {
    public void add(T o);

    public void add(int index, T o);

    public T get(int index);

    public T remove(int index);

    public int size();
}
