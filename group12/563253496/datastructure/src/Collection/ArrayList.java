package Collection;

import com.coding.basic.List;

import java.util.NoSuchElementException;

import com.coding.basic.Iterator;

public class ArrayList implements List {

    private int size;
    private Object[] elementData;

    public ArrayList() {
        size = 0;
        elementData = new Object[10];
    }

    public ArrayList(Object o) {
        size = 0;
        elementData = new Object[10];
        this.add(o);
    }

    public ArrayList(int initialCapacity) {
        size = 0;
        elementData = new Object[initialCapacity];
    }

    @Override
    public void add(Object o) {
        if (size <= elementData.length - 1) {
            elementData[size] = o;
            size++;
        } else {
            this.extendCapacity();
            elementData[size] = o;
            size++;

        }
    }

    @Override
    public void add(int index, Object o) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index > elementData.length - 1) {
            while (index > elementData.length - 1) {
                this.extendCapacity();
            }
            elementData[index] = o;
            size = index + 1;
            return;
        }

        if (index >= size) {
            size = index + 1;
            elementData[index] = o;
            return;
        }
        if (index >= 0 && index < size) {
            this.moveRearward(index);
            elementData[index] = o;
            size++;
            return;
        }
    }

    @Override
    public Object get(int index) {
        checkCapacity(index);
        if (index < size) {
            return elementData[index];
        }
        return null;
    }

    @Override
    public Object remove(int index) {
        checkCapacity(index);

        if (index == size - 1) {
            size--;
            return elementData[size - 1];
        }
        if (index < size - 1) {
            Object tmp = elementData[index];
            for (int i = index; i < size - 1; i++) {
                elementData[i] = elementData[i + 1];
            }
            size--;
            return tmp;
        }
        return null;
    }

    private void checkCapacity(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void extendCapacity() {
        Object[] elements = new Object[elementData.length + 10];
        for (int i = 0; i < size; i++) {
            elements[i] = elementData[i];
        }
        elementData = elements;

    }

    @Override
    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    private void moveRearward(int index) {
        size++;

        if (size >= elementData.length - 1)
            this.extendCapacity();

        for (int i = size - 1; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
    }

    public Iterator iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator {

        private int pos;

        public ArrayListIterator() {

            pos = 0;
        }

        @Override
        public boolean hasNext() {
            if (pos < size) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return elementData[pos++];
            } else
                throw new NoSuchElementException();

        }

    }

}
