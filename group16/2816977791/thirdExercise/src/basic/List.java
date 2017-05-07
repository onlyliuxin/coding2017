package basic;

/**
 * @author nvarchar
 *         date 2017/3/27
 */
public interface List {
    void add(Object o);

    void add(int index, Object o);

    Object get(int index);

    Object remove(int index);

    int size();
}
