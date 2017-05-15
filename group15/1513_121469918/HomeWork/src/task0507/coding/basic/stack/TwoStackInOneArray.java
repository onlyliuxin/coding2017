package task0507.coding.basic.stack;

import java.util.Arrays;

/**
 * 用一个数组实现两个栈 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * 
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	private int size = 10;
	private int top1;
	private int top2;
	private Object[] data;

	public TwoStackInOneArray() {
		top1 = -1;
		top2 = size;
		data = new Object[10];
	}

	public TwoStackInOneArray(int len) {
		size = len;
		top1 = -1;
		top2 = size;
		data = new Object[size];
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

	/**
	 * 从第一个栈中弹出元素
	 * 
	 * @return
	 */
	public Object pop1() {
		if (top1 < 0) {
			throw new RuntimeException("stack1 is empty");
		}
		return data[top1--];
	}

	/**
	 * 获取第一个栈的栈顶元素
	 * 
	 * @return
	 */

	public Object peek1() {
		if (top1 < 0) {
			throw new RuntimeException("stack1 is empty");
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
		if (top2 >= data.length) {
			throw new RuntimeException("stack2 is empty");
		}
		return data[top2++];
	}

	/**
	 * 获取第二个栈的栈顶元素
	 * 
	 * @return
	 */

	public Object peek2() {
		if (top2 >= data.length) {
			throw new RuntimeException("stack2 is empty");
		}
		return data[top2];
	}

	public void ensureCapacity() {
		if (top2 - top1 <=1) {
			Object[] newArray = new Object[data.length * 2];
			System.arraycopy(data, 0, newArray, 0, top1 + 1);
			
			int stack2Length = data.length - top2;
			int newTop2 = newArray.length - stack2Length;
			System.arraycopy(data, top2, newArray, newTop2, stack2Length);
			
			top2 = newTop2;
			data = newArray;
		}
	}

	public Object[] stack1ToArray() {
		return Arrays.copyOf(data, top1+1);
	}

	public Object[] stack2ToArray() {
		int index = 0;
		Object[] result = new Object[data.length - top2];
		
		for(int i = data.length-1;i >= top2;i--){
			result[index++] = data[i];
		}
		
		return result;
	}

}
