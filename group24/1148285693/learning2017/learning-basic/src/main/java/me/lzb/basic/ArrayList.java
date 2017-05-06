package me.lzb.basic;

/**
 * 简易ArrayList
 * Created by LZB on 2017/3/11.
 */
public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData = {};


    public void add(Object o) {
//        if (elementData.length < size + 1) {
//            Object[] target = new Object[size + 1];
//            System.arraycopy(elementData, 0, target, 0, elementData.length);
//            elementData = target;
//        }
//        elementData[size] = o;
//        size = size + 1;
        add(size, o);
    }


    public void add(int index, Object o) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index boom");
        }

        int leftSize = index;
        int rightSize = size - index;
        Object[] target = new Object[size + 1];
        System.arraycopy(elementData, 0, target, 0, leftSize);
        target[index] = o;
        System.arraycopy(elementData, index, target, index + 1, rightSize);
        elementData = target;
        size = size + 1;
    }

    public Object get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index boom");
        }
        return elementData[index];
    }

    public Object remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index boom");
        }
        Object removeObject = elementData[index];
        int leftSize = index;
        int rightSize = size - index - 1;
        Object[] target = new Object[elementData.length - 1];
        System.arraycopy(elementData, 0, target, 0, leftSize);
        System.arraycopy(elementData, index + 1, target, index, rightSize);
        elementData = target;
        size = size - 1;
        return removeObject;
    }

    public int size() {
        return size;
    }


    public Iterator iterator() {
        return new ArrayListIterator(this);
    }

    private class ArrayListIterator implements Iterator {
        private ArrayList arrayList;

        int pos = 0;

        private ArrayListIterator(ArrayList arrayList) {
            this.arrayList = arrayList;
        }

        @Override
        public boolean hasNext() {
            if (pos >= arrayList.size) {
                return false;
            }

            return true;
        }

        @Override
        public Object next() {
            Object result = arrayList.get(pos);
            pos = pos + 1;
            return result;
        }
    }
}
