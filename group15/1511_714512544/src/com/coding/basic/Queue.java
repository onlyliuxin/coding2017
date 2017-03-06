package com.coding.basic;

import java.util.NoSuchElementException;

/**
 * 队列,先进先出
 */
public class Queue {
	private LinkedList list = new LinkedList();  //链表数据结构

	//入队
	public void enQueue(Object o){
		list.addLast(o);
	}

	//出队
	public Object deQueue(){
		if(list.size() == 0) throw new NoSuchElementException("队列无元素");
		return list.removeFirst();
	}

	//是否为空
	public boolean isEmpty(){
		return list.size() == 0;
	}

	//队列内元素
	public int size(){
		return list.size();
	}

	//迭代器
	public Iterator iterator(){
		return list.iterator();
	}

}
