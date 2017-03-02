package com.myutil;


import java.text.MessageFormat;
import java.util.NoSuchElementException;

/**
 * 数组列表
 */
public class ArrayList<T> implements List<T> {
    private Object[] elementData;
    private int size = 0;
    private static final int DEFAULT_SIZE = 10;

    /**
     * 判断边界
     * <p>
     * <pre>
     *      若 index < 0 或者 index > size 则抛出非法参数异常
     * </pre>
     *
     * @param index 当前索引
     */
    private void judgeRange(int index) {
        if (index < 0) {
            throw new IllegalArgumentException(
                    MessageFormat.format("Index is must be great or equal then 0. index:{0}", index));
        }
        if (index >= this.size) {
            throw new IllegalArgumentException(
                    MessageFormat.format("Index is must be less then size(). index:{0}", index));
        }
        if (this.size == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Array already can not Expansion.");
        }
    }

    /**
     * 扩充数组容量
     * <p>
     * <pre>
     *     若 size >= elementData.length 则对数组进行扩容
     *     扩容至原(elementData.length+1) * 2
     * </pre>
     */
    private void capacityExpansion() {
        if (this.size >= elementData.length) {
            Object[] tmpData = new Object[(elementData.length + 1) * 2];
            System.arraycopy(elementData, 0, tmpData, 0, elementData.length);
            elementData = tmpData;
        }
    }

    public ArrayList() {
        elementData = new Object[DEFAULT_SIZE];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(
                    MessageFormat.format("Capacity is must be great or equal 0. capacity:{0}", capacity));
        }
        this.elementData = new Object[capacity];
    }

    public void add(T element) {
        capacityExpansion();
        elementData[this.size] = element;
        this.size++;
    }

    public void add(T element, int index) {
        judgeRange(index);
        capacityExpansion();
        if (this.size - index > 0) {
            System.arraycopy(elementData, index, elementData, index + 1, this.size - index);
        }
        elementData[index] = element;
        this.size++;
    }

    public T remove(int index) {
        judgeRange(index);
        T tmpObject = (T) elementData[index];
        if (this.size - index > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, this.size - index - 1);
        }
        this.size--;
        return tmpObject;
    }

    public T get(int index) {
        judgeRange(index);
        return (T) elementData[index];
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.size; i++) {
            sb.append((T) elementData[i]);
            if (i < this.size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * 获取迭代器
     *
     * @return 迭代器
     */
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        int position = 0;
        int lastRet = -1;

        public boolean hasNext() {
            return position < ArrayList.this.size();
        }

        public T next() {
            if (position >= size) {
                throw new NoSuchElementException();
            }
            int i = position;
            T element = ArrayList.this.get(position++);
            lastRet = i;
            return element;
        }

        public T remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            T removeElement = ArrayList.this.remove(lastRet);
            position = lastRet;
            lastRet = -1;
            return removeElement;
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            ids.add(i);
        }
        Iterator iterator = ids.iterator();
        System.out.println(ids);
    }
}
