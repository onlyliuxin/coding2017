package com.coding.basic;

public class LinkedList<T> implements List<T> {
/*
 * LinkedList的底层是一个双线链表。
 * 
 */
	private Node<T> head;
	
	private int size;
	
	public LinkedList(){
		head = new Node<T>();
		head.previous = head.next = head;
	}
	
	public void add(T o){
		addLast(o);		
	}
	
	public void add(int index , Object o){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
		}
		Node node = head;
		for(int i = 0; i < index; i++){
			node = node.next;
		}
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = node.next;
		node.next = newNode;
		size++;
	}
	public T get(int index){
		if(index > size-1){
			return null;
		}else{
			Node node = head;
			for(int i = 0; i < index; i++){
				node = node.next;
			}
			return (T)node.data;
		}		
	}
	public T remove(int index){
		if(index > size-1){
			return null;
		}else if(index == 0){
			T obj = (T)head.data;
			head = head.next;
			size--;
			return obj;
		}else{
			Node node = head;

			for(int i = 0; i < index-1; i++){
				node = node.next;
			}
			T obj = (T)node.next.data;
			node.next = node.next.next;
			size--;
			return obj;
		}		
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node newHead = new Node();
		newHead.data = o;
		newHead.next = head;
		head = newHead;
		size++;
	}
	public void addLast(Object o){
		Node node = head;
		while(node.next != null){
			node = node.next;
		}
		Node newNode = new Node();
		newNode.data = o;
		node.next = newNode;
		size++;
	}
	public Object removeFirst(){
		return remove(0);
	}
	public Object removeLast(){		
		return remove(size);
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static class Node<T>{
		T data;
		Node<T> previous;
		Node<T> next;		
		
		Node(T t, Node previous, Node next){
			this.data = t;
			this.previous = previous;
			this.next = next;
		}
		Node(T t){
			this.data = t;
		}
		Node(){
			
		}
	}
}
