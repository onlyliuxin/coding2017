package com.coding.basic;


/** 
 * @ClassName: LinkedList 
 * @Description: 带头结点的单向列表.
 * @author: tangxp
 * @date: 2017年2月23日 下午9:14:28  
 */
public class LinkedList implements List {
	
	private Node head = new Node();
	private int size ;
	
	public void add(Object o){
		add(size,o);
	}
	
	public void add(int index , Object o){
		if(index<0 || index>size) {
			throw new IllegalArgumentException("目前链表长度不够!");
		}
		Node tempHead = head;
		int i = 0;
		while(i++ < index) {
			tempHead = tempHead.next;
		}
		addDriect(getNode(o),tempHead);
	}


	/**
	 * @Title: get	
	 * @Description: TODO
	 * @param index
	 * @return 
	 * @see com.coding.basic.List#get(int) 
	 */
	public Object get(int index){
		if(index<0|| index>=size) {
			throw new IllegalArgumentException("下标超出链表范围!");
		}
		Node tempHead = head;
		int i = 0;
		while(i++ < index) {
			tempHead = tempHead.next;
		}
		return tempHead.next.data;
	}
	
	public Object remove(int index){
		if(index<0  || index>=size) {
			throw new IllegalArgumentException("下标超出链表范围!");
		}
		Node tempHead = head;
		int i = 0;
		while(i++ < index) {
			tempHead = tempHead.next;
		}
		
		Node removingNode = tempHead.next;
		tempHead.next = removingNode.next;
		removingNode.next = null;
		size--;
		return removingNode.data;
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
		return remove(0);
	}
	
	public Object removeLast(){
		return remove(size-1);
	}
	
	public Iterator iterator(){
		return null;
	}
	
	private void addDriect(Node n,Node before) {
		Node temp =  before.next;
		n.next = temp;
		before.next = n;
		size++;
	}
	
	private Node getNode(Object o) {
		
		if(null == o) {
			throw new IllegalArgumentException("节点值不能为空");
		}
		
		Node  n = new Node();
		n.data = o;
		return n;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("size: "+size+" {null | ---}--->");
		if(null == head.next) {
			return sb.toString();
		}
		
		Node tempHead = head;
		while(null != tempHead.next ) {
			sb.append(tempHead.next.toString());
			tempHead = tempHead.next;
		}
		sb.append("null");
		return sb.toString();
	}
	
	private static  class Node{
		Object data;
		Node next;
		
		@Override
		public String toString() {
			return "{" + this.data +"  |---}--->";
		}
		
	}
}
