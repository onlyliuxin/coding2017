package com.johnChnia.coding2017.basic;

import java.util.Arrays;

/**
 * Created by john on 2017/3/8.
 *
 * @// TODO: 2017/4/1  实现Iterator 接口
 */

public class ArrayList<E> implements List<E> {
    private Object[] elementData;
    private int size = 0;
    private static final int DEFAULTCAPACITY = 10;


    /**
     * Constructs an list with the default capacity.
     */
    public ArrayList() {
        elementData = new Object[DEFAULTCAPACITY];
    }

    /**
     * Constructs an list with the specified initial capacity.
     *
     * @param initialCapacity capacity of arrayList.
     * @throws IllegalArgumentException if the specified initial capacity
     *                                  is negative or zero
     */
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        rangeCheck(index);
        return (E) elementData[index];
    }


    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list
     */
    public void add(E element) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = element;
    }


    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param element element to be inserted
     * @param index   index at which the specified element is to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public void add(int index, E element) {
        rangeCheck(index);
        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException {@inheritDoc} index out of B
     */
    public E remove(int index) {
        rangeCheck(index);
        Object oldValue = elementData[index];
        int numMoved = size() - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        }
        elementData[--size] = null; // let jc to clear
        return (E) oldValue;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    /**
     * Returns <tt>true</tt> if this list contains no elements.
     *
     * @return <tt>true</tt> if this list contains no elements
     */
    public boolean empty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }


    /**
     * Increases the capacity to ensure that it can hold at least the
     * number of elements specified by the double length of list.
     */
    private void grow() {
        elementData = Arrays.copyOf(elementData,
                2 * elementData.length);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int index = 0; index < size(); index++) {
            stringBuilder.append(elementData[index]);
            stringBuilder.append(", ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (minCapacity - elementData.length > 0)
            grow();
    }

    public Object[] toArray() {
        return Arrays.copyOf(elementData, size());
    }


    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + elementData.length;
    }

    /**
     * Checks if the given index is in range.  If not, throws an appropriate
     * runtime exception.  This method does *not* check if the index is
     * negative: It is always used immediately prior to an array access,
     * which throws an ArrayIndexOutOfBoundsException if index is negative.
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

}
