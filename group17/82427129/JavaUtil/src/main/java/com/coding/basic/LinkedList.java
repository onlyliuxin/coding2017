package com.coding.basic;

public class LinkedList implements List {
	private int size = 0;
	
	private Node first;
	
	private Node last;
	
	public static int getFirst(LinkedList l){
		return l.size;
	}
	
	public void add(Object o){
		
	}
	public void add(int index , Object o){
		rangeCheck(index);
		
		final Node succ = indexOf(index);
		Node newNode = new Node(succ.prev, o, succ);
		succ.prev = newNode;
		if(index == size){
			last = newNode;
		}else{
			newNode.next = indexOf(index);
			indexOf(index).prev = newNode;
		}
		size++;
	}
	private void rangeCheck(int index) {
		if(index > size|| index < 0 )
			throw new IndexOutOfBoundsException("Size"+size+":index"+index);
	}
	/**
	 * 循环获取index下标的
	 * @param index
	 * @return
	 */
	private Node indexOf(int index){
		if(index < (this.size>>1) ){
			Node x = first;
			for (int i = 0; i < index; i++) {
				x = x.next;
			}
			return x;
		}else{
			Node x = last;
			for (int i = this.size-1; i > index; i--) {
				x = x.prev;
			}
			return x;
		}
	}

	public Object get(int index){
		return null;
	}
	public Object remove(int index){
		return null;
	}
	
	public int size(){
		return size;
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
	
	private  class Node{
		Object data;
		Node next;
		Node prev;
		Node(Node prev,Object data,Node next){
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
}