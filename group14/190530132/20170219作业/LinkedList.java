package com.coding.basic;
import java.util.NoSuchElementException;

//以下实现的是单向链表
public class LinkedList {
	
	private int size = 0;
	
	private Node head;
	
	
	//在不指定index的情况下，默认在链表的尾部添加新节点
	public void add(Object o){
		addLast(o);
	}
	
	
	//在指定index的情况下, 
	public void add(int index , Object o){
		
		//index越界检查
		if (index < 0 || index >= size) {
		    throw new IndexOutOfBoundsException("Index:" + index + ",size:" + size);
		}
		
		Node n = new Node();
		n.data = o;
		Node m = get(index);
		n.next = m.next;
		m.next = n;
		size = size + 1;
	}
	
	public Node get(int index){
		//index越界检查
		if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ",size:" + size);
        }
        
		Node n = head.next;
		int count = 0;
		while(count<=index){
			if(count==index){
				return n;
			}
			n = n.next;
			count++;
		}
		
        return null;
	}
	
	public void remove(int index){
		if(index<0||index>=size)
			throw new IndexOutOfBoundsException("Joy Index "+index+", Size: "+size);
		
		Node d = get(index);
		Node pred = get(index-1);
		pred.next = d.next;
		size = size - 1;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node n = new Node();
		n.data = o;
		
		//避免空链表
		if (head==null) head=new Node();
		
		n.next = head.next;
		head.next = n;
		size = size + 1;
	}
	
	public void addLast(Object o){
		Node n = new Node();
		n.data = o;
		
		//避免空链表
		if (head==null) head = new Node();
		
		//从头部往后顺序查找，找到尾部就添加
		Node m = head;
		while (m.next != null){
			m = m.next;
		}
		n.next = m.next;
		m.next = n;
		size = size + 1;
	}
	
	public Object removeFirst(){
		if(head==null||head.next==null)
			throw new NoSuchElementException();
		Node d = head.next;
		head.next = d.next;
		size = size - 1;
        return d.data;
	}
	
	public Object removeLast(){
		if(head==null||head.next==null)
			throw new NoSuchElementException();
		
		Node m = head;
		Node n = head.next;
		while(n.next != null){
			m = n;
			n = n.next;
		}
		size = size - 1;
		return m.data;	
	}
	
	//public Iterator iterator(){
	//	return null;
	//}
	
	private static class Node{
		Node next;
		Object data;		
	}

}
