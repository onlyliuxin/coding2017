package com.github.miniyk2012.coding2017.basic.stack;

import train.Visitor_Pattern.ObjectStructure;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，
 * 压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray<E> {
	Object[] data = new Object[2];
	//
	private int top1 = 0;  // 前半段栈下一个要放入的位置
	private int top2 = 1;  // 后半段栈下一个要放入的位置

    /**
     *  保证容量，在push前调用
     */
	private void ensureCapacity() {
	    // 如果2个指针相遇，则需要扩容
	    if (top1 >= top2) {
            int oldLength = data.length;
            int newLength = oldLength * 2;
            Object[] copied = new Object[newLength];
            // 拷贝前半段
            System.arraycopy(data, 0, copied, 0, size1());
            // 拷贝后半段
            System.arraycopy(data, top2+1, copied, newLength-size2(), size2());
            top2 = newLength - size2() - 1;
            data = copied;
        }
    }

    /**
     * 栈1的元素个数
     * @return
     */
    public int size1() {
	    return top1;
    }

    /**
     * 栈2的元素个数
     */
    public int size2() {
        return data.length - top2 - 1;
    }

    /**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(E o){
		ensureCapacity();
		data[top1++] = o;
	}
	/**
	 * 从第一个栈中弹出元素,为空抛异常
	 * @return
	 */
	public E pop1(){
	    return (E) data[--top1];
	}
	
	/**
	 * 获取第一个栈的栈顶元素,为空则返回null
	 * @return
	 */
	
	public E peek1(){
        if (size1() == 0) {
            return null;
        }
        return (E) data[top1-1];
	}

	/**
	 * 向第二个栈压入元素
	 */
	public void push2(E o){
        ensureCapacity();
        data[top2--] = o;
    }
	/**
	 * 从第二个栈弹出元素,为空抛异常
	 * @return
	 */
	public E pop2(){
        return (E) data[++top2];
	}

	/**
	 * 获取第二个栈的栈顶元素，,为空则返回null
	 * @return
	 */
	public  E peek2(){
	    if (size2() == 0) {
	        return null;
        }
        return (E) data[top2+1];
	}
	
}
