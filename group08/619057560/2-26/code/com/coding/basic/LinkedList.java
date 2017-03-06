package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	
	public void add(Object o){
		Node pNewNode = new Node();
		pNewNode.data = o;
		
		Node pNode = head;
		
		if (head == null) {
			head = pNewNode;
			return;
		}
		
		while (pNode.next != null) {
			pNode = pNode.next;
		}
		
		pNode.next = pNewNode;
	}
	
	public void add(int index , Object o){
		if (index < 0 && index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		Node pNewNode = new Node();
		pNewNode.data = o;
		
		if (index == 0) {
			pNewNode.next = head;
			head = pNewNode;
			return;
		}
		
		Node pNode = head;
		while (--index > 0) {
			pNode = pNode.next;
		}
		pNewNode.next = pNode.next;
		pNode.next = pNewNode;
	}
	
	public Object get(int index){
		if (index < 0 && index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		Node pNode = head;
		while (index-- > 0) {
			pNode = pNode.next;
		}
		
		return pNode.data;
	}
	
	public Object remove(int index){
		if (index < 0 && index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		Node pNode = head;
		if (index == 0) {
			head = head.next;
			return pNode.data;
		}
		
		while (--index > 0) {
			pNode = pNode.next;
		}
		Node pTargetNode = pNode.next;
		pNode.next = pTargetNode.next;
		
		return pTargetNode.data;
	}
	
	public int size(){
		Node pNode = head;
		int num = 0;
		while (pNode != null) {
			pNode = pNode.next;
			num++;
		}
		return num;
	}
	
	public void addFirst(Object o){
		add(0, o);
	}
	
	public void addLast(Object o){
		add(o);
	}
	
	public Object removeFirst(){
		if (head == null) {
			throw new NoSuchElementException();
		}
		return remove(0);
	}
	
	public Object removeLast(){
		if (head == null) {
			throw new NoSuchElementException();
		}
		
		Node pNode = head;
		Node pPrevNode = null;
		while (pNode.next != null) {
			pPrevNode = pNode;
			pNode = pNode.next;
		}
		if (pPrevNode != null) {
			pPrevNode.next = pNode.next;
		}
		else {
			head = null;
		}
		return pNode.data;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
	}
}
