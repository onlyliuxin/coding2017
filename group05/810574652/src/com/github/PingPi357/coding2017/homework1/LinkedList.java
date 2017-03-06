package com.github.PingPi357.coding2017.basic;


//实现单向单端列表
public class LinkedList implements List {
	private Node head = new Node(); // 定义链表的头节点
	private Node current;
	private int size;

	public LinkedList() {
		head = null;
		size = 0;
	}

	@Override
	public void add(Object o) {
		if (null == head) { // 头结点为空，直接放在头结点上
			head.data = o;
			head.next = null;
			size++;
		} else {
			current = head;
			while (!(current.next == null)) { // 找到链表的末尾节点，在其后增加
				current = current.next;
			}
			current.next = new Node();
			current.next.data = o;
			current.next.next = null;
			size++;
		}
	}

	public void addFirst(Object o) {
		if (null == head) {
			head.data = o;
			head.next = null;
			size++;
		} else {
			current = new Node();
			current.data = o;
			current.next = head;
			head = current;
			size++;
		}
	}

	public void addLast(Object o) {
		if (!(head == null)) {
			current = head;
			while (!(current.next == null)) {
				current = current.next;
			}
			current.next = new Node();
			current.next.data = o;
			current.next.next = current.next;
			size++;
		}
	}

	@Override
	public void add(int index, Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object remove(int index) {
		// TODO Auto-generated method stub
		if (index < size && index > 1) { // 为什么index>1???
			current = head;
			for (int i = 0; i < index - 1; i++) {
				current = current.next; // 遍历到指定到index的上一个节点
			}
			current.next = current.next.next;
			size--;
		}
		return null;
	}

	public Object removeFirst() {
		if (!(head == null)) {
			Node removeHead = head;
			head.next = head.next.next;
			size--;
			return removeHead;
		} else
			return null;
	}

	public Object removeLast() {
		if (!(head == null)) {
			Node theNext = current.next;
			Node oldLast;
			while (theNext.next != null) {
				current = theNext;
				theNext = theNext.next;
			}
			oldLast = current.next;
			current.next = theNext.next;

			size--;
			return oldLast;
		} else
			return null;

	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		if (index < size) {
			current = head;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			return current.data;
		} else
			return null;
	}

	@Override
	public int size() {
		return this.size;
	}

	private static class Node { // 创建Node类，定义Node类的两个属性
		Object data;
		Node next;
	}

}
