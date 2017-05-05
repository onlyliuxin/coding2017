package com.coding.basic.stack;

/**
 * 用一个数组实现两个栈,与循环队列类似,但相遇条件不同
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author kai
 *
 */
public class TwoStackInOneArray {
	Object[] data = new Object[10];
	
	private int p1 = 0;
	private int p2 = data.length-1;
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		if(p1 == p2){
			 data = grow(data, 10 , p2);
			 p2 += 10;
		}
		data[p1++] = o;
	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if(p1 <= 0){
			throw new RuntimeException("Stack1 is empty!");
		}
		p1--;
		Object result1 = data[p1];
		data[p1] = null;
		return result1;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		if(p1 <= 0){
			throw new RuntimeException("Stack1 is empty!");
		}
		p1--;
		Object result1 = data[p1];
		p1++;
		return result1;
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		if(p1 == p2){
			data = grow(data, 10 , p2);
			p2+=10;
		}
		data[p2--] = o;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		if(p2 >= data.length-1){
			throw new RuntimeException("Stack1 is empty!");
		}
		p2++;
		Object result2 = data[p2];
		data[p2] = null;
		return result2;
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		if(p2 >= data.length-1){
			throw new RuntimeException("Stack1 is empty!");
		}
		p2++;
		Object result2 = data[p2];
		p2--;
		return result2;
	}
	/*
	 * 扩容：
	 * 1.从p1与p2之间扩容，而不是直接在数组结束处扩容
	 * 2.扩容之后，p2相应扩大size
	 * 
	 */
	public static Object[] grow(Object[] src, int size, int p2){
		Object[] target = new Object[src.length + size];
		System.arraycopy(src, 0, target, 0, p2);
		System.arraycopy(src, p2,target, p2+size, src.length-p2);
		return target;
	}
	
	public static void main(String[] args){
		TwoStackInOneArray stack = new TwoStackInOneArray();
		stack.push1(1);
		stack.push1(2);
		stack.push1(3);
		stack.push1(4);
		stack.push1(5);
		stack.push1(6);
		stack.push1(7);
		stack.push1(8);
		stack.push1(9);
		stack.push2(9);
		stack.push2(8);
		stack.push2(7);
		stack.push2(6);
		stack.push2(5);
		stack.push2(4);
		stack.push2(3);
		stack.push2(2);
		stack.push2(1);
		System.out.println(stack.peek1());
		System.out.println(stack.pop1());
		System.out.println(stack.pop1());
		System.out.println(stack.pop1());
		System.out.println("============");
		System.out.println(stack.peek2());
		System.out.println(stack.pop2());
		System.out.println(stack.pop2());
		System.out.println(stack.pop2());	
	}
}
