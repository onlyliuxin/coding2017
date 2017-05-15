package com.aaront.exercise.basic;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 */
public class TwoStackInOneArray {
    private Object[] data = new Object[10];
    private int pos1 = -1;
    private int pos2 = data.length;

    /**
     * 向第一个栈中压入元素
     *
     * @param o
     */
    public void push1(Object o) {
        _ensureCapacityEnough();
        data[++pos1] = o;
    }

    /**
     * 从第一个栈中弹出元素
     *
     * @return
     */
    public Object pop1() {
        if (pos1 < 0) return null;
        return data[pos1--];
    }

    /**
     * 获取第一个栈的栈顶元素
     *
     * @return
     */

    public Object peek1() {
        if (pos1 < 0) return null;
        return data[pos1];
    }

    /*
     * 向第二个栈压入元素
     */
    public void push2(Object o) {
        _ensureCapacityEnough();
        data[--pos2] = o;
    }

    /**
     * 从第二个栈弹出元素
     *
     * @return
     */
    public Object pop2() {
        if (pos2 >= data.length) return null;
        return data[pos2++];
    }

    /**
     * 获取第二个栈的栈顶元素
     *
     * @return
     */

    public Object peek2() {
        if (pos2 >= data.length) return null;
        return data[pos2];
    }

    private void _ensureCapacityEnough() {
        if (pos1 + 1 == pos2) {
            _dilatancy();
        }
    }

    private void _dilatancy() {
        Object[] temp = new Object[data.length * 2];
        for (int i = 0; i <= pos1; i++) {
            temp[i] = data[i];
        }

        for (int i = pos2; i < data.length; i++) {
            temp[temp.length - (data.length - i)] = data[i];
        }

        pos2 = temp.length - (data.length - pos2);
        data = temp;
    }
}
