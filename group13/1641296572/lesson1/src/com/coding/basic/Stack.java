package com.coding.basic;

import java.util.EmptyStackException;

public class Stack
{
	private ArrayList elementData = new ArrayList();

	public void push(Object o)
	{
		elementData.add(elementData.size(), o);
	}

	public Object pop()
	{
		int size = elementData.size();
		if(size==0)
		{
			throw new EmptyStackException();
		}
		
		Object rt = elementData.get(size-1);
		elementData.remove(size-1);
		return rt;
	}

	public Object peek()
	{
		if(elementData.size()==0)
		{
			throw new EmptyStackException();
		}
		
		return elementData.get(elementData.size()-1);
	}

	public boolean isEmpty()
	{
		return 0==elementData.size();
	}

	public int size()
	{
		return elementData.size();
	}
	
	public static void main(String []args)
	{
		Stack st = new Stack();
		System.out.println("is Empty:"+ st.isEmpty());
		for(int i=0;i<10;i++)
		{
			st.push("s="+ i);
		}
		
		System.out.println("is Empty:"+ st.isEmpty());
		System.out.println(st.peek());
		for(int i=0;i<10;i++)
		{
			System.out.println("pop->" + st.pop());
		}
		System.out.println("is Empty:"+ st.isEmpty());
	}
	
	
}