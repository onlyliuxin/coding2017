package com.github.miniyk2012.coding2017.basic;

public class LinkedList implements List {
	
	private Node head;
	private int size = 0;
	
	/**
	 * node接收的index一定是范围内的值，不可能越界
	 * @param index
	 * @return a Node
	 */
	Node node(int index) {
		Node x = head;
		for (int i=0; i<index; i++) {
			x = x.next;
		}
		return x;
	}
	
	public void add(Object o){
		add(size, o);
	}
	
	public void add(int index , Object o){
		checkPositionIndex(index);
		Node after = node(index);
		Node newNode = new Node(o, after);
		if (index == 0) {
			head = newNode;
		}
		else {
			Node before = node(index-1);
			before.next = newNode;
		}
		size++;
	}
	
	public Object get(int index){
		checkElementIndex(index);
		Node thisNode = node(index);
		return thisNode.data;
	}
	
	public Object remove(int index){
		checkElementIndex(index);
		Node toRemove = node(index);
		if (index == 0)
			head = toRemove.next;
		else {
			Node before = node(index-1);
			before.next = toRemove.next;
		}
		size--;
		return toRemove.data;
	}
	
	public int size(){
		return size;
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
		return remove(size-1);
	}
	
	public Iterator iterator(){
		Iterator iterator = new IteratorImp(this);
		return iterator;
	}
	
	private static class Node{
		Object data;
		Node next;
		
		Node(){}
		Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	private void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    }
	
	private void checkPositionIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    }
}
