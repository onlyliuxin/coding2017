package com.coding.basic.datastructure.stack;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 */
public class TwoStackInOneArray {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int firstIndex;
    private int lastIndex;

    public TwoStackInOneArray() {
        this(DEFAULT_CAPACITY);
    }

    public TwoStackInOneArray(int size) {
        data = new Object[size];
        firstIndex = -1;
        lastIndex = size;
    }

    /**
     * 向第一个栈中压入元素
     */
    public void push1(Object o) {
        this.checkPosition();
        data[++firstIndex] = o;
    }

    /**
     * 从第一个栈中弹出元素
     */
    public Object pop1() {
        return data[firstIndex--];
    }

    /**
     * 获取第一个栈的栈顶元素
     */

    public Object peek1() {
        return data[firstIndex];
    }

    /**
     * 向第二个栈压入元素
     */
    public void push2(Object o) {
        this.checkPosition();
        data[--lastIndex] = o;
    }

    /**
     * 从第二个栈弹出元素
     */
    public Object pop2() {
        return data[lastIndex++];
    }

    /**
     * 获取第二个栈的栈顶元素
     */

    public Object peek2() {
        return data[lastIndex];
    }

    private void checkPosition() {
        if (firstIndex + 1 == lastIndex) {
            this.grow();
        }
    }

    private void grow() {
        Object[] newArray = new Object[data.length << 1];
        System.arraycopy(data, 0, newArray, 0, firstIndex + 1);
        int secondStackSize = data.length - lastIndex;
        int newLastIndex = newArray.length - secondStackSize;
        System.arraycopy(data, lastIndex, newArray, newLastIndex, secondStackSize);
        lastIndex = newLastIndex;
        data = newArray;
    }

}
