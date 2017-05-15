package com.basic.week9;
import java.util.Stack;

/**
 * @author chk
 */
public class TwoStackInOneArray {



    Object[] data = new Object[10];

    private int index1 = 0;
    private int index2 = data.length - 1;


    private boolean isFull() {
        if (index1 == index2) {
            return true;
        } else
            return false;
    }

    private void extendCapacity() {

    }

    /**
     * 向第一个栈中压入元素
     *
     * @param o
     */
    public void push1(Object o) {

    }

    /**
     * 从第一个栈中弹出元素
     *
     * @return
     */
    public Object pop1() {
        return null;
    }

    /**
     * 获取第一个栈的栈顶元素
     *
     * @return
     */

    public Object peek1() {
        return null;
    }

    /*
     * 向第二个栈压入元素
     */
    public void push2(Object o) {

    }

    /**
     * 从第二个栈弹出元素
     *
     * @return
     */
    public Object pop2() {
        return null;
    }

    /**
     * 获取第二个栈的栈顶元素
     *
     * @return
     */

    public Object peek2() {
        return null;
    }

}
