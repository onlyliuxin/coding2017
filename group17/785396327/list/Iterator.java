package list;

/**
 * Created by IBM on 2017/2/25.
 */
public interface Iterator<T> {

    boolean hasNext();

    T next();

    void remove();
}
