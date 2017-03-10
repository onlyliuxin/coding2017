package com.xusheng.stack;

/**
 * 使用链表结构实现栈
 * @author xusheng
 *
 */
public class MyLinkedStack<T> {

	private int size;
	private int modCount;
	private Node<T> beginMarker;
	private Node<T> endMarker;
	private int currentIndex = size;
	
	
	
	public MyLinkedStack() {
		doClear();
	}

	private void doClear(){
		this.size=0;
		this.modCount=0;
		beginMarker = new Node<T>(null,null,null);
		endMarker = new Node<T>(null,beginMarker,null);
		beginMarker.next = endMarker;
	}
	
	public int size(){
		return this.size;
	}
	
	public T get(int index){
		Node<T> n = get(index,0,size);
		return n.data;
	}
	
	public T pop(){
		Node<T> n = get(this.size-1,0,size);
		T old = n.data;
		n.prev.next = n.next;
		n.next.prev = n.prev;
		this.size--;
		return n.data;
	}
	
	public void push(T element){
		add(size,element);
	}
	
	public void add(int index,T element){
		addBefore(get(index,0,size),element);
	}
	
	private void addBefore(Node<T> n,T element){
		Node<T> newNode = new Node<T>(element,n.prev,n);
		n.prev.next = newNode;
		n.prev = newNode;
		this.size++;
		this.modCount++;
	}
	
	private Node<T> get(int index,int low,int upper){
		if(index<low || index>upper){
			throw new IndexOutOfBoundsException();
		}
		Node<T> p;
		if(index<(size/2)){
			p = beginMarker.next;
			for(int i=0;i<index;i++){
				p = p.next;
			}
		}else{
			p = endMarker;
			for(int i=size;i>index;i--){
				p = p.prev;
			}
		}
		return p;
	}
	
	private class Node<T>{
		
		private T data;
		private Node<T> prev;
		private Node<T> next;
		public Node(T data, Node<T> prev, Node<T> next) {
			super();
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
		
		
		
	}
}
