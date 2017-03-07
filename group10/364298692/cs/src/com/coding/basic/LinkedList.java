package com.coding.basic;

import java.util.Collection;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
/*
 * LinkedList的底层是一个双向循环链表。head节点中不储存数据。
 * 
 */
	private Node<T> head;
		
	private int size;
	
	public LinkedList(){
		head = new Node<T>();
		head.previous = head.next = head;
	}
	
	public LinkedList(Collection<? extends T> c){
		this();
		addAll(c);
	}
	
	public boolean addAll(Collection<? extends T> c) {
		return addAll(size, c);
		
	}
	/**
	 * 
	 * @param index 指定Collection中插入的第一个元素的位置
	 * @param c
	 * @return
	 */
	public boolean addAll(int index, Collection<? extends T> c){
		checkIndex(index);
		
		Object[] a = c.toArray();
		int numNew = a.length;
		if(numNew==0){
			return false;
		}
		//新的节点要接在successor之前，接在predecessor之后
		Node<T> successor = (index==size ? head : entry(index));
		Node<T> predecessor = successor.previous;
		for(int i=0; i<numNew; i++){
			Node node = new Node((T)a[i], successor, predecessor);
			predecessor.next = node;
			predecessor = node;
		}
		successor.previous = predecessor;
		size += numNew;		
		return true;
	}

	private void checkIndex(int index) {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Index: "+index+ ", Size: "+size);
		}
	}

	private Node<T> entry(int index) {
		checkIndex(index);
		
		Node<T> node = head;
		if(index < (size >> 1)){
			for(int i=0; i<index; i++){
				node = node.next;
			}
		}else{
			for(int i=size; i>index; i--){
				node = node.previous;
			}
		}		
		return node;
	}

	public boolean add(T o){
		addbefore(o, head);	
		return true;
	}
	
	private Node<T> addbefore(T o, Node<T> node) {
		Node<T> newNode = new Node(o, node, node.previous);
		node.previous.next = newNode;
		node.previous = newNode;
		size++;
		return newNode;
	}

	public boolean add(int index , T o){
		checkIndex(index);
		
		Node node = head;
		for(int i = 0; i < index; i++){
			node = node.next;
		}
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = node.next;
		node.next = newNode;
		size++;
		return true;
	}
	public T get(int index){
		Node<T> node = entry(index);
		return (T)node.data;	
	}
	
	public T remove(int index){
		Node<T> node = entry(index);		
		T t = remove(node);
				
		return t;		
	}
	
	public T remove(Node<T> node){
		if(node == head){
			throw new NoSuchElementException();
		}		
		T t = node.data;
		node.previous.next = node.next;
		node.next.previous = node.previous;
		node.previous = node.next = null;
		size--;
		
		return t;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(T o){
		addbefore(o, head.next);
	}
	
	public void addLast(T o){
		addbefore(o, head);
	}
	
	public T removeFirst(){
		return remove(0);
	}
	
	public T removeLast(){		
		return remove(size-1);
	}
	
	public Iterator iterator (){
		return new LinkedListIterator<T>();
	}
	
	private class LinkedListIterator<T> implements Iterator{
		//最近一次返回的节点
		private Node<T> lastReturn;
		
		private int index;
		
		
		
		@Override
		public boolean hasNext() {			
			return index < size;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
		
	private static class Node<T>{
		T data;
		Node<T> previous;
		Node<T> next;		
		
		Node(T t, Node next, Node previous){
			this.data = t;
			this.previous = previous;
			this.next = next;
		}
		Node(){
			
		}
	}
}
