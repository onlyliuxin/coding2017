package com.coding.basic;

public class Queue
{
	private LinkedList list = new LinkedList();
	
	public void enQueue(Object o)
	{
		list.add(o);
	}

	public Object deQueue()
	{
		Object rt = list.removeFirst();
		return rt;
	}

	public boolean isEmpty()
	{
		return 0==list.size();
	}

	public int size()
	{
		return list.size();
	}
	
}