package com.github.fei9009.coding2017.basic;

public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	public void add(Object o){
		add(size, o);
	}
	
	public void add(int index , Object o){
		if (index > this.size || index < 0) {
			throw new IndexOutOfBoundsException("index " + index + "beyond the size " + size );
		}
		Node dummy = node(index);
		Node newNode = new Node(o);
		newNode.next = dummy;
		if (index == 0) {
			head = newNode;
		}
		else {
			Node before = node(index-1);
			before.next = newNode;
		}
		this.size++;
		
	}
	
	public Object get(int index){
		if (index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException("index " + index + "beyond the size " + size );
		}
		Node node = node(index);
		return node.data;
		
	}
	public Object remove(int index){
		if (index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException("index " + index + "beyond the size " + size );
		}
		Node delNode = node(index);
		if (index == 0)
			head = delNode.next;
		else {
			Node before = node(index-1);
			before.next = delNode.next;
		}
		size--;
		return delNode.data;
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		add(0, o);
	}
	public void addLast(Object o){
		add(o);
	}
	public Object removeFirst(){
		return remove(0);
	}
	
	public Object removeLast(){
		return remove(this.size -1);
	}
	public Iterator iterator(){
		return null;
	}
	
	Node node(int index) {
		Node x = head;
		for (int i=0; i<index; i++) {
			x = x.next;
		}
		return x;
	}
	
	private static  class Node{
		Object data;
		Node next;
		Node(Object o) {
			this.data = o;
			this.next = null;
		}
		
	}
}
