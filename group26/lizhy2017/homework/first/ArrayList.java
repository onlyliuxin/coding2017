package first;

import java.util.Arrays;

import javax.swing.text.html.HTMLDocument;

public class ArrayList implements List {
    private int mSize = 0;
    private Object[] EMPTY_ElementData = new Object[0];
    private Object[] mElementData;

    public ArrayList() {
        this(10);
    }

    public ArrayList(int size) {
        if (size > 0) {
            mElementData = new Object[size];
            mSize = size;
        } else {
            if (mSize != 0) {
                throw new IllegalArgumentException("Illegal Capacity: " + mSize);
            }
            mElementData = EMPTY_ElementData;
        }
    }

    public void add(Object o) {
        checkIncrement(mSize);
        mElementData[mSize++] = o;
    }

    public void add(int index, Object o) {
        checkRange(index);
        if (mSize == 0) {
            mSize = Math.max(10, mSize);
        }
        checkIncrement(mSize);

        System.arraycopy(mElementData, index, mElementData, index + 1, mSize - index);
        mElementData[index] = o;
    }

    private void checkRange(int index) {
        if (index > mSize || index < 0) {
            throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + mSize);
        }
    }

    public Object get(int index) {
        checkRange(index);
        return mElementData[index];
    }

    public Object remove(int index) {
        checkRange(index);
        Object oldObject = mElementData[index];
        int temp = mSize - index - 1;
        if (temp > 0) {
            System.arraycopy(mElementData, index + 1, mElementData, index, temp);
        }
        mElementData[--mSize] = null;//JAVA GC
        return oldObject;
    }

    public int size() {
        return mSize;
    }

    public Iterator iterator() {
        return new MyIterator();
    }

    private void checkIncrement(int capacity) {
        ++capacity;
        if (capacity - mElementData.length > 0) {
            grow(capacity);
        }
    }

    /**
     * 扩容，规则为：oldCapacity + (oldCapacity >> 1)
     *
     * @param capacity 扩容
     */
    private void grow(int capacity) {
        int oldCapacity = mElementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < capacity) {
            newCapacity = capacity;
        }
        if (newCapacity > 2147483639) {
            newCapacity = 2147483639;
        }
        mElementData = Arrays.copyOf(mElementData, newCapacity);
    }

    private class MyIterator implements Iterator {
        int nextCursor = 0;//下个元素索引

        @Override
        public boolean hasNext() {
            return (nextCursor != mSize);
        }

        @Override
        public Object next() {
            int i = nextCursor;
            nextCursor++;
            return mElementData[i];
        }
    }
}
