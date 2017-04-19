package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	private int size;
	private Node tail;
	public void add(Object obj){
		linkLast(obj);
	}

	public void add(int index , Object obj){
		if (index<0 || index>size)
			throw new IndexOutOfBoundsException();
		else {
			if (index == size)
				linkLast(obj);
			else {
				Node node = linkIndex(index);//获取指定index的Node
				Node pred = node.prev;
				Node newNode = new Node(node.prev,obj,node);
				node.prev = newNode;
				if (pred==null)
					head = newNode;
				else
					node.prev.next = newNode;//原链表指定节点前一个的节点的next指向新节点
				size++;
			}
		}
	}
	public Object get(int index){
		return linkIndex(index);
	}
	public Object remove(int index){
		Node node = linkIndex(index);//获取指定index的Node
		Node prev = node.prev;
		Node next = node.next;
		if (prev==null) {
			//此时删除的节点为头节点
			head = next;
		} else {
			prev.next = next;
			node.prev = null;
		}
		if (next==null) {
			//此时删除节点为尾节点
			tail = prev;
		} else {
			next.prev = prev;
			node.next = null;
		}
		node.data = null;
		size--;
		return node;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node newNode = new Node(null,o, head);
		if (head == null) {
			head = newNode;
		} else {
			newNode.next = head;
		}
		size++;
	}
	public void addLast(Object o){
		Node newNode = new Node(head,o, null);
		if (head == null) {
			head = newNode;
		} else {
			newNode.next = head;
		}
		size++;
	}
	public Object removeFirst(){
		if (head == null) {
			throw new NoSuchElementException();
		} else {
			Node next = head.next;
			Object obj = head.data;
			head.next = null;
			head.data = null;
			head = next;
			if (next == null) {
				tail = null;
			} else {
				next.prev = null;
			}
			size--;
			return obj;
		}
	}
	public Object removeLast(){
		tail = tail.prev;
		head.next = null;
		head.data = null;
		return tail;
	}
	public Iterator iterator(){
		return null;
	}

	/**
	 * 尾部添加一个节点
	 * @param obj
     */
	private void linkLast(Object obj){
		Node newNode = new Node(tail,obj, null);
		if (tail == null) {
			head = newNode;
			tail = newNode;
		} else
			tail.next = newNode;
		size++;
	}
	/**
	 * 获取指定index索引的Node节点
	 * @param index
	 * @return
	 */
	private Node linkIndex(int index){
		Node n = head;
		for (int i=0;i<index;i++)
			n = n.next;
		return n;
	}
	private static  class Node{
		Object data;
		Node prev;
		Node next;
		Node(Node prev,Object obj, Node next){
			this.prev = prev;
			this.data = obj;
			this.next = next;
		}
	}
}
