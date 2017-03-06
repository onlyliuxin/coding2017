package io.github.vxzh;

/**
 * Created by vxzh on 22/02/2017.
 */
public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData;

    public ArrayList() {
        this.elementData = new Object[10];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(Object o) {
        int minCapacity = size + 1;
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
        elementData[size++] = o;
        return true;
    }

    public boolean add(int index, Object o) {
        if (index >= size || index < 0)
            throw new RuntimeException("IndexOutOfBoundsException");
        int minCapacity = size + 1;
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
        elementData[index] = o;
        size++;
        return true;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    int moved = size - index - 1;
                    if (moved > 0)
                        copy(elementData, index + 1, elementData, index, moved);
                    elementData[--size] = null;
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    int moved = size - index - 1;
                    if (moved > 0)
                        copy(elementData, index + 1, elementData, index, moved);
                    elementData[--size] = null;
                    return true;
                }
        }
        return false;
    }

    public boolean remove(int index) {
        if (index >= size || index < 0)
            throw new RuntimeException("IndexOutOfBoundsException");
        int moved = size - index - 1;
        if (moved > 0)
            copy(elementData, index + 1, elementData, index, moved);
        elementData[--size] = null;
        return true;
    }

    public Object get(int index) {
        if (index >= size || index < 0)
            throw new RuntimeException("IndexOutOfBoundsException");
        return elementData[index];
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        elementData = copy(elementData, newCapacity);

    }

    public Object[] copy(Object[] src, int newCapacity) {
        Object[] arr = new Object[newCapacity];
        for (int i = 0; i < src.length; i++) {
            arr[i] = elementData[i];
        }
        return arr;
    }

    public void copy(Object[] src, int srcPost, Object[] dest, int destPost, int length) {
        for (int i = 0; i < length; i++) {
            dest[destPost + i] = src[srcPost + i];
        }
    }

    public Iterator iterator() {
        return new ListIterator(this);
    }

    public class ListIterator implements Iterator {

        private List list;
        private int endIndex = 0;
        private int index = 0;

        public ListIterator(ArrayList list) {
            this.list = list;
            this.endIndex = list.size();
        }

        @Override
        public boolean hasNext() {
            return this.index < this.endIndex;
        }

        @Override
        public Object next() {
            if (!this.hasNext()) {
                throw new RuntimeException("EmptyElementException");
            } else {
                return list.get(index++);
            }
        }
    }
}

