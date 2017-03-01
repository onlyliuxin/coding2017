package com.ace.coding;

public class LinkedList implements List {
	private Node head = null;
	private int size = 0;
	
	public void add(Object o){
		Node newNode = new Node();
		newNode.data = o;
		if(head == null){
			head = newNode;
		} else {
			Node pNode = head;
			while(pNode.next != null){
				pNode = pNode.next;
			}
			pNode.next = newNode;
		}
		size++;
	}
	
	public void add(int index , Object o){
		checkLinkedListIndex(index);
		
		Node newNode = new Node();
		newNode.data = o;
		Node pNode = getNode(index);
		newNode.next = pNode.next;
		pNode.next = newNode;
		
		size++;
	}
	
	private Node getNode(int index){
		Node pNode = head;
		for(int i = 0; i < index; i++){
			pNode = pNode.next;
		}
		return pNode;
	}
	
	public Object get(int index){
		Node pNode = getNode(index);
		return pNode.data;
	}
	public Object remove(int index){
		checkLinkedListIndex(index);
		
		Node pNode = head;
		for(int i = 0; i < index - 1; i++){
			pNode = pNode.next;
		}
		Node tempNode = getNode(index);
		pNode.next = tempNode.next;
		size--;
		return tempNode.data;
	}
	
	
	public void addFirst(Object o){
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = head;
		head = newNode;
		size++;
	}
	public void addLast(Object o){
		Node newNode = new Node();
		newNode.data = o;
		Node pNode = getNode(size() - 1);
		pNode.next = newNode;
		size++;
	}
	public Object removeFirst(){
		Node pNode = head;
		head = pNode.next;
		size--;
		return pNode.data;
	}
	public Object removeLast(){
		Object obj = remove(size() - 1);
		return obj;
	}
	
	public int size(){
		return size;
	}
	
	private void checkLinkedListIndex(int index){
		if(index < 0 || index >= size()){
			throw new IndexOutOfBoundsException("The index " + index + " is invalid.");
		}
	}
	
	public Iterator iterator(){
		return null;
//		return new ListIterator();
	}
	
	/*private class ListIterator implements Iterator{
		private Node pNode = head;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return pNode.next != null;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			Object obj = pNode.data;
			pNode = pNode.next;
			return obj;
		}
		
	}*/
	
	private static class Node{
		Object data;
		Node next;
	}
}
