package com.pxshuo.basic.impl;

import java.time.Period;

import com.pxshuo.basic.Iterator;
import com.pxshuo.basic.List;

public class LinkedList implements List {
	
	private Node head = new Node();
	private Node tail = null;
	private int size = -1;//与index的位置等同
	
	//封装空表时候的增加与最后一次的删除--
	
	public void add(Object o){
		Node node = new Node(o);
		node.next = null;
		
		if (head.next == null) {//初始化
			head.next = node;
			tail = node;
		}
		else {
			tail.next = node;
			tail = node;
		}
		size ++;
	}
	public void add(int index , Object o){
		if (index > size + 1) {
			add(o);
		}
		else{
			Node prev = head;
			Node current = new Node(o);
			for(int i=0; i < index; i++)
			{
				prev = prev.next;
			}
			current.next = prev.next;
			prev.next = current;
			size ++;
		}
	}
	public Object get(int index){
		if (index <= size) {
			Node node = head;
			for(int i = 0; i <= index;i++){
				node = node.next;
			}
			return node.data;
		}
		return null;
	}
	public Object remove(int index){
		Node remove = null;
		if (index <= size) {
			Node prev = head;
			for(int i=0; i < index; i++)
			{
				prev = prev.next;
			}
			remove = prev.next;
			prev.next = remove.next;
			remove.next = null;
			
			if (index == size) {//设置尾部
				tail = prev;
				if (size == 0) {
					tail = null;
				}
			}
			size --;
		}
		return remove != null ? remove.data : null;
	}
	
	public int size(){
		return size + 1;
	}
	
	public void addFirst(Object o){
		Node first = new Node(o);
		first.next = head.next;
		head.next = first;
		if(tail == null)
		{
			tail = first;
		}
		size ++;
	}
	public void addLast(Object o){
		if(tail == null){
			add(o);
		}
		else {
			Node last = new Node(o);
			last.next = null;
			tail.next = last;
			tail = last;
			size ++;
		}
		
	}
	public Object removeFirst(){
		Node first = head.next;
		if(first != null)
		{
			head.next = first.next;
			first.next = null;
		}
		else {
			head.next = null;
		}
		if (head.next == null) {//如果链表为空
			tail = null;
		}
		size --;
		return first!=null ? first.data : null;
	}
	public Object removeLast(){
		Node last = head;
		for(;last.next != tail; last = last.next ){
			
		}
		tail = last;
		last = last.next;
		if(tail == head){//最后一个元素
			head.next=null;
			tail = null;
		}else {
			tail.next = null;
		}
		
		size --;
		return last != null? last.data : null;
	}
	public Iterator iterator(){
		return new LinkedListIterator(this);
	}
	
	private static class Node{
		Object data;
		Node next;
		
		public Node() {
		}
		
		public Node(Object data) {
			this.data = data;
		}
		
	}
	
	private class LinkedListIterator implements Iterator{
		LinkedList linkedList = null;
		Node position = new Node();
		
		public LinkedListIterator(LinkedList linkedList) {
			this.linkedList = linkedList;
			this.position = head;
		}
		
		@Override
		public boolean hasNext() {
			position = position.next;
			if (position == null) {
				return false;
			}
			return true;
		}

		@Override
		public Object next() {
			return position.data;
		}
		
	}
	
}
