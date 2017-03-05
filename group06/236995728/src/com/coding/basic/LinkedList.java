package com.coding.basic;

/**
 * 2017/2/24
 * @author 236995728
 * 单链表
 */
public class LinkedList implements List {
	
	private Node head = new Node(null,null);
	private Node current = new Node(null, null);
	
	private int size = 0;
	
	/**
	 * 在尾节点添加节点
	 */
	@Override
	public void add(Object o){
		Node newNode = new Node(null,o);
		if(head.next == null){
			head.next = newNode;
		}else{
			current.next = newNode;
		}
		current = newNode;
		size ++;
	}
	
	/**
	 * 按照索引添加节点
	 */
	@Override
	public void add(int index , Object o){
		if(index <0 || index > size){
			throw new IndexOutOfBoundsException("param invalid");
		}
		Node node = head;
		Node newNode = new Node(null,o);
		for(int i=0; i<index-1; i++){
			node = node.next;
		}
		newNode.next = node.next;
		node.next = newNode;
		size ++;
	}
	
	/**
	 * 根据索引获取节点
	 */
	@Override
	public Object get(int index){
		if(index <0 || index > size){
			throw new IndexOutOfBoundsException("param invalid");
		}
		Node node = head;
		for(int i=0; i<index; i++){
			node = node.next;
		}
		return node.data;
	}
	
	/**
	 * 根据索引删除节点
	 */
	@Override
	public Object remove(int index){
		if(index <0 || index > size){
			throw new IndexOutOfBoundsException("param invalid");
		}
		Node node = head;
		Node nextNode = null;
		Object o = null;
		for(int i=0; i<index-1; i++){
			node = node.next;
		}
		nextNode = node.next.next;
		o = node.next.data;
		node.next = nextNode;
		size --;
		return o;
		
	}
	
	/**
	 * 返回节点的个数
	 */
	@Override
	public int size(){
		return size;
	}
	
	/**
	 * 添加第一个节点
	 * @param o
	 */
	public void addFirst(Object o){
		add(1,o);
	}
	
	/**
	 * 添加最后一个节点
	 * @param o
	 */
	public void addLast(Object o){
		add(size,o);
	}
	
	/**
	 * 移除第一个节点
	 * @return
	 */
	public Object removeFirst(){
		Object o = remove(1);
		return o;
	}
	
	/**
	 * 移除最后一个节点
	 * @return
	 */
	public Object removeLast(){
		Object o = remove(size);
		return o;
	}
	
	/**
	 * 
	 * @return
	 */
	public Iterator iterator(){
		return null;
	}
	
	/**
	 * 节点类
	 * @author Administrator
	 *
	 */
	private static  class Node{
		Object data;
		Node next;
		
		Node(Node next, Object data){
			this.next = next;
			this.data = data;
		}
	}
}
