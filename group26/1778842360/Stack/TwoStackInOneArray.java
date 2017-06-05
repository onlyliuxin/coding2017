package stack;

import java.util.Arrays;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	Object[] data = new Object[10];
	//第一个栈向后的指针
	int toLast=-1;
	//第二个栈向前的指针
	int toHead=data.length;
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	
	public void push1(Object o){
		
		//首先判断容量
		ensureCapacity();
		data[++toLast]=o;
	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		
		if(toLast==-1)
		{
			throw new RuntimeException("stack1 is enmpty!");
		}
		Object o=data[toLast];
		data[toLast]=null;
		toLast--;
		return o;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		
		if(toLast==-1)
		{
			throw new RuntimeException("stack1 is enmpty!");
		}
		return data[toLast];
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		ensureCapacity();
		data[--toHead]=o;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		
		if(toHead==data.length)
		{
			throw new RuntimeException("stack2 is empty!");
		}
		Object o=data[toHead];
		data[toHead]=null;
		toHead++;
		return o;
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		
		if(toHead==data.length)
		{
			throw new RuntimeException("stack2 is empty!");
		}
		return data[toHead];
	}
	
	private void ensureCapacity() {
		if(toHead-toLast>1)
		{
			return;
		}else{
			//将原数组扩容为两倍
			Object[] newArray=new Object[data.length*2];
			//首先将栈1的元素移入新数组
			System.arraycopy(data, 0, newArray, 0, toLast+1);
			
			//栈2的元素数
			int stack2Num=data.length-toHead;
			int newToHead=newArray.length-stack2Num;
			System.arraycopy(data, toHead, newArray, newToHead, stack2Num);
			toHead=newToHead;
			data=newArray;
		}
	}
	
	public Object[] stack1ToArray()
	{
		return Arrays.copyOf(data, toLast+1);
	}
	
	public Object[] stack2ToArray()
	{
		int size=data.length-toHead;
		Object[] newArray=new Object[size];
		int j=0;
		for(int i=data.length-1;i>=toHead;i--)
		{
			newArray[j++]=data[i];
		}
		return newArray;
	}
}

