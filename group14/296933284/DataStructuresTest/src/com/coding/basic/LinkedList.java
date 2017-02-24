package com.coding.basic;

/**
 * LinkedList 实现 第14小组 296933284
 * 
 * @author Tonnyson
 *
 */
public class LinkedList implements List {

	private Node head;
	private int size;
	
	public LinkedList() {
		super();
		this.head = new Node();
		this.size = 0;
	}

	/**
	 * 尾插法在链表末尾插入节点
	 */
	public void add(Object obj) {

		if (head.data == null) {
			head.data = obj;
			head.next = null;
		} else {

			Node r = head;
			while (r.next != null)
				r = r.next;

			Node node = new Node();
			node.data = obj;
			node.next = null;

			r.next = node;
		}

		size++;

	}

	/**
	 * 在指定索引位置插入节点
	 */
	public void add(int index, Object obj) {

		if (index > size)
			throw new IndexOutOfBoundsException();

		Node r = head;
		for (int i = 0; i < index - 1; i++)
			r = r.next;

		Node node = new Node();
		node.data = obj;
		node.next = null;
		node.next = r.next;
		r.next = node;
		size++;
	}

	/**
	 * 返回指定位置的值
	 */
	public Object get(int index) {
		if (index > size)
			throw new IndexOutOfBoundsException();

		Node r = head;
		for (int i = 0; i < index; i++)
			r = r.next;

		return r.data;
	}

	/**
	 * 删除指定位置节点并返回其值
	 */
	public Object remove(int index) {
		if (index > size)
			throw new IndexOutOfBoundsException();

		Node node = new Node();

		Node r = head;
		for (int i = 0; i < index - 1; i++)
			r = r.next;

		node = r.next;
		r.next = node.next;
		node.next = null;
		size--;
		return node.data;
	}

	/**
	 * 返回链表的长度
	 */
	public int size() {
		return size;
	}

	/**
	 * 在链表的起始位置插入节点
	 * 
	 * @param obj
	 */
	public void addFirst(Object obj) {
		Node node = new Node();
		node.data = obj;
		node.next = head;
		head = node;
		size++;
	}

	/**
	 * 在链表尾部插入节点
	 * 
	 * @param obj
	 */
	public void addLast(Object obj) {
		add(obj);
	}

	/**
	 * 删除链表的第一个节点
	 * 
	 * @return 所删除节点的值
	 */
	public Object removeFirst() {
		Node node = new Node();
		node = head;
		head = head.next;
		size--;

		return node.data;
	}

	/**
	 * 删除链表的最后一个节点
	 * 
	 * @return 所删除节点的值
	 */
	public Object removeLast() {
		return remove(size - 1);
	}
	
	/**
	 * 迭代器
	 * 
	 * @return 返回一个迭代器对象
	 */
	public Iterator iterator() {
		return new Iter();
	}

	// 迭代器内部类
	private class Iter implements Iterator {
		int current;

		@Override
		public boolean hasNext() {
			return current != size;
		}

		@Override
		public Object next() {
			int i = current;
			
			if (i >= size)
				throw new IndexOutOfBoundsException();
			
			current++;

			return get(i);
		}

	}

	// 节点类定义
	private static class Node {
		Object data;
		Node next;
	}
}
