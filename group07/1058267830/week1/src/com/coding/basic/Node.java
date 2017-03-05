package com.coding.basic;

public class Node {
	private Object data;
	private Node next;
	
	// 头结点构造方法
	public Node(Node head){
		this.next= head;
	}
	
	// 非头结点构造方法
	public Node(Object data, Node node){
		this.data = data;
		this.next = node;
	}

	// 获得当前节点的值
	public Object getData() {
		return data;
	}

	// 设置当前节点的值
	public void setData(Object data) {
		this.data = data;
	}

	// 获得下一个节点
	public Node getNext() {
		return next;
	}

	// 设置下一个节点
	public void setNext(Node next) {
		this.next = next;
	}
	
	
}
