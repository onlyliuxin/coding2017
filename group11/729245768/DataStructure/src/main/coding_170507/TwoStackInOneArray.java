package main.coding_170507;

import java.util.EmptyStackException;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 */
public class TwoStackInOneArray {
    Object[] data = new Object[10];
    private int start = 0;
    private int end = data.length-1;

    /**
     * 向第一个栈中压入元素
     *
     * @param o
     */
    public void push1(Object o) {
        if(start==end){
            throw new RuntimeException("栈满");
        }
        data[start++] = o;
    }

    /**
     * 从第一个栈中弹出元素
     *
     * @return
     */
    public Object pop1() {
        if(start==0){
            throw new EmptyStackException();
        }
        return data[--start];
    }

    /**
     * 获取第一个栈的栈顶元素
     *
     * @return
     */

    public Object peek1() {
        if(start==0){
            throw new EmptyStackException();
        }
        return data[start-1];
    }

    /*
     * 向第二个栈压入元素
     */
    public void push2(Object o) {
        if(start==end){
            throw new RuntimeException("栈满");
        }
        data[end--] = o;
    }

    /**
     * 从第二个栈弹出元素
     *
     * @return
     */
    public Object pop2() {
        if(end==data.length-1){
            throw new EmptyStackException();
        }
        return data[++end];
    }

    /**
     * 获取第二个栈的栈顶元素
     *
     * @return
     */

    public Object peek2() {
        if(end==data.length-1){
            throw new EmptyStackException();
        }
        return data[end+1];
    }
}
