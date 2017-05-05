package com.xusheng.stack;

/**
 * 此栈是用单链表实现
 * @author xusheng
 *
 */
public class MyLinkedStack2<T> {

	private Node<T> beginNode;
	private Node<T> endNode;
	private int size;
	
	
	
	public MyLinkedStack2() {
		size = 0;
		doClear();
	}

	private void doClear(){
		endNode = new Node<T>(null,null);
		beginNode = new Node<T>(null,beginNode);
	}
	
	private void insert(Node<T> p,T element){
		Node<T> newNode = new Node<T>(element,p.next);
		p.next = newNode;
		this.size++;
	}
	
	private Node getNode(int index){
		Node<T> p;
		p = beginNode.next;
		for(int i=0;i<index;i++){
			p = p.next;
		}
		return p;
	}
	
	private class Node<T>{
		public T data;
		public Node<T> next;
		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}
		
		
	}
	
}
