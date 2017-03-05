package com.coding.basic;

public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData = new Object[5];

    public void add(Object o) {
        dilatation();
        size = size + 1;
        elementData[size - 1] = o;
    }


    public void add(int index, Object o) {
        //是否超出目前大小+1；
        //是否需要先扩容
        //移动数组
        checkRange(index);

        dilatation();
        //手动移动素组
        for (int i = size; i >= index; i--) {
            Object o1 = elementData[i];
            elementData[i + 1] = elementData[i];
        }
        elementData[index] = o;
        size = size + 1;

    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }


    public Object get(int index) {
        checkRange(index);
        return elementData[index];
    }

    public Object remove(int index) {
        checkRange(index);

        Object object = elementData[index];

        //native 方法，未知细节
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size = size - 1;
        elementData[size] = null;
        return object;
    }

    @Override
    public int size() {
        return this.size;
    }

    public void checkRange(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    public void dilatation() {
        if (elementData.length == size + 1) {
            Object[] tempElementData = elementData;
            elementData = new Object[elementData.length * 2];
            for (int i = 0; i < tempElementData.length; i++) {
                elementData[i] = tempElementData[i];
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < size; i++) {
            Object object = elementData[i];
            value.append(object.toString() + ",");
        }
        return value.toString();
    }

    Iterator mIterator = new MIterator();

    public Iterator iterator() {
        return mIterator;
    }

    class MIterator implements Iterator {
        int index = 0;

        @Override
        public boolean hasNext() {
            if (index < size) {

                return true;
            }
            index = 0;
            return false;
        }

        @Override
        public Object next() {
            Object object = elementData[index];
            index++;
            return object;
        }

    }
}
