package stack;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	Object[] data = new Object[10];
	
	private int size1 = 0;
	private int size2 = 0;
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		checkCapacity();
		data[size1++] = o;
	}
	
	@Override
	public String toString() {
		return "TwoStackInOneArray [data=" + Arrays.toString(data) + "]";
	}

	public int size1() {
		return size1;
	}
	
	public int size2() {
		return size2;
	}
	
	private void checkCapacity() {
		if (size() + 1 >= data.length) {
			grow();
		}
	}
	private int size() {
		return size1 + size2;
	}

	private void grow() {
		int newCapacity = size() * 2;
		Object[] target = new Object[newCapacity];
		System.arraycopy(data, 0, target, 0, size1);
		System.arraycopy(data, data.length - size2, target, target.length - size2, size2);
		data = target;	
	}

	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		
		checkEmpty(size1);
		Object o = data[size1-1];
		data[size1-1] = null;
		size1--;
		return o;
	
	}
	
	private void checkEmpty(int size) {
		if (size <= 0) {
			throw new NoSuchElementException("stack is empty");
		}
	}

	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		
		checkEmpty(size1);
		return data[size1-1];
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		checkCapacity();
		data[data.length - size2 - 1] = o;
		size2++;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		checkEmpty(size2);
		Object o = data[data.length - size2];
		data[data.length - size2] = null;
		size2--;
		return o;
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		checkEmpty(size2);
		return data[data.length - size2];
	}
	
}
