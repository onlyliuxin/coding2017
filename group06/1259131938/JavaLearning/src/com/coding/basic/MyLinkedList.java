package com.coding.basic;


public class MyLinkedList implements List {
	
	private Node headNode;
	
	private Node endNode;
	
	private int size;
	
	public void add(Object o){
		add(size,o);
	}
	public void add(int index , Object o){
		addBefore(getNode(index),o);
	}
	
	// 执行添加元素：
	private void addBefore(Node node,Object o) {
		Node newNode = new Node(o, node.prev, node.next);
		newNode.prev.next = newNode;
		newNode.next.prev = newNode;
		size ++;
	}
	
	// 获得元素；
	private Node getNode(int index) {
		Node rtnNode;
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (index < size / 2) {
			rtnNode = headNode.next;
			for (int i = 0; i < index; i++) {
				rtnNode = rtnNode.next;
			}
		} else {
			rtnNode = endNode;
			for (int i = size; i > index; i--) {
				rtnNode = rtnNode.prev;
			}
		}
		return rtnNode;
	}
	
	public Object get(int index){
		return getNode(index).data;
	}
	public Object remove(int index){
		return remove(getNode(index));
	}
	
	private Object remove(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		size --;
		return node.data;
	}
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		add(headNode.prev);
	}
	public void addLast(Object o){
		add(endNode.next);
	}
	public Object removeFirst(){
		remove(headNode);
		return headNode.data;
	}
	public Object removeLast(){
		remove(endNode);
		return endNode.data;
	}
	public Iterator iterator(){
		return null;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	private static  class Node{
		//当前元素，下一个及前一个；
		Object data;
		Node next;
		Node prev;
		public Node(Object data,Node prev, Node next) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
}
