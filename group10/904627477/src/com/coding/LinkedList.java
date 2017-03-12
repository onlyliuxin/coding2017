package com.coding;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	
	public void add(Object o){
		addLast(o);
	}	
	
	public void add(int index , Object o){		
		Node node = new Node();
		node.data = o;
		if(index==0){
			addFirst(o);
			return ;
		}
		Node before = getNode(index-1);
		Node next = before.next;
		before.next = node;
		node.next = next;
	}
	
	private Node getLastNode(){
		Node temp = head;		
		if(head!=null){
			while(true){
				if(temp.next!=null){
					temp = temp.next;
				}else{
					break;
				}
			}
		}else{
			throw new NoSuchElementException();
		}
		return temp;
	}
	
	private Node getNode(int index){
		if(index<0){
			throw new IndexOutOfBoundsException();
		}
		int i = 0;
		Node temp = head;
		while(true){
			if(temp==null){
				throw new IndexOutOfBoundsException();
			}
			if(i==index){
				break;
			}else{
				i++;
				temp = temp.next;
			}
		}
		return temp;
	}
	
	public Object get(int index){
		Node node = getNode(index);
		return node.data;
	}
	
	public Object remove(int index){
		if(index==0){
			removeFirst();
		}
		Node before = getNode(index-1);
		Node temp = getNode(index);
		before.next = temp.next;
		return temp.data;
	}
	
	public int size(){
		int size = 0;
		Node temp = head;
		while(true){
			if(temp==null){
				break;
			}else{
				size++;
				temp = temp.next;
			}
		}
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node();
		node.data = o;
		node.next = head;
		head = node;
	}
	
	public void addLast(Object o){
		Node node = new Node();
		node.data = o;
		if(head==null){
			head = node;
			return;
		}
		Node last = getLastNode();
		last.next = node;
	}
	public Object removeFirst(){
		if(head == null){
			throw new NoSuchElementException();
		}
		Object obj = head.data;
		head = head.next;
		return obj;
	}
	public Object removeLast(){
		if(head == null){
			throw new NoSuchElementException();
		}
		if(head.next == null){
			return removeFirst();
		}
		Node before = head;
		Node temp = head.next;
		while(true){
			if(temp.next==null){
				break;
			}else{
				before = temp;
				temp = temp.next;
			}
		}
		before.next = null;
		return temp.data;
	}
	
	public Iterator iterator(){
		return new LinkedIterator();
	}
	
	
	private static  class Node{
		Object data;
		Node next;		
	}
	
	private class LinkedIterator implements Iterator{
		
		private Node node;
		
		public LinkedIterator(){
			node = head;
		}

		@Override
		public boolean hasNext() {
			if(node!=null){
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if(node==null){
				throw new NoSuchElementException();
			}else{
				Object obj = node.data;
				node = node.next;
				return obj;
			}
		}
		
	}
}
