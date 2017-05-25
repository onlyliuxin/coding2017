package com.zhuoyue.scheduleplan.domain;

/**
 * 用一个数组实现两个栈,将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 *
 * @author xyy
 * @create 2017-05-25 16:17
 **/
public class TwoStackInOneArray {

    Object[] data = new Object[10];
    private int size;
    private int top1;
    private int top2;


    public TwoStackInOneArray(int size) {
        this.data = new Object[size];
        this.size = size;
        this.top1 = -1;
        this.top2 = data.length;
    }



    /**
     * 向第一个栈中压入元素
     *
     * @param o
     */
    public void push1(Object o) {
        ensureCapacity();
        data[++top1] = o;
    }

    private void ensureCapacity() {
        if (top2 - top1 > 1) {
            return;
        } else {
            Object[]newArray = new Object[data.length * 2];
            System.arraycopy(data,0,newArray,0,top1+1);

            int stack2Size = data.length -top2;
            int newTop2 = newArray.length - stack2Size;
            System.arraycopy(data, top2, newArray, newTop2, stack2Size);

            top2 = newTop2;
            data = newArray;
        }



    }

    /**
     * 从第一个栈中弹出元素
     *
     * @return
     */
    public Object pop1() {
        if (top1 == -1) {
            throw new RuntimeException("stack1 为空");
        }
        Object o = data[top1];
        data[top1--] = null;
        return o;
    }

    /**
     * 获取第一个栈的栈顶元素
     *
     * @return
     */

    public Object peek1() {
        if (top1 == -1) {
            throw new RuntimeException("stack1 为空");
        }
        return data[top1];
    }

    /*
     * 向第二个栈压入元素
     */
    public void push2(Object o) {
        ensureCapacity();
        data[--top2] = o;
    }

    /**
     * 从第二个栈弹出元素
     *
     * @return
     */
    public Object pop2() {
        if (top2 == data.length) {
            throw new RuntimeException("stack2 为空");
        }
        Object o = data[top2];
        data[top2++] = null;
        return o;
    }

    /**
     * 获取第二个栈的栈顶元素
     *
     * @return
     */

    public Object peek2() {
        if (top2 == data.length) {
            throw new RuntimeException("stack2 为空");
        }
        return data[top2];
    }


    /**
     * @Author xuyangyang
     * @Describe 测试类
     * @Date 2017/5/25
     * @Params
     * @Return
     */
    public static void main(String[] args) {
        TwoStackInOneArray stack = new TwoStackInOneArray(10);
        stack.push1(1);
        stack.push1(2);
        stack.push1(3);
        stack.push1(4);
        stack.push1(5);

        stack.push2(1);
        stack.push2(2);
        stack.push2(3);
        stack.push2(4);
        stack.push2(5);

        for (int i = 1; i <= 5; i++) {
            System.out.println(stack.peek1()+"  == "+stack.peek2() );
            System.out.println(stack.pop1()+"  == "+stack.pop2() );
        }
    }




}
