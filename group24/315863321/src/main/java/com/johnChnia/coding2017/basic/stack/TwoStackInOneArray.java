package com.johnChnia.coding2017.basic.stack;

/**
 * Created by john on 2017/5/7.
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 *
 * @author john
 */
public class TwoStackInOneArray {
    private Object[] data = new Object[4];
    private int pointer1 = -1;
    private int pointer2 = data.length;


    /**
     * 向第一个栈中压入元素
     *
     * @param o
     */
    public void push1(Object o) {
        ensureCapacityInternal();
        data[++pointer1] = o;
    }

    /**
     * 从第一个栈中弹出元素
     *
     * @return
     */
    public Object pop1() throws Exception {
        if (pointer1 == -1) {
            throw new Exception("栈1中没有元素");
        } else {
            return data[pointer1--];
        }
    }

    /**
     * 获取第一个栈的栈顶元素
     *
     * @return
     */

    public Object peek1() throws Exception {
        if (pointer1 == -1) {
            throw new Exception("栈1中没有元素");
        } else {
            return data[pointer1];
        }
    }

    /*
     * 向第二个栈压入元素
     */
    public void push2(Object o) {
        ensureCapacityInternal();
        data[--pointer2] = o;
    }

    /**
     * 从第二个栈弹出元素
     *
     * @return
     */
    public Object pop2() {
        if (pointer2 == data.length) {
            return null;
        } else {
            return data[pointer2++];
        }
    }

    /**
     * 获取第二个栈的栈顶元素
     *
     * @return
     */

    public Object peek2() {
        if (pointer2 == data.length) {
            return null;
        } else {
            return data[pointer2];
        }
    }

    /**
     * 如果栈1的指针和栈2的指针之差为1的话，那么就需要扩容。
     */
    private void ensureCapacityInternal() {
        if (pointer2 - pointer1 == 1) {
            grow();
        }
    }

    private void grow() {
        Object[] data2 = new Object[data.length * 2];
        System.arraycopy(data, 0, data2, 0, pointer1 + 1); //复制栈1值到data2
        int desPosForStack2 = data2.length - pointer2;
        System.arraycopy(data, pointer2, data2, desPosForStack2, pointer2);  // 复制栈2值到data2
        data = data2;
        pointer2 = desPosForStack2;  // 调整栈2指针位置
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i <= pointer1; i++) {
            sb.append(data[i]);
            sb.append(", ");
        }
        for (int j = pointer2; j < data.length; j++) {
            sb.append(data[j]);
            sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

}
