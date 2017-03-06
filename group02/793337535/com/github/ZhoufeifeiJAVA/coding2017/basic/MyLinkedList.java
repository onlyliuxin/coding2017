package com.github.ZhoufeifeiJAVA.coding2017.basic;

public class MyLinkedList implements List {
	
	private Node head,tail;
	private int size = 0;
	
	public void add(Object o){
		if(size == 0){
			head = new Node();
			tail = head;
			head.data = o;
		}
		else{
			Node newNode = new Node();
			newNode.data = o;
			newNode.next = null;
			tail.next = newNode;
			tail = newNode;
		}
		size ++;
	}
	public void add(int index , Object o)throws IndexOutOfBoundsException{
		if(index>size || index<0)
			throw new IndexOutOfBoundsException("the index is bigger than the size of MyLinkedList");
		Node newNode = new Node();
		newNode.data = o;
		if(index == 0){
			newNode.next = head;
			head = newNode;
		}
		else{
			int i = 0;
			Node pointer = head;
			while(i<index-1){
				i++;
				pointer = pointer.next;
			}
			newNode.next = pointer.next;
			pointer.next = newNode;	
		}
		size++;
	}
	public Object get(int index){
		if(index<0 || index>size-1)
			return null;
		Node pointer = head;
		while(index>0){
			pointer = pointer.next;
			index --;
		}
		return pointer.data;
		
	}
	public Object remove(int index)throws IndexOutOfBoundsException{
		if(index<0 || index>size-1)
			throw new IndexOutOfBoundsException("the index is not legal");
		Node pointer = head;
		if(index == 0){
			head = head.next;
			size --;
			return pointer.data;
		}
		else{
			while(index>1){
				pointer = pointer.next;
				index --;
			}
			Node temp = pointer.next;
			pointer.next = pointer.next.next;
			size --;
			return temp.data;
		}
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		add(0,o);
	}
	public void addLast(Object o){
		add(size,o);
	}
	public Object removeFirst(){
		return remove(0);
	}
	public Object removeLast(){
		return remove(size-1);
	}	
	
	private static class Node{
		Object data;
		Node next;
	}
	private class MyLinkedListIterator implements Iterator{
		private Node pointer;
		MyLinkedListIterator(){
			pointer = head;
		}
		public boolean hasNext() {
			if(pointer.next != null)
				return true;
			else
				return false;
		}
		public Object next() {
			Node temp = pointer;
			pointer = pointer.next;
			return temp.data;
		}
	}
	public Iterator iterator(){
		return new MyLinkedListIterator();
	}
	//public void showData(int index){
	//	System.out.println("the data of "+index+" is "+get(index).data);
	//}
}







