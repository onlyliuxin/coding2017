package com.github.zhanglifeng.coding2017.basic;

import java.util.NoSuchElementException;

/**
 * 功能：实现LinkedList.
 * @author zhanglifeng.
 */
public class LinkedList implements List {
	private Node head, tail;
	private int size;

	private Node getNodeByIndex(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("线性表索引越界");
		}
		Node current = head;
		for (int i = 0; i < size && current != null; i++, current = current.next) {
			if (i == index) {
				return current;
			}
		}
		return null;
	}

	public void add(Object o) {
		addLast(o);
	}

	public void add(int index, Object o) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("线性表索引越界");
		}

		if (0 == index) {
			addFirst(o);
		}else{			
			Node node = getNodeByIndex(index - 1);
			node.next = new Node(o, node.next);
			size ++;
		}
	}

	public Object get(int index) {
		return getNodeByIndex(index).data;
	}

	public Object remove(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("线性表索引越界");
		}

		if(0 == index){
			return removeFirst();
		}else if(size - 1 == index){
			return removeLast();
		}else{
			Node node = getNodeByIndex(index);
			Node preNode = getNodeByIndex(index - 1);
			preNode.next = node.next;
			size --;
			return node.data;
		}
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node currentHead = head;
		Node newNode = new Node(o, currentHead);
		head = newNode;
		if(currentHead == null){
			tail = newNode;
		}

		size++;
	}

	public void addLast(Object o) {
		Node currentTail = tail;
		Node newNode = new Node(o, null);
		tail = newNode;
		if(currentTail == null){
			head = newNode;
		}else {
			currentTail.next = newNode;
		}
		size++;
	}

	public Object removeFirst() {
		if(head == null){
			throw new NoSuchElementException();
		}
		Node node = new Node(head.data, null);
		head = head.next;
		size --;
		return node.data;
	}

	public Object removeLast() {
		if(tail == null){
			throw new NoSuchElementException();
		}
		Node node = getNodeByIndex(size - 1);
		node.next = null;
		size --;
		return node.data;
	}

	public Iterator iterator() {
		return new LinkedListIterator(this);
	}

	private static class Node {
		Object data;
		Node next;

		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	private class LinkedListIterator implements Iterator {
		LinkedList linkedList = null;
		private int current = 0;

		public LinkedListIterator(LinkedList linkedList) {
			this.linkedList = linkedList;
		}

		@Override
		public boolean hasNext() {
			return current < size;
		}

		@Override
		public Object next() {
			return linkedList.get(current ++);
		}
	}

	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList();
		linkedList.add("s1");
		linkedList.add("s2");
		linkedList.add("s3");
		linkedList.addFirst("s0");
		linkedList.addLast("s4");

		Iterator it = linkedList.iterator();
		while(it.hasNext()){
			System.out.print(it.next() + " ");
		}
		System.out.println();
		System.out.println("第3个元素：" + linkedList.get(3));

		System.out.println(linkedList.removeFirst());
		System.out.println(linkedList.size());
		System.out.println("Last element:" + linkedList.removeLast());
		System.out.println(linkedList.size());
		System.out.println("第2个元素：" + linkedList.remove(2));
		System.out.println(linkedList.size());
	}
}
