package com.coding.basic;

public class LinkedList implements List 
{
	
	private Node head;
	private Node tail;
	
	public void add(Object o)
	{
		Node n = new Node(o);
		tail.next = n;
		tail = n;
	}
	
	public void add(int index , Object o)
	{
		Node n = new Node(o);
		getNode(index-1).next = n;
		n.next = getNode(index);
	}
	
	private Node getNode(int index)
	{
		Node n = head;
		int counter = 0;
		while (counter <index)
		{
			counter++;
			n = n.next;
		}
		return n;
	}
	
	public Object get(int index)
	{
		return getNode(index).data;
	}
	
	public Object remove(int index)
	{
		Object o = getNode(index).data;
		getNode(index-1).next = getNode(index+1);
		return o;
	}
	
	public int size()
	{
		Node n = head;
		int counter = 0;
		while (n.next != tail)
		{
			counter++;
			n = n.next;
		}
		counter++;
		return counter;
	}
	
	public void addFirst(Object o)
	{
		Node n = new Node(o);
		n.next = head;
		head = n;
	}
	
	public void addLast(Object o)
	{
		add(o);
	}
	
	public Object removeFirst()
	{
		Object o = head.data;
		head = head.next;
		return o;
	}
	
	public Object removeLast()
	{
		Object o = tail.data;
		Node n = head;
		while (n.next != tail)
		{
			n = n.next;
		}
		tail = n;
		return o;
	}
	
	
	public Iterator iterator()
	{
		return null;
	}
	
	
	private static class Node
	{
		Object data;
		Node next;
		
		public Node(Object data)
		{
			this.data = data;
		}
	}
}
