package com.github.HarryHook.coding2017.stack;

import java.util.Arrays;

/**
 * 用一个数组实现两个栈 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * 
 * @author HarryHook
 *
 */
public class TwoStackInOneArray {
    Object[] data = new Object[6];
    int i = 0;
    int j = data.length - 1;

    /**
     * 向第一个栈中压入元素
     * 
     * @param o
     */
    public void push1(Object o) {
	if (i > j) {
	    expansion();
	}
	data[i++] = o;

    }

    /**
     * 从第一个栈中弹出元素
     * 
     * @return
     */
    public Object pop1() {
	if (data[0] == null) {
	    throw new RuntimeException("stack1 is empty");
	}
	Object tmp = data[i - 1];
	data[--i] = null;
	return tmp;
    }

    /**
     * 获取第一个栈的栈顶元素
     * 
     * @return
     */

    public Object peek1() {
	return data[i - 1];
    }

    /*
     * 向第二个栈压入元素
     */
    public void push2(Object o) {
	if (i > j) {
	    expansion();
	}
	data[j--] = o;

    }

    /**
     * 从第二个栈弹出元素
     * 
     * @return
     */
    public Object pop2() {
	if (data[data.length - 1] == null) {
	    throw new RuntimeException("stack2 is empty");
	}
	Object tmp = data[j + 1];
	data[++j] = null;
	return tmp;

    }

    /**
     * 获取第二个栈的栈顶元素
     * 
     * @return
     */

    public Object peek2() {
	return data[j + 1];
    }

    // 给数组扩容
    public void expansion() {
	int oldCapacity = data.length;
	int newCapacity = oldCapacity * 2; // 此时扩容是因为数组有可能全是stack2的元素
	data = Arrays.copyOf(data, newCapacity);
	int newIndex = newCapacity - 1;
	for (int k = oldCapacity - 1; k > j; k--) {
	    data[newIndex--] = data[k];
	    data[k] = null;
	}
	j += newCapacity - oldCapacity;
    }

    public static void main(String[] args) {
	TwoStackInOneArray stack = new TwoStackInOneArray();

	stack.push1(3);
	stack.push1(5);
	stack.push1(6);
	stack.push1(11);
	System.out.println("peek1: " + stack.peek1());
	System.out.println("pop1: " + stack.pop1());
	System.out.println("pop1: " + stack.pop1());
	stack.push1(61);
	System.out.println("pop1: " + stack.pop1());
	System.out.println("pop1: " + stack.pop1());
	System.out.println("peek1: " + stack.peek1());
	System.out.println("pop1: " + stack.pop1());

	stack.push2(123);
	stack.push2(1);
	stack.push2(4);

	stack.push1(3);
	stack.push1(5);
	stack.push1(6);

	stack.push2(123);
	stack.push2(1);
	stack.push2(4);
	stack.push2(12);
	stack.push2(2);
	stack.push2(8);
	System.out.println("pop1: " + stack.pop1());
	System.out.println("peek1: " + stack.peek1());
	System.out.println("pop1: " + stack.pop1());
	System.out.println("pop1: " + stack.pop1());

	System.out.println("pop2: " + stack.pop2());
	System.out.println("pop2: " + stack.pop2());
	System.out.println("pop2: " + stack.pop2());
	System.out.println("pop2: " + stack.pop2());
	System.out.println("pop2: " + stack.pop2());
	System.out.println("pop2: " + stack.pop2());

	System.out.println("pop2: " + stack.pop2());
	System.out.println("pop2: " + stack.pop2());
	System.out.println("pop2: " + stack.pop2());

    }

}
