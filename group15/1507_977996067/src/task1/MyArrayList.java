package task1;

import java.util.Arrays;
import java.util.Iterator;

/**
 * ArrayList实现
 */
public class MyArrayList<T> implements MyList<T> {

    //列表元素的个数
    private int size;
    //列表存放的元素
    private Object[] elements;
    //初始容量10
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        elements = new Object[capacity <= DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity];
    }

    @Override
    public void add(T o) {
        add(size, o);
    }

    @Override
    public void add(int index, T o) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index " + index + " 不合法");
        if (size >= elements.length)
            ensureCapacity((size >> 1) + size);
        elements[index] = o;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index " + index + " 越界");
        return (T) elements[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index " + index + " 越界");
        T removeElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, elements.length - index - 1);
        size--;
        return removeElement;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new MyItr();
    }

    private void ensureCapacity(int newCapacity) {
        elements = Arrays.copyOf(elements, newCapacity);
    }

    private class MyItr implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            return (T) elements[current++];
        }

        @Override
        public void remove() {
            if (current == 0)
                throw new IllegalStateException("先调用next后才能再调用remove");
            MyArrayList.this.remove(--current);
        }
    }
}
