package 基本数据结构;


/**
 * Created by LIANG on 2017/2/24.
 */
public class MyArrayList<E> implements MyList<E>
{
    public static final int INITIAL_CAPACITTY = 16;
    private E[] data = (E[]) new Object[INITIAL_CAPACITTY];
    private static int size = 0;

    public MyArrayList(E[] objects)
    {

        for (int i = 0; i < objects.length; i++)
        {
            add(objects[i]);
            size++;
        }
    }

    public MyArrayList() {}

    ;

    public void ensureCapicity()
    {
        if (size >= data.length)
        {
            E[] newData = (E[]) new Object[size * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }

//    public boolean add(E e)
//    {
//        ensureCapicity();
//        if(e != null)
//            data[size++] = e;
//        return true;
//    }

    public void add(E e)
    {
        add(size, e);
    }

    @Override
    public void add(int index, E e)
    {
        ensureCapicity();
        for (int i = size - 1; i >= index; i--)
        {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    @Override
    public E get(int index)
    {
        return data[index];
    }

    @Override
    public E remove(int index)
    {
        checkIndex(index);
        E e = data[index];
        for (int i = index; i < data.length - 1; i++)
            data[i] = data[i + 1];

        data[size - 1] = null;
        size--;
        return e;

    }

    private void checkIndex(int index)
    {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("输入下标有误");
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void clear()
    {
        data = (E[]) new Object[INITIAL_CAPACITTY];
        size = 0;
    }

    @Override
    public int indexOf(E e)
    {
        for (int i = 0; i < size; i++)
        {
            if (e.equals(data[i]))
                return i;
        }
        return -1;
    }


    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public E set(int index, E e)
    {
        checkIndex(index);
        E temp = data[index];
        data[index] = e;
        return temp;
    }

    @Override
    public boolean contains(E e)
    {
        for (int i = 0; i < size; i++)
        {
            if(data[i].equals(e))
                return true;
        }
        return false;
    }

    public void trimToSize()
    {
        if (size != data.length)
        {
            E[] newData = (E[]) new Object[size];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < size; i++)
        {
            result.append(data[i]);
            if (i < size - 1)
                result.append(",");
        }
        return result.toString() + "]";
    }

    private class ArrayListIterator implements Iterator
    {
        private int current = 0;

        @Override
        public boolean hasNext()
        {
            return (current < size);
        }

        @Override
        public E next()
        {
            return data[current++];
        }

        @Override
        public void remove()
        {
            MyArrayList.this.remove(current);
        }
    }

}
