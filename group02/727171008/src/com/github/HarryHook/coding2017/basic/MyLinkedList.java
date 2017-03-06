/*
 * created by Harry 2017-2-21 14:43:41
 * 实现简单的LinkedList
 */

package com.github.HarryHook.coding2017.basic;

public class MyLinkedList implements List 
{
	private Node head = null;	//头指针
	private int size = 0;
	private static  class Node
	{
		Object data;
		Node next;
	}
	public void add(Object o) 
	{
		addLast(o);
	}
	//在指定位置添加元素
	public void add(int index , Object o)
	{
		
		if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);	
		//存在插入头结点的情况
		if(index == 0)
			addFirst(o);
		else
		{		//即 index != 0 的情况
				// p保存待插入节点的前一节点，x指向要插入的节点
				Node x = head; 
				Node p = null;
				int i = 0;
				while(i < index)
				{
					p = x;
					x = x.next;
					i++;
				}
				Node n = new Node();
				p.next = n;
				n.next = x;
				n.data = o;	
				size++;
		}
		
	}
	//返回指定位置元素
	public Object get(int index)
	{
		Node x = head; 
		int i = 0;
		while(i < index && x != null)
		{
			x = x.next;
			i++;
		}
		return x.data;
	}
	
	//移除指定位置节点
	public Object remove(int index)
	{
		if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		//先判断是否是头节点
		if( index == 0)
		{			
			return removeFirst();		
		}
		else
		{
			Node x = head; 
			Node pre = null;
			int i = 0;
			while(i < index)
			{ 
				pre = x;
				x = x.next; 
				i++;	
			}
			Object Data = pre.next.data;
			pre.next = x.next;
			x = null;
			size--;
			return Data;				
		}

	}
	//头部添加节点
	public void addFirst(Object o)
	{
		Node n = new Node();
		n.next = head;
		head = n;
		n.data = o;
		size++;
	}
	//尾部添加节点
	public void addLast(Object o)
	{
		 if (head == null)
		 {
			 head = new Node();
			 head.data = o;
		 }
		else
	    {
	    	Node x = head; 
			while(x.next != null)		
			{
				x = x.next;
			}
			Node n = new Node();
			x.next = n;
			n.next = null;
			n.data = o;
	    }
		size++;
	}
	//移除第一个节点
	public Object removeFirst()
	{
		Node n = head;
		Object Data = n.data;
		head = head.next;
		n = null;
		size--;
		return Data;	
	}
	
	//移除最后一个节点
	//removeLast()方法存在bug
	public Object removeLast()
	{
		Node x = head; 
		Node p = null;
		if(x.next == null)
		{
			return removeFirst();
		}
		else
		{
			while(x.next != null)
			{
				p = x;
				x = x.next;
			}
			Object Data = x.data;
			p.next = null;
			x = null;       //删除最后一个节点
			size--;
			return Data;	
		}
	}
	public int size(){
		return size;
	}
	public Iterator iterator()
	{
		return new MyLinkedListIterator();
	}
	private class MyLinkedListIterator implements Iterator
	{
		private int cursor = 0;      //记录索引位置         
		public boolean hasNext()
		{
			return cursor != size;    
		}
		public Object next() 
		{  
			Object next = get(cursor);  
			cursor ++;  
			return next;  
		}
	}
	public static void main(String[] args)
	{
		MyLinkedList myList = new MyLinkedList();
		myList.add(3);
		myList.add(5);
		myList.add(0, 4);
		myList.add(2, 7);
		myList.addFirst(1);
		myList.addLast(6);
		
		Print(myList);
	
		System.out.println("当前指定位置元素： " + myList.get(1));
		System.out.println("移除指定位置元素： " + myList.remove(4));
		Print(myList);

		System.out.println("移除第一个节点元素： " + myList.removeLast());
		Print(myList);
		System.out.println("移除最后一个节点元素： " + myList.removeLast());
		Print(myList);
		System.out.println("移除最后一个节点元素： " + myList.removeLast());
		Print(myList);
		System.out.println("移除最后一个节点元素： " + myList.removeLast());
		Print(myList);
		System.out.println("移除最后一个节点元素： " + myList.removeLast());
		Print(myList);

	}
	public static void Print(MyLinkedList myList)
	{
		Iterator it = myList.iterator();	
		System.out.println("对链表中的元素进行打印：");
		while(it.hasNext())
			System.out.print(it.next() + " ");
		System.out.println("");
		System.out.println("当前元素个数： " + myList.size()); 
		System.out.println("");
	}

	
}
