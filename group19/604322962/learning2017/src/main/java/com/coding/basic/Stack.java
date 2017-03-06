package main.java.com.coding.basic;

import org.junit.Test;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class Stack {
	private ArrayList elementData = new ArrayList();
	private int top = -1;
	public void push(Object o){
		elementData.add(++top);
	}
	public Object pop(){
		Object obj = elementData.get(top--);
		elementData.remove(top);
		return obj;
	}
	public Object peek(){
		if (size() == 0)
			throw new EmptyStackException();
		return elementData.get(size()-1);
	}
	public boolean isEmpty(){
		return size()==0;
	}
	public int size(){
		return top+1;
	}
	@Test
	public void test(){
		int[] elementData = {1,2,4,5,6,7};
		int[] newArray = new int[10];
		int index = 2;
		System.arraycopy(elementData,index,newArray,index+1,4);
		newArray[2] = 3;
		System.out.println(newArray.length);
		for (int data :
				newArray) {
			System.out.println(data);
		}
	}
}
