package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	private Node head;  //首节点
	private int size;   //节点个数
	
	public void add(Object o){  //在链表尾部添加node
		if(head == null){
			head = new Node(o, null);
		}else {
			Node last = head;
			while(last.next != null){
				last = last.next;
			}
			last.next = new Node(o, null);
		}
		size ++;
	}

	public void add(int index , Object o){  //在指定索引处插入node
		if(index > size || index < 0) throw new RuntimeException("IndexOutOfBounds");
		if(head == null){
			head = new Node(o, null);
		}else {
			if(index == 0){  //插入位置在头部
				head = new Node(o, head);
			}else {  //后面位置插入
				Node temp = head;
				int i = 0;
				while(i != index - 1){
					temp = temp.next;
					i ++;
				}
				Node tempNext = temp.next;
				temp.next = new Node(o, tempNext);
			}
		}
		size ++;
	}

	public Object get(int index){  //取出指定节点处的元素,从0开始
		if(index > size -1 || index < 0) throw new RuntimeException("IndexOutOfBounds");
		int i = 0;
		Node temp = head;
		while(i != index){
			i ++;
			temp = temp.next;
		}
		return temp.data;
	}

	public Object remove(int index){  //删除指定索引处的节点
		if(index > size -1 || index < 0) throw new RuntimeException("IndexOutOfBounds");
		if(index == 0) {  //第一个元素或只有一个元素
			Object o = head.data;
			head = head.next;
			size --;
			return o;
		}else {  //其他元素
			int i = 0;
			Node temp = head;  //被删除节点之前的节点
			while(i != index - 1){
				i ++;
				temp = temp.next;
			}
			Node delete = temp.next; //被删除的节点
			Object o = delete.data;
			temp.next = delete.next; //删除
			size --;
			return o;
		}
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){  //在表头添加节点
		head = new Node(o, head);
		size ++;
	}

	public void addLast(Object o){  //在链表尾部添加节点
		if(head == null){
			head = new Node(o,null);
			size ++;
			return;
		}
		Node last = head;
		while(last.next != null){
			last = last.next;
		}
		last.next = new Node(o, null);
		size ++;
	}

	public Object removeFirst(){  //在链表头部删除节点
		if(size() == 0) throw new RuntimeException("Underflow");
		Object o = head.data;
		head = head.next;
		size --;
		return o;
	}

	public Object removeLast(){  //在链表尾部删除节点
		if(size() == 0) throw new RuntimeException("Underflow");
		if(size() == 1){
			Object o = head.data;
			head = null;
			size --;
			return o;
		}
		Node temp = head;
		int i = 0;
		while(i != size-2){
			temp = temp.next;
			i ++;
		}
		Object o = temp.next.data;
		temp.next = null;
		size --;
		return o;
	}

	public Iterator iterator(){ //迭代器
		return new ListIterator();
	}

	private class ListIterator implements Iterator{ //实例内部类
		private Node current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Object next() {
			if(size() == 0) throw new NoSuchElementException("Underflow");
			Object o = current.data;
			current = current.next;
			return o;
		}
	}
	
	//这里内部类须为static,在类级别上一一对应，非实例级别
	private static class Node{
		Object data;
		Node next;
		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
}
