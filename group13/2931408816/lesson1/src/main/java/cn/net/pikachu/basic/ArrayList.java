package cn.net.pikachu.basic;

import java.util.Arrays;

public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData = new Object[100];

    private class Itr implements Iterator {
        private int curIndex = 0;

        @Override
        public boolean hasNext() {
            return curIndex < size;
        }

        @Override
        public Object next() {
            return elementData[curIndex++];
        }
    }

    public void add(Object o) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size++] = o;
    }

    private void grow() {
        if (elementData.length < 2048) {
            Arrays.copyOf(elementData, elementData.length * 2);
        } else {
            Arrays.copyOf(elementData, elementData.length + 1024);
        }
    }

    public void add(int index, Object o) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        } else if (index == size) {
            add(o);
        } else {
            if (size == elementData.length) {
                grow();
            }
            for (int i = size; i >= index; i--) {
                elementData[i + 1] = elementData[i];
            }
            elementData[index] = o;
        }
        size++;

    }

    public Object get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        return elementData[index];
    }

    public Object remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        Object o = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return o;
    }

    public int size() {
        return size;
    }

    // 迭代器留在后面写
    public Iterator iterator() {
        return new Itr();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < size - 1; i++) {
            builder.append(elementData[i]).append(",");
        }
        if (size > 0) {
            builder.append(elementData[size - 1]);
        }
        builder.append("]");
        return builder.toString();
    }
}
