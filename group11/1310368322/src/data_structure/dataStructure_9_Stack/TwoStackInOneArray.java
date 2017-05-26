package dataStructure_9_Stack;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;
/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	
	Object[] data = new Object[10];
	int i = 0;// 第一个栈 的栈顶指针
	int j = data.length-1;// 第二个栈的栈顶指针
	public int getSize(){
		return data.length;
	}
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		ensureCapacity();
		System.out.println("i= " + i);
		data[i++] = o;
	}

	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if(i ==0 ){
			throw new RuntimeException("第一个栈为空");
		}
		
		return data[i--];
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		return data[i];
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		ensureCapacity();
		System.out.println("j==" + j);
		data[j--] = o;
	}
	/**b
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		return data[j++];
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		return data[j];
	}
	
	private void ensureCapacity() {
		System.out.println("ensureCapacity: " + i + "和" + j);
		if(i >= j){
			data = grow();
			j = data.length - j;
		}
		
	}

	private Object[] grow() {
		Object[] dataNew = new Object[data.length*2];
		System.arraycopy(data, 0, dataNew, 0, i);
		System.arraycopy(data, i, dataNew, dataNew.length-j, j);
		return dataNew;
		
	}
}
