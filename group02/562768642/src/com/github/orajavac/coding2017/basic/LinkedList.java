package com.github.orajavac.coding2017.basic;

public class LinkedList implements List,Iterator {
	
private Node head;

private Node current;
	
	private int size=0;
	
	public void add(Object o){
		Node n = new Node();
		n.next=this.head;
		n.data=o;
		this.head=n;
	}
	public void add(int index , Object o){
		
	}
	public Object get(int index){
		Node c = this.head;
		int i=0;
		while(c!=null){
			i++;
			if (index==i){
				return c.data;
			}
			c=c.next;
		}
		return null;
	}
	public Object remove(int index){
		return null;
	}
	
	public int size(){
		size=0;
		Node c = this.head;
		while(c!=null){
			size++;
			c=c.next;
		}
		return size;
	}
	
	public void addFirst(Object o){
		if (this.head==null){
			this.head = new Node();
			this.head.data=o;
		}
	}
	public void addLast(Object o){
		add(o);
	}
	public Object removeFirst(){
		int s=size();
		int index=0;
		Node c = this.head;
		while(c!=null){
			index=s--;
			if (index==2){
				System.out.println(c.next.data);
				
				break;
			}
			c=c.next;
		}
		return null;
	}
	public Object removeLast(){
		Node e = this.head.next;
		this.head=null;
		this.head=e;
		return null;
	}
	
	public void listNode(){
		Node c = this.head;
		while(c!=null){
			System.out.print(c.data+ " -> ");
			c=c.next;
		}
		System.out.println();
	}
	public Iterator iterator(){
		LinkedList l = new LinkedList();
		l.head=this.head;
		return l;
	}
	
	public boolean hasNext(){
		current = head;
		if (current!=null){
			head = current.next;
			return true;
		}
		return false;
	}
	
	public Object next(){
		return current.data;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
	}
}
