package Collection ;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
   Created by william on 2017/2/25.
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0)
            throw new RuntimeException("�Ƿ���ʼ����С����!");
        elementData = new Object[initialCapacity];
    }

    public boolean add(T ele) {
        grow();
        elementData[size] = ele;
        size++;
        return true;
    }

    public T get(int index) {
        checkBounds(index);
        return (T) elementData[index];
    }

    public T remove(int index) {
        checkBounds(index);
        T removeEle = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return removeEle;
    }

    public boolean add(int index, T ele) {
        checkBounds(index);
        size++;//��ЧԪ�������ȼӣ���֤���ȼ������grow�����ǰ��Ч
        grow();
        //��ԭ������Ӵ������index��ȡ����ԭ��index�����Чֵ�����Ƶ�ԭ������index+1֮��
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = ele;
        return true;
    }

    @Override
    public boolean remove(T ele) {
        int index;
        if ((index = indexOf(ele)) == -1)
            return false;
        remove(index);
        return true;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index : " + index + ", size : [ 0 - " + size + " ]");
    }

    public int size() {
        return size;
    }

    private void grow() {
        if (size >= elementData.length) {
            int curLen = elementData.length;
            int newLen = curLen + (curLen >> 1) > Integer.MAX_VALUE ? Integer.MAX_VALUE : curLen + (curLen >> 1);
            elementData = Arrays.copyOf(elementData, newLen);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T ele) {
        return indexOf(ele) != -1;
    }

    public int indexOf(T ele) {
        for (int i = 0; i < size; i++) {
            if (ele == null)
                if (null == elementData[i])
                    return i;
                else if (ele.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    public Iterator<T> iterator() {
        return new Itr<T>();
    }

    private class Itr<T> implements Iterator<T> {
        int cursor;//������Ԫ�ص��±�

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            if (cursor >= size)
                throw new NoSuchElementException();
            return (T) elementData[cursor++];
        }

        @Override
        public void remove() {
            if (cursor >= size)
                throw new NoSuchElementException();
            ArrayList.this.remove(cursor--);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (Object ele : elementData) {
            sb.append(ele).append(" ");
        }
        return sb.append("]").toString();
    }
}