package com.mycoding;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	private Node head;
	private int size;
	
	
	public LinkedList()  {
		this.head = null;
		this.size = 0;
	}
	//添加新元素
	public void add(Object o){
	Node newNode = new Node(o);
		if(head == null) {
			head = newNode;
		} else {
				Node nextNode = head;
				while(nextNode.next != null) {
				nextNode = nextNode.next;
			}
				nextNode.next =  new Node(o);				
		}
		size++;		
	}

	//在指定位置添加新元素
	public void add(int index , Object o){
		checkAddElementIndex(index);	
		Node addNode = head;
		if(index == 0) {
			addFirst(o);
		} else {
			for(int i=0;i<index-1;i++) {
				addNode = addNode.next;
			}
			Node insertNode = new Node(o);
			insertNode.next = addNode.next;		
			addNode.next = insertNode;
			size++;
		}
	}

	public Object get(int index){
		checkGetElementIndex(index);		
		Node getNode = head;
		for(int i=0;i<index;i++) {
			getNode = getNode.next;
		}
		return getNode.data;
	}	
	
	public Object remove(int index){
		checkGetElementIndex(index);
		Node indexNode = head;
		
		if(index == 0) {
			Node laterNode = head;
			while (indexNode.next != null) {
				laterNode.data = indexNode.next.data;
				laterNode = laterNode.next;
				indexNode = indexNode.next;
			}
			size--;
			return laterNode.data;
		} else {
			if(index > 0 && index < size()) {		
				for (int i=0;i<index-1;i++) {
					indexNode = indexNode.next;
				}
				indexNode.next = indexNode.next.next; 
				size--;		
			}	
			return indexNode.data;
		}		
	}
	
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node addFirstNode = new Node(o);
		addFirstNode.next = head;
		head = addFirstNode;	
		size++;
	}
	
	
	public void addLast(Object o){
		add(o);
	}
	
	
	public Object removeFirst(){
		isEmpty();
		return remove(0);
	}
	public Object removeLast(){	
		return remove(size()-1);	
	}
	
	public Iterator iterator(){
		return new LinkedListIterator(this);
	}
	private class LinkedListIterator implements Iterator {
		@SuppressWarnings("unused")
		LinkedList linked = null;
		int cursor=0;
		private LinkedListIterator(LinkedList linked) {
			this.linked = linked;
		}
		
		@Override
		public boolean hasNext() {			
			return cursor < size;
		}
		
		@Override
		public Object next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}		
			return get(cursor++);
		}	
	}
	
	public void checkGetElementIndex(int index) {
		if (index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}
	}
	
	public void checkAddElementIndex(int index) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
	}	
	public void isEmpty() {
		if(head == null) {
			throw new NoSuchElementException();
		}
	}
		
	private static  class Node{
		Object data;
		Node next;
		
		public Node(Object o){
			data = o;
			next = null;
		}	
	}
	

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("[");
		for(int i=0; i<size; i++){
			str.append( this.get(i) + ",");
		}
		str.deleteCharAt(str.length()-1);	
		str.append("]");
		return str.toString();
	}
}
