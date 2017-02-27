package com.matthew.practice.structure;


import java.util.Arrays;

public class MyArrayList<E> implements MyList<E> {
    //思考为什么要用transient
    //elementData数组相当于容器，当容器不足时就会再扩充容量，但是容器的容量往往都是大于或者等于ArrayList所存元素的个数。
    //比如，现在实际有了8个元素，那么elementData数组的容量可能是8x1.5=12，如果直接序列化elementData数组，那么就会浪费4
    //个元素的空间，特别是当元素个数非常多时，这种浪费是非常不合算的。所以ArrayList的设计者将elementData设计为transient，
    //然后在writeObject方法中手动将其序列化，并且只序列化了实际存储的那些元素，而不是整个数组。
    transient Object[] elementData;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private int size;
    //容器的默认大小
    private static final int DEFAULT_CAPACITY = 10;

    //添加元素
    @Override
    public void add(E o) {
        //加入这个元素后 数组的容量至少要 +1 确保容量足够
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = o;
    }


    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {

        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        // >>1:   右移1位，     右移一位相当于除2，右移n位相当于除以2的n次方。扩大原来的一半
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        //容量的最小值minCapacity 比新的容量大小newCapacity还要大的时候 就赋值给newCapacity
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        //底层System.arraycopy
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public void add(int index, E o) {
        if (index > size || index < 0)
            //数组越界异常
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = o;
        size++;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public E get(int index) {
        if (index >= size)
            //数组越界异常
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        return (E) elementData[index];
    }

    //将后面的元素向前移
    @Override
    public E remove(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));

        E oldValue = (E) elementData[index];

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = null;

        return (E) oldValue;
    }

    @Override
    public int size() {
        return size;
    }
}
