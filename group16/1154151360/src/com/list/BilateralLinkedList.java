package com.list;

import java.util.LinkedList;

//双向链表
public class BilateralLinkedList {
	
	int size;
	
	Node first;
	
	Node last;
	
	
	private void addFirst(Object o){
		Node f = first;
		Node newNode = new Node(null, o, first);
		first = newNode;
		if (f== null)
			last = newNode;
		else
			f.prev = newNode;
		size++;
	}
	
	private void addLast(Object o){
		Node l = last;
		Node newNode = new Node(l, o,null);
		last = newNode;
		if (l == null)
			first = newNode;
		else
			l.next = newNode;
		size++;
	}
	
	private void insert(Object o, Node node){
		
		Node n = node;
		Node newNode = new Node(node.prev, o, node);
		if (node.prev == null)
			first = newNode;
		else
			node.prev.next = newNode;
		size++;
		
	}
	
	
	private static class Node{
		
		Node prev;
		
		Object o;
		
		Node next;
		
		Node (Node prev,Object o, Node next){
			this.prev = prev;
			this.o = o;
			this.next = next;
		}
	}
}
