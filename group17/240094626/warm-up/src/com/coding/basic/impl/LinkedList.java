package com.coding.basic.impl;

import java.util.NoSuchElementException;

import com.coding.basic.Iterator;
import com.coding.basic.List;

/**
 * 双向链表简单实现
 * @author 240094626
 */
public class LinkedList implements List {
	/**头节点（空的）*/
	private Node header = new Node(null, null, null); 
	/**链表节点长度*/
	private int size = 0;

	
	/**
	 * 无参构造函数，初始化header节点，前后均指向header节点，形成环形链表
	 * 环形链表：为了使链表节点的开头是header，结尾也是header；
	 * 		由于实现了List，那么链表就是有序的，根据下标查询时可借助环形特点双向查找，提升效率；
	 */
	public LinkedList() {
		header.next = header.pre = header;
	}
	
	
	/**
	 * 将Object o 添加到 节点n之前
	 * @param o
	 * @param n
	 */
	private void addBefore(Object o, Node n) {
		Node newNode = new Node(o, n.pre, n);
		newNode.next.pre = newNode;
		newNode.pre.next = newNode;
		size++;
	}
	
	/**
	 * 根据下标位置查找结点
	 * @param index
	 * @return
	 */
	private Node getNode(int index) {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("index:"+index);
		}
		// 查找从header开始
		Node n = header;
		if(index <= (size >> 1)){
			// 往next方向找第index个节点
			for(int i=0; i <=index; i++){
				n = n.next;
			}
		}else{
			// 往pre方向找第size-index个节点
			for(int i=size-index; i >0; i--){
				n = n.pre;
			}
		}
		return n;
	}
	
	
	/**
	 * 移除节点，从当前节点的前后节点间删除当前节点
	 * @param n
	 * @return
	 */
	private Object remove(Node n){
		if(n == header){
			throw new NoSuchElementException("未找到节点");
		}
		Object result = n.data;
		n.pre.next = n.next;
		n.next.pre = n.pre;
		n.data = null;
		size--;
		return result;
	}
	
	@Override
	public void add(Object o) {
		// 默认往header前添加
		addBefore(o,header);
	}


	@Override
	public void add(int index, Object o) {
		addBefore(o,index==size?header:getNode(index));
	}

	@Override
	public Object get(int index) {
		Node n = getNode(index);
		return n.data;
	}


	@Override
	public Object remove(int index) {
		return remove(getNode(index));
	}


	@Override
	public int size() {
		return size;
	}
	/**
	 * 环形链表结构，header.next就是第一个节点
	 * @param o
	 */
	public void addFirst(Object o){
		addBefore(o, header.next);
	}
	/**
	 * 环形链表结构，header.pre就是最后一个节点
	 * @param o
	 */
	public void addLast(Object o){
		addBefore(o, header);
	}
	public Object removeFirst(){
		return remove(header.next);
	}
	public Object removeLast(){
		return remove(header.pre);
	}
	
	public Iterator iterator(){
		
		return new LinkedListIterator();
	}
	
	
	@Override
	public String toString() {
		Iterator it = iterator();
		StringBuilder sb = new StringBuilder();
		while(it.hasNext()){
			if(sb.length() > 0){
				sb.append(",");
			}
			sb.append(it.next());
		}
		return "LinkedList {nodes=[" + sb + "], size=" + size + "}";
	}


	private static class Node{
		Object data;
		Node pre;
		Node next;
		
		/**
		 * 链表节点，带参构造函数
		 * @param data 节点内容
		 * @param pre 上一个节点
		 * @param next 下一个节点
		 */
		public Node(Object data, Node pre, Node next) {
			super();
			this.data = data;
			this.pre = pre;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node {data=" + data + "}";
		}
		
		
		
	}
	
	private class LinkedListIterator implements Iterator{
		int index ;
		
		public LinkedListIterator() {
			index = 0;
		}
		
		@Override
		public boolean hasNext() {
			if(index < size){
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			Node n = getNode(index++);
			return n.data;
		}
		
	}
	

}
