package main.java;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by yrs on 2017/2/21.
 */
public class ArrayList implements List{

    private int size = 0;

    private Object[] elementData;

    public ArrayList() {
        this.elementData = new Object[10];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = new Object[0];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    public void add(Object o) {
        judegGrow();
        elementData[size++] = o;
    }

    public void add(int index, Object o) {
        if(index<0 || index>size) {
            throw new IndexOutOfBoundsException();
        }else if(index == size) {
            add(o);
        }else {
            judegGrow();
            System.arraycopy(elementData, index, elementData, index + 1,
                    size - index);
            elementData[index] = o;
            size++;
        }
    }

    public Object get(int index) {
        rangeCheck(index);
        return elementData[index];
    }

    public Object remove(int index) {
        rangeCheck(index);
        Object o = elementData[index];

        int move = size - 1 -index;
        if(move > 0) {
            System.arraycopy(elementData, index+1, elementData, index, move);
        }
        elementData[--size] = null;

        return o;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return new ArrrayListIterator(this);
    }

    private class ArrrayListIterator implements Iterator {

        ArrayList arrayList;

        int pos;

        private ArrrayListIterator(ArrayList arrayList) {
            this.arrayList = arrayList;
        }

        @Override
        public boolean hasNext() {
            return pos != arrayList.size;
        }

        @Override
        public Object next() {
            if(pos < size) {
                int i = pos;
                pos++;
                return elementData[i];
            }else {
                throw new NoSuchElementException();
            }
        }
    }

    private void judegGrow() {
        if(size == elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length + 1);
        }
    }

    private void rangeCheck(int index) {
        if(index<0 || index>=size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
                