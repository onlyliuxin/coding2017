package org.apn.coding2017.basic;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by QiPan on 2017/2/23.
 */
public class ArrayList implements List {

    private int size;

    // 设置默认容量 不可变
    private static final int DEFAULT_CAPACITY = 10;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private Object[] elementData;

    // 定义一个默认的空的数组,引用不可变
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = new Object[]{};
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public ArrayList() { // 如果调用默认构造函数，设置容器为空的默认数组
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }


    public boolean add(Object o) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = o;
        return true;
    }

    @Override
    public boolean add(int index, Object o) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        ensureCapacityInternal(size + 1);
        // 从插入位置开发，所有的数据需要往后移动
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = o;
        size++;
        return true;
    }

    public Object set(int index, Object element) {
        checkIndexOutOf(index);
        Object oldEle = elementData[index];
        elementData[index] = element;
        return oldEle;
    }

    public Object get(int index) {
        checkIndexOutOf(index);
        return elementData[index];
    }

    public Object remove(int index) {
        checkIndexOutOf(index);
        Object oldValue = elementData[index];

        // 计算需要移动的位数
        int needMoveNum = size - index - 1;

        if (needMoveNum > 0) {
            /*
             * src：源数组；
             * srcPos：源数组要复制的起始位置；
             * dest：目的数组；
             * destPos：目的数组放置的起始位置；
             * length：复制的长度。
             */
            System.arraycopy(elementData, index + 1, elementData, index, needMoveNum);
        }
        // 避免对象游离,是的gcc能工作回收
        elementData[--size] = null;
        return oldValue;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Iterator iterator() {
        return new Itr();
    }


    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * 检查数组越界
     *
     * @param index
     */
    private void checkIndexOutOf(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private void ensureCapacityInternal(int minCapacity) {
        // 如果是容器是默认的空的数组
        if (this.elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            // 取得为与默认容量相比的较大值
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        // 调用确保有能力放下那么多元素
        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        // 防止容量溢出。所需的最小容器大于数组长度
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    /**
     * 扩充容量
     *
     * @param minCapacity
     */
    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        // 得到一个新的容量大小，为 oldCapacity 的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // 如果扩容后的大小比，最小容量（DEFAULT_CAPACITY： 10）小
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        } else if (newCapacity - MAX_ARRAY_SIZE > 0) { // 扩容后比最大的还大，考虑溢出
            //
            newCapacity = hugeCapacity(minCapacity);
        }

        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    /**
     * 这个很少用到这个判断，毕竟基本不会使用那么大容量存储
     *
     * @param minCapacity
     * @return
     */
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    private class Itr implements Iterator {

        /**
         * 当前游标
         */
        int cursor;
        int lastRet = -1; // 是否是返回最后一个结果

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Object next() {
            int i = cursor;
            // 游标已经超过数组的大小
            if (i >= size) {
                throw new NoSuchElementException();
            }
            Object[] elementData = ArrayList.this.elementData;

            // 指向下一个元素
            cursor = i + 1;
            Object elementDatum = elementData[i];
            lastRet = i;
            return elementDatum;
        }

        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            // 实际上这个时候的 lastRet,为 next方法时候的 i = cursor
            ArrayList.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }
    }


}
