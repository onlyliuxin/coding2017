package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	
	public void add(Object o){
    Node node = new Node(o, null);
    if (head == null){
      head = node;
    }else {
      Node current = head;
      while (current.getNext() != null) {
        current = current.getNext();
      }
      current.setNext(node);
    }
  }

	public void add(int index , Object o){
	  Node current = head;
    for (int i = 0; i < index-1; i++) {
      current = current.getNext();
      if (current == null){
        throw new IndexOutOfBoundsException();
      }
    }
    Node right = current.getNext();
    current.setNext(new Node(o,right));
  }

	public Object get(int index){
	  if (head == null){
	    return null;
    }
    Node current = head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
      if (current == null){
        throw new IndexOutOfBoundsException();
      }
    }
    return current.getData();
	}

	public Object remove(int index){
	  Node current = head;
    for (int i = 0; i < index - 1; i++) {
      current = current.getNext();
      if (current == null){
        throw new IndexOutOfBoundsException();
      }
    }
    if (current.getNext() == null){
      throw new IndexOutOfBoundsException();
    }
    Object removed = current.getNext().getData();
    current.setNext(current.getNext().getNext());
    return removed;
	}
	
	public int size(){
	  if (head == null){
	    return 0;
    }
    int n = 1;
	  Node current = head;
    while (current.getNext() != null) {
	    n ++;
	    current = current.getNext();
    }
    return n;
	}
	
	public void addFirst(Object o){
		
	}
	public void addLast(Object o){
		
	}
	public Object removeFirst(){
		return null;
	}
	public Object removeLast(){
		return null;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;

		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}
}
