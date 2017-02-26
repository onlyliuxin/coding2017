package com.coding2017.group7.homework.c0226;

import java.util.Arrays;

public class MyArrayList implements MyList {

    private int size = 0;

    private Object[] elementData = new Object[10];

<<<<<<< HEAD
=======
    @Override
>>>>>>> 6f77b0da09652d5cbdf03d5d53e197580ced1c5a
    public void add(Object o) {
        if (isFull()) {
            increase();
        }
        elementData[size++] = o;
    }

<<<<<<< HEAD
    public void add(int index, Object o) {
        checkRange(index);
=======
    @Override
    public void add(int index, Object o) {
        checkRangeAdd(index);
>>>>>>> 6f77b0da09652d5cbdf03d5d53e197580ced1c5a
        if (isFull()) {
            increase();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
    }

<<<<<<< HEAD
    public Object get(int index) {
        checkRange(index);
        return elementData[index];
    }

    public Object remove(int index) {
        checkRange(index);
        Object element = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - (index + 1));
        elementData[size] = null;
        size--;
        return element;
    }

=======
    @Override
    public Object get(int index) {
        checkRangeGet(index);
        return elementData[index];
    }

    @Override
    public Object remove(int index) {
        checkRangeGet(index);
        Object element = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - (index + 1));
        elementData[--size] = null;
        return element;
    }

    @Override
>>>>>>> 6f77b0da09652d5cbdf03d5d53e197580ced1c5a
    public int size() {
        return size;
    }

    public MyIterator iterator() {
        return new MyArrayListIterator();
    }

    private boolean isFull() {
        return size >= elementData.length;
    }

<<<<<<< HEAD
    private void checkRange(int index) {
=======
    private void checkRangeGet(int index) {
>>>>>>> 6f77b0da09652d5cbdf03d5d53e197580ced1c5a
        boolean outOfRange = index < 0 || index >= size;
        if (outOfRange) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

<<<<<<< HEAD
=======
    private void checkRangeAdd(int index) {
        boolean outOfRange = index < 0 || index > size;
        if (outOfRange) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

>>>>>>> 6f77b0da09652d5cbdf03d5d53e197580ced1c5a
    private void increase() {
        Object[] target = new Object[elementData.length * 2];
        System.arraycopy(elementData, 0, target, 0, elementData.length);
        elementData = target;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }

    private class MyArrayListIterator implements MyIterator {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Object next() {
<<<<<<< HEAD
=======
            checkRangeGet(index);
>>>>>>> 6f77b0da09652d5cbdf03d5d53e197580ced1c5a
            return elementData[index++];
        }
    }
}
