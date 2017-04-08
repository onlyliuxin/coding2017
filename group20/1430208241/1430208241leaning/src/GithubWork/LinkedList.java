package GithubWork;

import java.util.Iterator;

public class LinkedList implements List {

	private Node head = null;// 头节点
	private int size = 0;
	private Node last = null;

	/*
	 * 向链表中插入数据 (non-Javadoc)
	 * 
	 * @see GithubWork.List#add(java.lang.Object)
	 */
	public void add(Object o) {
		Node newNode = new Node(0);// 实例化一个节点
		if (head == null) {
			head = newNode;
			return;
		}
		Node tmp = head;
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		tmp.next = newNode;
		size++;
	}

	public void add(int index, Object o) {
		Node newNode = new Node(0);
		Node indexNode = head;
		int i = 0;
		while (i == index) {
			indexNode = indexNode.next;
			i++;
		}
		Node indexNextNode = indexNode.next;
		indexNode.next = newNode;
		newNode.pre = indexNode;
		newNode.next = indexNextNode;
		indexNextNode.pre = newNode;
		size++;
	}

	public Object get(int index) {
		Node indexNode = head;
		int i = 0;
		while (i == index) {
			indexNode = indexNode.next;
			i++;
		}
		return indexNode;
	}

	public Object remove(int index) {
		if (index < 1 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 1) {
			head = head.next;
			return head;
		}
		int i = 1;
		Node preNode = head;
		Node curNode = preNode.next;
		while (curNode != null) {
			if (i == index) {
				preNode.next = curNode.next;

			}
			preNode = curNode;
			curNode = curNode.next;
			i++;
		}
		return curNode;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node newNode = new Node(o);
		newNode.data = o;
		newNode.next = head;
		head.pre = newNode;
		head = newNode;
		size++;
	}

	public void addLast(Object o) {
		Node newNode = new Node(o);
		newNode.data = o;

		newNode.pre = last;
		last.next = newNode;
		last = newNode;
		size++;

	}

	public Object removeFirst() {
		Node ref = head;
		head = head.next;
		head.pre = null;
		size--;
		return ref;
	}

	public Object removeLast() {
		Node rel = last;
		last = last.pre;
		last.next = null;
		size--;
		return rel;

	}

	public Iterator iterator() {
		
		return null;
	}

	private static class Node {
		Object data;// 节点内容
		Node next = null;// 头节点
		Node pre = null;

		public Node(Object data) {
			this.data = data;
		}
       
	}

}
