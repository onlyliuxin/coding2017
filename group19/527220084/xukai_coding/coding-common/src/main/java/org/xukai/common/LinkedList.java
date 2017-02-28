package org.xukai.common;

public class LinkedList implements List {
	
	private Node head;

	private int size = 0;
	
	public void add(Object o){
		Node node = new Node();
		node.data = o;
		if (head == null) {
			head = node;
		} else {
			Node next = head.next;
			if (next == null) {
				head.next = node;
			} else {
				while (next.next != null){
					next = next.next;
				}
				next.next = node;
			}
		}
		size++;
	}

	public void add(int index , Object o){
		if (index < 0 || index > size ) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			size++;
			Node node = new Node();
			node.data = o;
			if (index == 0) {
				node.next = head;
				head = node;
				return;
			}
			int pos = 1;
			Node next = head;
			//index 2
			while(index > pos){
				next = next.next;
				pos++;
			}
			node.next = next.next;
			next.next = node;
		}
	}
	public Object get(int index){
		if (index < 0 || index > size ) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			if (index == 0) {
				return head.data;
			}
			int pos = 1;
			Node next = head;
			//index 2
			while(index > pos){
				next = next.next;
				pos++;
			}
			return next.data;
		}
	}

	public Object remove(int index){
		if (index < 0 || index > size - 1 ) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			if (index == 0) {
				Node result = head;
				head = head.next;
				return result.data;
			}
			int pos = 1;
			Node next = head;
			//index 1
			while(index > pos){
				next = next.next;
				pos++;
			}
			Node result = next.next;
			next.next = next.next.next;
			size--;
			return result.data;
		}
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node();
		node.data = o;
		node.next = head;
		head = node;
		size++;
	}

	public void addLast(Object o){
		add(o);
	}

	public Object removeFirst(){
		Node result = head;
		head = head.next;
		size--;
		return result.data;
	}

	public Object removeLast(){
		return remove(size-1);
	}

	public Iterator iterator(){
		return new LinkedListIterator();
	}

	class LinkedListIterator implements Iterator {

		private Node currentNode ;

		@Override
		public boolean hasNext() {
			if (currentNode == null) {
				if (head != null) {
					return true;
				} else {
					return false;
				}
			}
			return currentNode.next != null;
		}

		@Override
		public Object next() {
			if (currentNode == null) {
				currentNode = head;
				return currentNode.data;
			}
			currentNode = currentNode.next;
			return currentNode.data;
		}
	}

	public void display(){
		System.out.print("[");
		Iterator iterator = iterator();
		while (iterator.hasNext()){
			System.out.print(" " + iterator.next());
		}
		System.out.print(" ]");
	}

	private static  class Node{
		Object data;
		Node next;
	}
}
