package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	public LinkedList(){
		head = new Node(new Object(),null,null);
	}
	
	public void add(Object o){
		Node last = head;
		for (int i = 0; i < size; i++) {
			last = last.next;
		}
		Node newNode = new Node(o,null,last);
		last.next = newNode;
		size++;
	}
	public void add(int index , Object o){
		Node oldNode = getNode(index);
		Node newNode = new Node(o, oldNode, oldNode.prev);
		oldNode.prev.next = newNode;
		oldNode.prev = newNode;
		size ++;
	}
	public Object get(int index){
		Node node = getNode(index);
		return node.data;
	}
	
	private Node getNode(int index){
		Node n = head.next;
		for (int i = 0; i < index; i++) {
			n = n.next;
		}
		return n;
	}
	
	public Object remove(int index){
		Node node = getNode(index);
		Object o =node.data;
		Node prevNode = node.prev;
		Node nextNode = node.next;
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
		node.next = node.prev =null;
		node.data = null;
		size --;
		return o;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		add(0,o);
	}
	public void addLast(Object o){
		add(o);
	}
	public Object removeFirst(){
		Object o = remove(0);
		return o;
	}
	public Object removeLast(){
		Object o = remove(size);
		return o;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static class Node{
		Object data;
		Node next;
		Node prev;
		
		public Node(Object o,Node next,Node prev){
			this.data = o;
			this.next = next;
			this.prev = prev;
		}
	}
	
	@Override
	public String toString() {
		String s = "{";
		Node n = head;
		for (int i = 0; i < size; i++) {
			n  = n.next;
			if(i == (size -1)){
				s += n.data;
			}else{
				s += n.data + ",";
			}
		}
		s += "}";
		return s;
	}
}
