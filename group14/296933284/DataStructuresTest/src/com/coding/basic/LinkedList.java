package com.coding.basic;

import java.util.Collection;

import org.junit.Test;

/**
 * LinkedList (带头结点的单链表) 实现 第14小组 296933284
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

	public void add(Object obj) {
		addLast(obj);
	}

	public void add(int index, Object obj) {
		
		if (index == size + 1) {
			addLast(obj);
		} else {
			Node r = getPreNode(index);
			Node node = new Node();
			node.data = obj;
			node.next = r.next;
			r.next = node;
			size++;
		}
	}

	
	
	/**
	 * 单链表首部插入节点
	 * 
	 * @param obj 所插入节点的节点值
	 *            
	 */
	public void addFirst(Object obj) {
		Node node = new Node();
		node.data = obj;

		node.next = head.next;
		head.next = node;
		size++;
	}

	/**
	 * 单链表尾部插入节点
	 * 
	 * @param obj 所插入节点的节点值
	 * 
	 */
	public void addLast(Object obj) {
		
		Node node = new Node();
		node.data = obj;
		node.next = null;
		
		Node r = head;
		while (r.next != null) r = r.next;
		
		r.next = node;
		
		size++;

	}

	/**
	 * 将集合中所有元素按顺序插入单链表
	 * 
	 * @param c 要插入单链表的所有元素的集合
	 *            
	 */
	public void addAll(Collection c) {

		Iterator iter = (Iterator) c.iterator();

		while (iter.hasNext()) {
			addLast(iter.next());
		}
	}

	/**
	 * 获取指定位置的节点值
	 */
	public Object get(int index) {
		// rangCheck(index);
		
		return getPreNode(index).next.data;
	}

	/**
	 * 删除指定位置节点，并返回节点值
	 */
	public Object remove(int index) {
		rangCheck(index);
		
		Node r = getPreNode(index);
		
		Object result = r.next.data;
		
		r.next = r.next.next;
		size--;
		return result;
	}
	
	/**
	 * 删除单链表第一个节点，并返回节点值
	 * 
	 * @return 第一个节点的值
	 */
	public Object removeFirst() {
		return remove(0);
	}
	
	/**
	 * 删除单链表最后一个节点，并返回节点值
	 * 
	 * @return 最后一个节点的值
	 */
	public Object removeLast() {
		return remove(size - 1);
	}
	
	// 获取指定位置的前驱结点并返回
	private Node getPreNode(int index) {
		rangCheck(index);
		
		if (index == 0) {
			return head;
		} else {
			Node r = head;
			
			for (int i = 0; i < index; i++)
				r = r.next;
			
			return r;			
		}
		
	}
	
	/**
	 * 返回单链表的长度
	 */
	public int size() {
		return size;
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
		int current = 0;

		@Override
		public boolean hasNext() {
			return current != size;
		}

		@Override
		public Object next() {
			int i = current;
			
			rangCheck(i);
			
			current++;

			return get(i);
		}

	}
	
	/**
	 * 检查是否越界
	 * 
	 * @param index
	 */
	private void rangCheck(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException();
	}

	private static class Node {
		Object data;
		Node next;
		
		public Node() {
			super();
			this.data = null;
			this.next = null;
		}
		
		
	}
}