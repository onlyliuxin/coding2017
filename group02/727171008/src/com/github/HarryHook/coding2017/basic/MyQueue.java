/*
 * created by Harry 2017-2-22 13:06:43
 * 实现简单的队列
 */
package com.github.HarryHook.coding2017.basic;
import java.util.*;
public class MyQueue 
{	
	private MyArrayList elementData = new MyArrayList();
	private int size = 0;
	//入队
	public void enQueue(Object o)
	{		
		elementData.add(o);
		size++;
	}
	//出队
	public Object deQueue()
	{
		if(isEmpty())
			throw new NoSuchElementException();
		Object Data = elementData.remove(0);
		size--;
		return Data;
	}
	//判断队列是否为空
	public boolean isEmpty()
	{
		return size() == 0;
	}
	//队列中元素个数
	public int size()
	{
		return size;
	}
	public static void main(String[] args)
	{
		MyQueue mq = new MyQueue();
		mq.enQueue(1);
		mq.enQueue(2);
		mq.enQueue(3);
		mq.enQueue(4);
		System.out.println("队列出栈，出栈元素为： " + mq.deQueue());
		System.out.println("队列中元素个数： " + mq.size());
		System.out.println("队列出栈，出栈元素为： " + mq.deQueue());
		System.out.println("队列中元素个数： " + mq.size());
		System.out.println("队列出栈，出栈元素为： " + mq.deQueue());
		System.out.println("队列中元素个数： " + mq.size());
		System.out.println("队列出栈，出栈元素为： " + mq.deQueue());
		System.out.println("队列中元素个数： " + mq.size());
		//System.out.println("队列出栈，出栈元素为： " + mq.deQueue());
		//System.out.println("队列中元素个数： " + mq.size());
	}
}
