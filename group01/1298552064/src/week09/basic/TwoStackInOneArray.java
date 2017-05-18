package week09.basic;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author gallenzhang
 *
 */
public class TwoStackInOneArray {
	private int DEFAULT_SIZE = 10;
	
	Object[] data = null;
	
	private int size1;
	
	private int size2;
	
	public TwoStackInOneArray(int size){
		if(size <= 0){
			throw new IllegalArgumentException("size can not be negative or zero");
		}
		data = new Object[size];
	}
	
	public TwoStackInOneArray(){
		data = new Object[DEFAULT_SIZE];
	}

	/**
	 * 向第一个栈中压入元素
	 * 
	 * @param o
	 */
	public void push1(Object o) {
		ensureCapacity(size1 + size2 + 1);
		data[size1] = o;
		size1 ++;
	}

	/**
	 * 从第一个栈中弹出元素
	 * 
	 * @return
	 */
	public Object pop1() {
		checkStack1Empty();
		Object obj = data[size1 - 1];
		data[size1 - 1] = null;
		size1--;
		return obj;
	}

	/**
	 * 获取第一个栈的栈顶元素
	 * 
	 * @return
	 */

	public Object peek1() {
		checkStack1Empty();
		return data[size1-1];
	}
	
	/**
	 * 第一个栈是否为空
	 * @return
	 */
	public boolean isStack1Empty(){
		return size1 == 0 ;
	}
	
	/**
	 * 获取第一个栈的大小
	 * @return
	 */
	public int size1(){
		return size1;
	}

	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o) {
		ensureCapacity(size1 + size2 + 1);
		data[data.length - size2 - 1] = o;
		size2 ++;
	}

	/**
	 * 从第二个栈弹出元素
	 * 
	 * @return
	 */
	public Object pop2() {
		checkStack2Empty();
		Object obj = data[data.length - size2];
		data[data.length - size2] = null;
		size2 --;
		return obj;
	}

	/**
	 * 获取第二个栈的栈顶元素
	 * 
	 * @return
	 */

	public Object peek2() {
		checkStack2Empty();
		if(size2 == 0){
			throw new EmptyStackException();
		}
		return data[data.length - size2];
	}
	
	public boolean isStack2Empty(){
		return size2 == 0 ;
	}
	
	public int size2(){
		return size2;
	}
	
	private void ensureCapacity(int minCapacity){
		int capacity = data.length;
		if (minCapacity > capacity) {
			capacity += capacity / 2;
			grow(capacity);
		}
	}
	
	private void checkStack1Empty(){
		if(size1 == 0){
			throw new EmptyStackException();
		}
	}
	
	private void checkStack2Empty(){
		if(size2 == 0){
			throw new EmptyStackException();
		}
	}
	
	private void grow(int capacity){
		data = Arrays.copyOf(data, capacity);
		System.arraycopy(data, size1, data, data.length - size2, size2);
		for(int i = size1; i < data.length - size2;i++){
			data[i] = null;
		}
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer("[");
		for(int i=0; i < data.length; i++){
			if(i == data.length - 1){
				sb.append(data[i]);
			}else{
				sb.append(data[i]);
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
}
