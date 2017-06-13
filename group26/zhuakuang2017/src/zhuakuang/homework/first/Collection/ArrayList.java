package zhuakuang.homework.first.Collection;

import java.util.Arrays;

public class ArrayList implements List, Iterable {

    private int size = 0;

    private Object[] elementData = new Object[10];

    public void add(Object o) {
        ensureCapacity(size + 1);
        elementData[size] = o;
        size++;
    }

    /**
     * 插入
     *
     * @param index
     * @param o
     */
    public void add(int index, Object o) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("index" + index + "越界");
        }
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = o;
        size++;
    }

    public Object get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    public Object remove(int index) {
        checkIndex(index);
        Object oldValue = elementData[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        }
        elementData[--size] = null;// GC
        return oldValue;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("index" + index + "越界");
        }
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return new Itr();
    }

    private class Itr implements Iterator {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            if (cursor < size) {
                return true;
            }//
            return false;
        }

        @Override
        public Object next() {
            int nowCursor = cursor;
            cursor++;
            return elementData[nowCursor];
        }
    }


    public void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1; // oldCapacity 的 1.5 倍（取整）+ 1
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            // Arrays.copyOf 功能：把一个数组复制到另一个数组，返回值就是另一个数组。
            // 第一参数是被拷贝参数，第二个参数是返回的新数组的长度。
            // 如果新数组的长度超过原数组的长度，则保留数组默认值。
            // 如果新数组的长度小于原数组的长度，则多出的部分不保留。
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }


}
