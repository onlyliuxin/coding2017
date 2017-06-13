package com.coding.basic;

import java.util.NoSuchElementException;

import org.junit.Test;

public class LinkedList implements List {

	private Node first;

	private Node last;

	private int size;

	/**
	 * 头部添加节点方法
	 */
	private void linkFirst(Object o) {
		final Node node = first;
		final Node firstNode = new Node(o, node, null);
		this.first = firstNode;
		if (node == null) {
			last = firstNode;
		} else {
			node.prev = firstNode;
		}
		size++;
	}

	/**
	 * 末端添加节点方法
	 * 
	 * @param o
	 */
	private void linkLast(Object o) {
		final Node node = last;
		final Node lastNode = new Node(o, null, node);
		last = lastNode;
		if (node == null) {
			first = lastNode;
		} else {
			node.next = lastNode;
		}
		size++;
	}

	/**
	 * 在链表末端添加节点
	 */
	public void add(Object o) {
		if (o != null)
			linkLast(o);
	}

	/**
	 * 在指定下标处添加节点
	 */
	public void add(int index, Object o) {
		checkIndex(index);
		Node befo = getNodeByIndex(index);
		linkBefore(o, befo);
	}

	/**
	 * 根据下标获取节点
	 */
	public Object get(int index) {
		checkIndex(index);
		return getNodeByIndex(index).data;
	}

	/**
	 * 根据下标移除节点
	 */
	public Object remove(int index) {
		checkIndex(index);
		Node current = getNodeByIndex(index);
		final Node node = current;
		final Node prev = node.prev;
		final Node next = node.next;
		if (prev == null) {
			first = next;
		} else {
			prev.next = next;
			next.prev = prev;
			current = null;
		}
		if (next == null) {
			last = prev;
		} else {
			prev.next = next;
			next.prev = prev;
			current = null;
		}
		size--;
		return node.data;
	}

	/**
	 * 返回链表长度
	 */
	public int size() {
		return size;
	}

	/**
	 * 在链表头添加节点
	 * 
	 * @param o
	 */
	public void addFirst(Object o) {
		linkFirst(o);
	}

	/**
	 * 在链表最后添加节点
	 * 
	 * @param o
	 */
	public void addLast(Object o) {
		linkLast(o);
	}

	/**
	 * 移除链表首个元素
	 * 
	 * @return
	 */
	public Object removeFirst() {
		Node node = first;
		if (node == null)
			throw new NoSuchElementException();
		else {
			Node next = node.next;
			if (next == null)
				first = null;
			else {
				first = next;
				first.prev = null;
			}
		}
		size--;
		return node.data;
	}

	/**
	 * 移除链表最后一个元素
	 * 
	 * @return
	 */
	public Object removeLast() {
		Node node = last;
		if (last == null)
			throw new NoSuchElementException();
		else {
			Node prev = node.prev;
			if (prev == null)
				last = null;
			else {
				last = prev;
				last.next = null;
			}
		}
		size--;
		return node.data;
	}

	/**
	 * 迭代方法
	 * 
	 * @return
	 */
	public Iterator iterator() {
		return new Iter();
	}

	/**
	 * 在节点之前插入一个新的节点
	 * 
	 * @param data
	 * @param befo
	 */
	private void linkBefore(Object data, Node befo) {
		final Node prev = befo.prev;
		final Node node = new Node(data, befo, prev);
		befo.prev = node;
		if (prev == null)
			first = node;
		else
			prev.next = node;
		size++;
	}

	/**
	 * 根据下标获取节点
	 * 
	 * @param index
	 * @return
	 */
	private Node getNodeByIndex(int index) {
		checkIndex(index);

		Node node = first;
		for (int x = 0; x < index; x++) {
			node = node.next;
		}
		return node;
	}

	/**
	 * 检查下标是否越界
	 * 
	 * @param index
	 */
	private void checkIndex(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("链表下标越界");
		}
	}

	/**
	 * 迭代器内部类
	 * 
	 * @author Adminstater
	 * 
	 */
	private class Iter implements Iterator {
		private int nextIndex = 0;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return nextIndex != size;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			int i = nextIndex++;
			if (i > size - 1)
				throw new IndexOutOfBoundsException();
			return getNodeByIndex(i).data;
		}

	}

	/**
	 * 节点内部类
	 * 
	 * @author Adminstater
	 * 
	 */
	private static class Node {
		Object data;
		Node next;
		Node prev;

		private Node(Object data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}

		private Node() {

		}
	}

	/*------------------------------------------------------单元测试----------------------------------------------------*/

	/**
	 * 测试添加方法功能
	 */
	// add(Object o)
	@Test
	public void TestAddFunction() {

		LinkedList list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		System.out.println("添加完毕");

	}

	// add(int index,Object o)
	@Test
	public void TestAddIndexFunction() {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		for (int x = 0; x < list.size(); x++) {
			System.out.println(list.get(x));
		}
		list.add(3, 111);
		System.out.println(list.get(3));
	}

	// addFirst(Object o)
	@Test
	public void TestAddFirstFunction() {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		System.out.println(list.get(0));
		list.addFirst(20);
		System.out.println(list.get(0));
	}

	// addLast(Object o)
	@Test
	public void TestAddLastFunction() {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		System.out.println(list.get(list.size() - 1));
		list.addLast(111);
		System.out.println(list.get(list.size() - 1));
	}

	/**
	 * remove方法测试
	 */
	// removeFirst()
	@Test
	public void TestRemoveFirst() {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		System.out.println(list.get(0));
		list.removeFirst();
		System.out.println(list.get(0));
	}

	// removeLast()
	@Test
	public void TestRemoveLast() {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		System.out.println(list.get(list.size() - 1));
		list.removeLast();
		System.out.println(list.get(list.size() - 1));
	}

	// remove(int index)
	@Test
	public void TestRemoveFunction() {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		System.out.println(list.get(50));
		list.remove(50);
		System.out.println(list.get(50));
	}

	/**
	 * Iterator测试
	 */
	@Test
	public void TestIterator() {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		Iterator ite = list.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
	}

	public static void main(String[] args) {
		java.util.LinkedList list = null;
	}
}
