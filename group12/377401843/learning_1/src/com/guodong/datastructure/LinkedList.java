package com.guodong.datastructure;

import java.util.NoSuchElementException;

public class LinkedList implements List {

	private int size;

	private Node head;

	private Node last;

	/**
	 * 向 链表尾端插入元素
	 *
	 * @Method add
	 * @param o
	 * @see com.guodong.datastructure.List#add(java.lang.Object)
	 */
	public void add(Object o) {
		linkLast(o);
	}

	/**
	 * 向链表指定位置插入元素
	 *
	 * @Method add
	 * @param index
	 * @param o
	 * @see com.guodong.datastructure.List#add(int, java.lang.Object)
	 */
	public void add(int index, Object o) {
		checkIndexForAdd(index);

		if (index == size) {
			linkLast(o);
		} else {
			Node prevNode = getNodeByIndex(index - 1); // 取到当前下标的前一个节点
			Node currentNode = getNodeByIndex(index); // 取到当前下标节点
			Node newNode = new Node(o, currentNode); // 创建新节点，新节点的下一个节点为当前下标节点

			if (prevNode == null) { // 如果前一个节点为空，说明从头部插入
				head = newNode;
			} else {
				prevNode.next = newNode;
			}
			size++;
		}
	}

	/**
	 * 根据下标获取链表中元素
	 *
	 * @Method get
	 * @param index
	 * @return
	 * @see com.guodong.datastructure.List#get(int)
	 */
	public Object get(int index) {
		checkIndexForGet(index);
		return getNodeByIndex(index).data;
	}

	public Object getLast() {
		return last.data;
	}

	/**
	 * 根据下标移除链表元素
	 *
	 * @Method remove
	 * @param index
	 * @return
	 * @see com.guodong.datastructure.List#remove(int)
	 */
	public Object remove(int index) {
		checkIndexForGet(index);

		Node prevNode = getNodeByIndex(index - 1); // 获取当前index前一个元素
		Node currentNode = null;
		if (prevNode == null) {
			currentNode = getNodeByIndex(index); // 如果前一个为空，则把下一个元素赋值给链表头
			head = currentNode.next;
		} else {
			currentNode = prevNode.next; // 如果不为空，则把前一个节点跟后一个节点链接
			prevNode.next = currentNode.next;
		}
		Node nextNode = currentNode.next;

		if (nextNode == null) { // 如果后一个节点为空，则把链尾赋值为前一个节点
			last = prevNode;
		} else {
			currentNode.next = null; // 如果后一个节点不为空，不做任何处理，只打断当前节点的链接
		}
		Object data = currentNode.data;
		currentNode.data = null; // 清空当前节点的值，等待垃圾回收

		size--;

		return data;
	}

	/**
	 * 返回List长度
	 */
	public int size() {
		return size;
	}

	/**
	 * 向列表头部添加元素
	 * 
	 * @param o
	 */
	public void addFirst(Object o) {
		Node n = head;
		Node newNode = new Node(o, n);

		head = newNode;
		if (n == null) {
			last = newNode;
		}
		size++;
	}

	/**
	 * 向列表尾部添加元素
	 * 
	 * @param o
	 */
	public void addLast(Object o) {
		linkLast(o);
	}

	/**
	 * 移除链表第一个元素
	 * 
	 * @return
	 */
	public Object removeFirst() {
		Node n = head;
		if (n == null) {
			throw new NoSuchElementException();
		}
		Object data = n.data;
		Node nextNode = n.next;

		n.data = null;
		n.next = null;

		head = nextNode;
		if (nextNode == null) {
			last = null;
		}

		size--;
		return data;
	}

	public Object removeLast() {
		Node n = last;
		if (n == null) {
			throw new NoSuchElementException();
		}
		Object data = n.data;
		Node prevNode = getNodeByIndex(size - 2);
		n.data = null;
		if (prevNode == null) {
			head = null;
		} else {
			prevNode.next = null;
		}
		last = prevNode;

		size--;
		return data;
	}

	/**
	 * 根据下标获取对应的节点
	 *
	 * @MethodName getNodeByIndex
	 * @author zhaogd
	 * @date 2017年2月23日 下午3:32:48
	 * @param index
	 * @return
	 */
	private Node getNodeByIndex(int index) {
		if (index < 0) {
			return null;
		}
		Node n = head;
		for (int i = 0; i < index; i++) {
			n = n.next;
		}
		return n;
	}

	/**
	 * 在链表尾端插入节点
	 *
	 * @MethodName linkLast
	 * @author zhaogd
	 * @date 2017年2月23日 下午3:14:28
	 * @param o
	 */
	private void linkLast(Object o) {
		Node n = last; // 取出原尾端数据
		Node newNode = new Node(o, null); // 创建新节点
		last = newNode; // 把新节点放入链表尾端
		// 如果原尾端为空，说明链表为空，把新节点也放入链表头部
		// 如果不为空，把原尾端节点指向新节点
		if (n == null) {
			head = newNode;
		} else {
			n.next = newNode;
		}

		size++;
	}

	/**
	 * 检查下标是否合法
	 *
	 * @MethodName checkIndexForAdd
	 * @author zhaogd
	 * @date 2017年2月23日 下午3:05:07
	 * @param index
	 */
	private void checkIndexForAdd(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + size);
		}
	}

	/**
	 * 检查下标是否合法
	 *
	 * @MethodName checkIndexForGet
	 * @author zhaogd
	 * @date 2017年2月23日 下午4:21:35
	 * @param index
	 */
	private void checkIndexForGet(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + size);
		}
	}

	private static class Node {
		Object data;
		Node next;

		Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	public Iterator iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator {
		private Node current;

		private int index;

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public Object next() {
			if (current == null) {
				current = getNodeByIndex(index);
			}
			Object data = current.data;
			current = current.next;
			index++;
			return data;
		}
	}
}
