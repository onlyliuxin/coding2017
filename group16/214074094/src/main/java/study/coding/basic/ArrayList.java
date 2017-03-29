package study.coding.basic;


import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @Author shane
 * @Time 2017/2/25 13:06
 * @Email stevenchenguang@gmail.com
 * @Desc OwnArrayList
 */
public class ArrayList<E> implements List<E> {

    private int size = 0;

    private final static Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 默认容量
     */
    private static int DEFAULT_CAPACITY = 10;

    private Object[] elementData;

    public ArrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }

    @Override
    public void add(E e) {
        if (elementData == EMPTY_ELEMENTDATA) {
            elementData = Arrays.copyOf(elementData, DEFAULT_CAPACITY);
            elementData[0] = e;
        } else if (size < elementData.length) {
            elementData[size] = e;
        } else {
            _grow();
            elementData[size] = e;
        }
        size++;
        _analyze();
    }

    @Override
    public void add(int index, E e) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        }
        if (elementData == EMPTY_ELEMENTDATA) {
            if (index != 0) {
                throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
            } else {
                elementData = new Object[DEFAULT_CAPACITY];
                elementData[0] = e;
            }
        } else if (index > size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        } else if (index == size) {
            _grow();
            elementData[size] = e;
            size++;
        } else {
            if (elementData.length == size) {
                _grow();
            }
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = e;
            size++;
        }
        _analyze();
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        }
        return (E) elementData[index];
    }

    @Override
    public E remove(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        }
        E oldValue = (E) elementData[index];
        //需要复制的长度
        int needMoveLength = size - index - 1;
        //如果该长度小于0,　说明只有一个元素,　直接置空即可
        if (needMoveLength > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, needMoveLength);
        }
        elementData[--size] = null;
        _analyze();
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] toArray() {
        return (E[]) elementData;
    }

    public Iterator iterator() {
        return new ArrayListItrator();
    }

    /**
     * @Author: shane
     * @Time: 2017/2/25 20:18
     * @Email: stevenchenguang@gmail.com
     * @param:
     * @Return:
     * @Throw:
     * @Desc: 返回真实长度的数组数据
     */
    private void _analyze() {
        if (size < elementData.length) {
            elementData = Arrays.copyOf(elementData, size);
        }
    }

    /**
     * @Author: shane
     * @Time: 2017/2/25 20:19
     * @Email: stevenchenguang@gmail.com
     * @param:
     * @Return:
     * @Throw:
     * @Desc: 将数组的长度扩容至2倍
     */
    private void _grow() {
        elementData = Arrays.copyOf(elementData, elementData.length << 1);
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class ArrayListItrator implements Iterator {

        private int position = 0;

        @Override
        public boolean hasNext() {
            return position != size;
        }

        @Override
        public Object next() {
            int i = position;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            position = i + 1;
            return elementData[i];
        }
    }
}
