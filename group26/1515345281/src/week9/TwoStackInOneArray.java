package week9;

import java.util.Arrays;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，
 * 直到两栈顶指针相遇，则扩容。
 *
 */
public class TwoStackInOneArray {
	
	Object[] data;
	
	private int indexOne=-1;
	private int indexTwo=-1;
	
	public TwoStackInOneArray(int capacity){
		data=new Object[capacity];
		indexTwo=data.length;
	}
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		if(indexOne+1 == indexTwo){
			ensureCapacity();//扩容
		}
		data[++indexOne]=o;
	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if(indexOne < 0){
			throw new RuntimeException("The stackOne isEmpty,can't execute pop");
		}
		
		return data[indexOne--];
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		return data[indexOne];
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		if(indexOne == indexTwo-1){
			ensureCapacity();//扩容
		}
		data[--indexTwo]=o;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		if(indexTwo == data.length){
			throw new RuntimeException("The stackTwo isEmpty,can't execute pop");
		}
		return data[indexTwo++];
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		return data[indexTwo];
	}
	
	private void ensureCapacity(){
		Object[] descData=new Object[data.length*2];		
		System.arraycopy(data, 0, descData, 0, indexOne+1);
		
		int stack2Size=data.length-indexTwo;
		int newIndexTwo=descData.length-stack2Size;
		
		
		System.arraycopy(data, indexTwo, descData, newIndexTwo, stack2Size);
		
		indexTwo=newIndexTwo;
		data=descData;
	}

	public Object[] stack1ToArray() {
		return Arrays.copyOf(data,indexOne+1);
	}
	
	public Object[] stack2ToArray(){
		int size=data.length-indexTwo;
		Object[] o=new Object[size];
		
		for(int i=0;i<o.length;i++){
			o[i]=data[data.length-1-i];
		}
		
		return o;
	}
}