package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	
	private int size = 0;
	
	public void add(Object o){
		Node tmp = head;
		while (tmp.next != null)
			tmp = tmp.next;
		
		Node n = new Node(o);
		tmp.next = n;
		size++;
	}
	public void add(int index , Object o){
		if (!rangeCheck(index))
			throw new IndexOutOfBoundsException();
		
		if (index == 0) {
            Node newhead = new Node(o);
            newhead.next = head;
            head = newhead;
        } else {
            Node tmp = head;
            for (int i = 0; i < index - 1; ++i) 
            	tmp = tmp.next;
            Node node = new Node(o);
            node.next = tmp.next;
            tmp.next = node;
        }

        size++;	
	}
	public Object get(int index){
		if (!rangeCheck(index))
			throw new IndexOutOfBoundsException();
		Node tmp = head;
        for (int i = 0; i < index - 1; ++i) 
        	tmp = tmp.next;
        return tmp.data;

	}
	
	public Object remove(int index){
		if (!rangeCheck(index))
			throw new IndexOutOfBoundsException();
		
		if (index == 0) {
            Node  oldHead= head;
            head = head.next;
            size--;	
            return oldHead.data;
        }else {
			Node tmp = head;
	        for (int i = 0; i < index - 1; i++) {
	        	tmp = tmp.next;
	        }
	        Node node = tmp.next;
	        tmp.next = node.next;
	        size--;
	        return node.data;
        }
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node newHead = new Node(o);
		newHead.next = head;
        head = newHead;
        size++;
	}
	public void addLast(Object o){
		Node tmp = head;
        while (tmp.next != null) {
        	tmp = tmp.next;
        }
        Node node = new Node(o);
        tmp.next = node;
        size++;
	}
	public Object removeFirst(){
		if (head == null) 
            throw new IndexOutOfBoundsException();
        Node oldHead = head;
        head = head.next;
        size--;
        return oldHead.data;
	}
	public Object removeLast(){
		if (head == null) 
            throw new IndexOutOfBoundsException();
        Node tmp = head;
        while (tmp.next.next != null) {
        	tmp = tmp.next;
        }
        Node node = tmp.next;
        tmp.next = null;
        size--;
        return node.data;
	}
	public Iterator iterator(){
		return null;
	}
	
	private boolean rangeCheck(int index) {
		if (index < 0 || index >= size)
			return false;
		return true;
	}
	
	private static  class Node{
		Object data;
		Node next;
		
		Node(Object data) {
            this.data = data;
            next = null;
        }
		
	}
}
