package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	public void add(Object o){
		if (head.data == null) {
			head.data = o;
			head.next = null;
			size++;
			return;
		}
		Node node = new Node(o);
		Node curr = head;
		while (curr.next != null) {
			curr = curr.next;
		}
		curr.next = node;
		size++;
	}
	public void add(int index , Object o){

		if (index < 0 || index > size) {
			System.out.println(index + " is invalid index!");
			return;
		}
		if (head.data == null) {
			if (index == 0) {
				head.data = o;
				head.next = null;
				size++;
				return;
			} else {
				System.out.println("invalid index!");
				return;
			}
		}
		Node node = new Node(o);
		Node curr = head;
		for (int i = 0; i < index - 1; i++) {
			curr = curr.next;
		}
		Node temp = curr.next;
		curr.next = node;
		node.next = temp;
		size++;
	}
	public Object get(int index){
		if (index < 0 || index > size) {
			System.out.println(index + " is invalid index!");
			return null;
		}
		Node result = head;
		for (int i = 0; i < index; i++) {
			result = result.next;
		}
		return result;
	}
	public Object remove(int index){
		if (index < 0 || index > size) {
			System.out.println(index + " is invalid index!");
			return null;
		}
		Node curr = head;
		for (int i = 0; i < index - 1; i++) {
			curr = curr.next;
		}
		Node result = curr.next;
		curr.next = curr.next.next;
		size--;
		return result;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		if (head.data == null) {
			head.data = o;
			head.next = null;
			size++;
			return;
		}
		Node temp = head;
		head = new Node(o);
		head.next = temp;
		size++;
	}

	public void addLast(Object o){
		if (head.data == null) {
			head.data = o;
			head.next = null;
			size++;
			return;
		}
		Node node = new Node(o);
		Node curr = head;
		while (curr.next != null) {
			curr = curr.next;
		}
		curr.next = node;
		size++;
	}

	public Object removeFirst(){
		if (head.data == null) {
			return null;
		}
		Node result = head;
		head = head.next;
		size--;
		return result;
	}

	public Object removeLast(){
		if (head.data == null) {
			return null;
		}
		Node curr = head;
		for (int i = 0; i < size - 1; i++) {
			curr = curr.next;
		}
		Node result = curr.next;
		curr.next = null;
		size--;
		return result;
	}

	public Iterator iterator(){
		return null;
	}
	
	
	private static class Node{
		Object data;
		Node next;

		Node(Object o) {
			data = o;
			next = null;
		}
		
	}
}
