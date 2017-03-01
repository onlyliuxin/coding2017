package 基本数据结构;

/**
 * Created by LIANG on 2017/2/24.
 */
public interface MyList<E>
{

    public void add(int index, E e);

    public E get(int index);

    public E remove(int index);

    public int size();

    public void clear();


    public int indexOf(E e);

    public boolean isEmpty();

    public E set(int index, E e);

    public boolean contains(E e);
}
