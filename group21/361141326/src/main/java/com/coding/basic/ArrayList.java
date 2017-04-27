package com.coding.basic;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {

    private int size = 0;

    private Object[] elementData;

    /**
     * 默认初始化大小
     */
    private final static int DEFAULT_SIZE = 10;

    /**
     * 默认增加的固定长度，该值后期可以改成与size相关的值
     * 原ArrayList中采用length >> 1
     */
    private final static int DEFAULT_STEP = 10;

    private final static int MAX_SIZE = Integer.MAX_VALUE - 8;

    public ArrayList(int initCapacity) {
        // 初始化
        elementData = new Object[initCapacity <= 0 ? DEFAULT_SIZE : initCapacity];
    }

    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    public void add(T t) {
        sizeCheck(size + 1);
        elementData[size ++] = t;
    }

    public void add(int index, T o) {
        rangeCheck(index);
        sizeCheck(size + 1);

        // 将index开始的元素向后移动一位，利用copy
        // 拷贝长度为size - index
        System.arraycopy(elementData, index, elementData, index + 1, size - index);

        // 赋值
        elementData[index] = o;
        size ++;
    }

    public T get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    public T remove(int index) {
        rangeCheck(index);

        T origin = elementData(index);

        // 从index + 1到最后一个元素的长度
        int num = size - (index + 1);
        System.arraycopy(elementData, index + 1, elementData, index, num);

        size--;
        return origin;
    }

    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {

        /**
         * 当前迭代器位置
         */
        private int currentPoint = 0;

        @Override
        public boolean hasNext() {
            return currentPoint < size;
        }

        @Override
        public T next() {
            return get(currentPoint ++);
        }
    }

    private void sizeCheck(int expectSize) {
        if (expectSize > elementData.length) grow();
    }

    @SuppressWarnings("unchecked")
    private T elementData(int index) {
        return (T) elementData[index];
    }

    /**
     * 改变elementData容量大小
     */
    private void grow() {
        int currentSize = elementData.length;
        int newSize = MAX_SIZE - currentSize > DEFAULT_STEP ? currentSize+ DEFAULT_STEP : MAX_SIZE ;
        elementData = Arrays.copyOf(elementData, newSize);
    }

    /**
     * 长度校验
     * @param index 指定index
     */
    private void rangeCheck(int index) {
        if (size < index) throw new ArrayIndexOutOfBoundsException();
    }

    /**
     * 方便输出查看
     * @return 格式化后的内容
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++){
            sb.append(elementData[i]);
            if (i < size - 1) sb.append(",");
        }
        return sb.append("]").toString();
    }
}
