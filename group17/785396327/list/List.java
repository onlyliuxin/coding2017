package list;

/**
 * Created by william on 2017/2/25.
 */
public interface List<T> {

    int size();

    boolean isEmpty();

    boolean contains(T ele);

    boolean add(T ele);

    boolean add(int index, T ele);

    boolean remove(T ele);

    T remove(int index);

    T get(int index);

    int indexOf(T ele);
}
