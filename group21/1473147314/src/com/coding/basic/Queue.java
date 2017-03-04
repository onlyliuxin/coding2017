package com.coding.basic;

public class Queue {
	
	private Node head;
	
	public void enQueue(Object o){
		Node node = new Node(o);
		if (head == null) {
			head = node;
			return;
		}
		Node current = head;
		while (current.next != null) {
			current = current.next;
		}
		current.next = node;
	}
	
	public Object deQueue(){
		if (head == null) {
			return null;
		}
		Object data = head.data;
		if (head.next == null) {
			head = null;
			return data;
		}
		head = head.next;
		return data;
	}
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public int size(){
		int size = 0;
		Node node = head;
		while (node != null) {
			node = node.next;
			size++;
		}
		return size;
	}
	
	private static class Node {
		Object data;
		Node next;

		public Node(Object data) {
			this.data = data;
		}
	}
}
