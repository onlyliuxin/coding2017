package com.coding.basic;

public class LinkedList implements List,Iterator {
	
	private Node head;
	private Node last;
	private int size = 0;
	
	public LinkedList() {
		head = new Node();
	}

	@Override
	public void add(Object o) {
		Node newNode = new Node();
		Node last = head;
		while(last.next != null){
			last = last.next;
		}
		last.next = newNode;
		newNode.prev = last;
		last = newNode;
		size++;
	}

	@Override
	public void add(int index, Object o) {
		Node newNode = new Node();
		Node indexNode = head ;
		int i = 0;
		while(i == index){
			indexNode = indexNode.next;
			i++;
		}
		Node indexNextNode = indexNode.next;
		indexNode.next = newNode;
		newNode.prev = indexNode;
		newNode.next = indexNextNode;
		indexNextNode.prev = newNode;
		size ++;
	}

	@Override
	public Object get(int index) {
		Node indexNode = head;
		int i = 0;
		while(i == index){
			indexNode = indexNode.next;
			i++;
		}
		return indexNode;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object remove(int index) {
		Node indexNode = head ;
		int i = 0;
		while(i == index){
			
			indexNode = indexNode.next;
			i++;
		}
		Object o = indexNode.prev;
		Node indexNextNode = indexNode.next;
		Node indexPrevNode = indexNode.prev;
		
		indexNextNode.prev = indexPrevNode;
		indexPrevNode.next = indexNextNode;
		
		indexNode.next = null;
		indexNode.prev = null;
		size--;
		return o;
	}

	public void addFirst(Object o){
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = head;
		head.prev = newNode;
		head = newNode;
		size ++;
	}
	public void addLast(Object o){
		Node newNode = new Node();
		newNode.data = o;
		newNode.prev = last;
		last.next = newNode;
		last = newNode;
		size ++;
	}
	public Object removeFirst(){
		Node ret = head;
		head = head.next;
		head.prev = null;
		size--;
		return ret;
	}
	public Object removeLast(){
		Node ret = last;
		last = last.prev;
		last.next = null;
		size--;
		return ret;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		Node prev;
	}

	private Node index = head;
	@Override
	public boolean hasNext() {
		return index != null;
	}

	@Override
	public Object next() {
		Node tem = index;
		index = index.next;
		return tem;
	}
}
