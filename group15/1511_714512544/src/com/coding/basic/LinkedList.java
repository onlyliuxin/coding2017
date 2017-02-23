package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	private Node head;  //数据结构,首节点
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
		if(index > size || index < 0) throw new RuntimeException("索引越界");
		if(head == null){
			head = new Node(o, null);
		}else {
			if(index == 0){  //插入位置在头部
				head = new Node(o, head);
			}else {  //后面位置
				int i = 0;
				Node temp = head;
				while(i != index - 1){
					i ++;
					temp = temp.next;
				}
				Node tempNext = temp.next;
				temp.next = new Node(o, tempNext);
			}
		}
		size ++;
	}

	public Object get(int index){  //取出指定节点处的元素
		if(index > size -1 || index < 0) throw new RuntimeException("索引越界");
		int i = 0;
		Node temp = head;
		while(i != index){
			i ++;
			temp = temp.next;
		}
		return temp.data;
	}

	public Object remove(int index){  //删除指定索引处的节点
		if(index > size -1 || index < 0) throw new RuntimeException("索引越界");
		if(index == 0) {  //第一个元素或只有一个元素
			Object o = head.data;
			head = head.next;
			size --;
			return o;
		}else {  //其他元素
			int i = 0;
			Node temp = head;
			while(i != index - 1){
				i ++;
				temp = temp.next;
			}
			Node delete = temp.next;
			Object o = delete.data;
			temp.next = delete.next;
			delete = null;
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
		if(size() == 0) throw new RuntimeException("堆栈下溢");
		Object o = head.data;
		head = head.next;
		size --;
		return o;
	}

	public Object removeLast(){  //在链表尾部删除节点
		if(size() == 0) throw new RuntimeException("堆栈下溢");
		Node last = head;
		while(last.next != null){
			last = last.next;
		}
		Object o = last.data;
		size --;
		last = null;
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
			if(size() == 0) throw new NoSuchElementException("堆栈下溢");
			Object o = current.data;
			current = current.next;
			return o;
		}
	}
	
	//这里内部类必须为static,这里的每个LinkedList对象都对应唯一的class Node,在类级别上一一对应，非实例级别
	private static class Node{
		Object data;
		Node next;
		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
}
