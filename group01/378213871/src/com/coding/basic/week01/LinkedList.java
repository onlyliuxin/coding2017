package com.coding.basic.week01;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	
	private int size;
	
	public void add(Object o){
		if (head == null) {
			head = new Node(o);
			size++;
		} else{
			addLast(o);
		}
	}
	public void add(int index , Object o){
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} 
		if (index == 0) {
			addFirst(o);
		} else {
			//定义标记节点sentinelNode，标记节点的下一个节点即为要新加的元素
			Node sentinelNode = head;
			for (int i = 0; i < index - 1; i++) {
				sentinelNode = sentinelNode.next;
			}
			Node node = new Node(o);
			node.next = sentinelNode.next;
			sentinelNode.next = node;
			size++;
		}
	}
	public Object get(int index){
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} else {
			Node indexNode = head;
			for (int i = 0; i < index; i++) {
				indexNode = indexNode.next;
			}
			return indexNode.data;
			}
	}
	public Object remove(int index){
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} else {
			/**
			 * sentinelNode是所删除节点的上一个节点；
			 * indexNode是需要被删除的节点
			 */
			Node sentinelNode = head;
			Node indexNode = head;
			for (int i = 0; i < index - 1; i++) {
				sentinelNode = sentinelNode.next;
			}
			for (int i = 0; i < index; i++) {
				indexNode = indexNode.next;
			}
			Node nextIndexNode = indexNode.next;
			sentinelNode.next = nextIndexNode;
			indexNode.next = null;
			size--;
			return indexNode.data;
		}
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node(o);
		node.next = head;
		head = node;
		size++;
	}
	public void addLast(Object o){
		//定义尾节点并通过while循环找到当前链表的尾节点
		Node tailNode = head;
		while (tailNode.next != null) {
			tailNode = tailNode.next;
		}
		Node node = new Node(o);
		tailNode.next = node;
		size++;
	}
	public Object removeFirst(){
		if (head == null) {
			throw new NoSuchElementException();
		}
		Node newNode = head;
		head = head.next;
		size--;
		return newNode.data;
	}
	public Object removeLast(){
		if (head == null) {
			throw new NoSuchElementException();
		}
		Node newNode = head;
		while (newNode.next.next != null) {
			newNode = newNode.next;
		}
		Node lastNode = newNode.next;
		newNode.next = null;
		size--;
		return lastNode.data;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next; // 下一个节点
		
		private Node(Object data) {
			this.data = data;
			next = null;
		}
	}
}
