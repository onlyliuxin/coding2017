package org.apn.coding2017.basic;

import java.util.Arrays;

/**
 * Created by QiPan on 2017/2/23.
 */
public class ArrayList implements List {

    private int size;

    // 设置默认容量 不可变
    private static final int DEFAULT_CAPACITY = 10;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private Object[] elementData;

    private int modCount = 0;

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

    public Object set(int index, Object element) {
        return null;
    }

    public Object get(int index) {
        return null;
    }

    public Object remove(int index) {
        return null;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public Iterator iterator() {
        return null;
    }

    private void ensureCapacityInternal(int minCapacity) {
        // 如果是容器是默认的空的数组
        if (this.elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            // 取得为与默认容量相比的较大值
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
        // 调用确保有能力放下那么多元素
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // 防止容量溢出。所需的最小容器大于数组长度
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    /**
     * 扩充容量
     * @param minCapacity
     */
    private void grow(int minCapacity){
        int oldCapacity = elementData.length;
        // 得到一个新的容量大小，为 oldCapacity 的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // 如果扩容后的大小比，最小容量（DEFAULT_CAPACITY： 10）小
        if (newCapacity - minCapacity < 0){
            newCapacity = minCapacity;
        }else if (newCapacity - MAX_ARRAY_SIZE > 0){ // 扩容后比最大的还大，考虑溢出
            //
            newCapacity = hugeCapacity(minCapacity);
        }

        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    /**
     * 这个很少用到这个判断，毕竟基本不会使用那么大容量存储
     * @param minCapacity
     * @return
     */
    private static  int hugeCapacity(int minCapacity){
        if (minCapacity < 0){
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }


}
