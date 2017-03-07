package com.xusheng.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
	private int size;
	private int modCount=0;
	private Node<T> beginMarker;
	private Node<T> endMarker;
	
	public MyLinkedList() {
		doClear();
	}
	
	public void clear(){
		doClear();
	}

	public void doClear(){
		beginMarker = new Node<T>(null,null,null);
		endMarker = new Node<T>(null,beginMarker,null);
		beginMarker.next = endMarker;
		
		this.size = 0;
		this.modCount++;
	}

	public int size(){
		return this.size;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	public void add(int index,T element){
		addBefore(getNode(index),element);
	}
	
	public boolean add(T element){
		add(size,element);
		return true;
	}
	
	public T get(int index){
		return getNode(index).data;
	}
	
	public T set(int index,T element){
		Node<T> node = getNode(index);
		T oldElement = node.data;
		node.data = element;
		return oldElement;
	}

	public T remove(int index){
		return remove(getNode(index));
	}
	
	//此方法用于判断集合中是否含有输入的元素
	public boolean contains(T element){
		for(int i=0;i<size;i++){
			if(element.equals(getNode(i).data)){
				return true;
			}
		}
		return false;
	}
	
	//该方法用于index对应的元素与后一个元素进行位置调换
	public void change(int index){
		Node<T> node = getNode(index);
		Node<T> beforep,afterp;
		beforep = node.prev;
		afterp = node.next;
		
		beforep.next = afterp;
		afterp.prev = beforep;
		
		node.next = afterp.next;
		node.prev = afterp;
		
		afterp.next = node;
		afterp.prev = beforep;
		
	}
	
	private T remove(Node<T> delNode){
		delNode.next.prev = delNode.prev;
		delNode.prev.next = delNode.next;
		size--;
		modCount++;
		return delNode.data;
	}
	
	private void addBefore(Node<T> p,T element){
		Node<T> newNode = new Node<T>(element,p.prev,p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		this.size++;
		this.modCount++;
	}
	
	private Node<T> getNode(int index){
		Node<T> p;
		if(index>=0 && index<=size){
			if(index<(size/2)){
				p = beginMarker.next;
				for(int i=0;i<index;i++){
					p = p.next;
				}
			}else{
				p = endMarker;
				for(int i=size;i>index;i--){
					p = p.prev;
				}
			}
		}else{
			throw new IndexOutOfBoundsException();
		}
		return p;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	
	private class Node<T>{
		public T data;
		public Node<T> prev;
		public Node<T> next;
		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
	
	private class LinkedListIterator implements Iterator<T>{

		private Node<T> current = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;
		
		@Override
		public boolean hasNext() {
			return current != endMarker;
		}

		@Override
		public T next(){
			if(modCount != expectedModCount){
				throw new ConcurrentModificationException();
			}
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			T n = current.data;
			current = current.next;
			okToRemove = true;
			return n;
		}
		
		public void remove(){
			if(modCount != expectedModCount){
				throw new ConcurrentModificationException();
			}
			if(!okToRemove){
				throw new IllegalStateException();
			}
			MyLinkedList.this.remove(current.prev);
			size--;
			expectedModCount++;
			okToRemove = false;
		}
	}
}
