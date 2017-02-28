package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	
	private int size;
	
	public void add(Object o){
		addLast(o);		
	}
	
	public void add(int index , Object o){
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
	public Object get(int index){
		if(index > size-1){
			return null;
		}else{
			Node node = head;
			for(int i = 0; i < index; i++){
				node = node.next;
			}
			return node.data;
		}		
	}
	public Object remove(int index){
		if(index > size-1){
			return null;
		}else if(index == 0){
			Object obj = head.data;
			head = head.next;
			size--;
			return obj;
		}else{
			Node node = head;
			//获得被删掉节点的前一个节点
			for(int i = 0; i < index-1; i++){
				node = node.next;
			}
			Object obj = node.next.data;
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
	
	
	private static class Node{
		Object data;
		Node next;		
	}
}
