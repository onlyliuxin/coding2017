package com.basic.datastructure;

public class LinkedList implements List{
	private Node first;
	private Node last;
	
	private int size;
	
	public void add(Object o){
		if(first == null){
			first = new Node(o,null);
		}
		size ++;
	}
	public void add(int index , Object o){
		
	}
	public Object get(int index){
		return null;
	}
	public Object remove(int index){
		return null;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		
	}
	public void addLast(Object o){
		
	}
	public Object removeFirst(){
		return null;
	}
	public Object removeLast(){
		return null;
	}
	
	
	private static class Node{
		Object item;
		Node next;
		Node(Object item, Node next){
			this.item = item;
			this.next = next;
		}
	}

}

