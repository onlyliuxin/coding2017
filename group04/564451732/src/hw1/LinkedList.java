package hw1;

import java.util.Iterator;

public class LinkedList implements List {
private Node head;
private Node tail;
	
	public void add(Object o){
		if (head != null) {
			Node dummy = new Node(new Object());
			dummy.next = head;
			Node nextNode = head;
			
			while (nextNode != null) {
				dummy = dummy.next;
				nextNode = nextNode.next;
			}
			dummy.next = new Node(o);
			tail = dummy.next;
		} else {
			head = new Node(o);
			tail = head;
		}
		
	}
	public void add(int index , Object o){
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index entered is out of bounds");
		}
		Node dummy = head;
		for (int i = 1; i < index; i++) {
			dummy = dummy.next;
		}
		Node temp = dummy.next;
		dummy.next = new Node(o);
		dummy.next.next = temp;
		if (index == size()) {
			tail = dummy.next;
		}
	}
	public Object get(int index){
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index entered is out of bounds");
		}
		Node dummy = head;
		for (int i = 0; i < index;i++) {
			dummy = dummy.next;
		}
		return dummy.data;
	}
	public Object remove(int index){
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index entered is out of bounds");
		}
		Node dummy = head;
		for (int i = 1; i < index;i++) {
			dummy = dummy.next;
		}
		Node result = dummy.next;
		dummy.next = dummy.next.next;
		if (dummy.next == null) {
			tail = dummy;
		}
		return result.data;
	}
	
	public int size(){
		Node dummy = head;
		int size = 0;
		while (dummy != null) {
			dummy = dummy.next;
			size++;
		}
		
		return size;
	}
	
	public void addFirst(Object o){
		Node first = new Node(o);
		first.next = head;
		head = first;
	}
	public void addLast(Object o){
		tail.next = new Node(o);
		tail = tail.next;
	}
	public Object removeFirst(){
		Node temp = head;
		head = head.next;
		return temp.data;
	}
	public Object removeLast(){
		Node dummy = head;
		for (int i = 1; i < size()-1; i++) {
			dummy = dummy.next;
		}
		Node result = dummy.next;
		tail = dummy;
		dummy.next  = null;
		return result.data;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
		public Node(Object data) {
			this.data = data;
		}
		
	}
}
