package edu.coerscnu.basic.list.LinkedList;

import edu.coerscnu.basic.Iterator;
import edu.coerscnu.basic.list.MyList;

public class MyLinkedList<E> implements MyList<E> {

	// 链表大小
	private int size;
	// 头节点
	private Node<E> firstNode;
	// 尾节点
	private Node<E> lastNode;

	private static class Node<E> {

		private Node<E> prev; // 前置节点

		private Node<E> next; // 后置节点

		private E ele; // 节点数据

		public Node(E ele, Node<E> prev, Node<E> next) {
			this.ele = ele;
			this.prev = prev;
			this.next = next;
		}
	}

	public MyLinkedList() {
		firstNode = new Node<E>(null, null, null);
		lastNode = new Node<E>(null, firstNode, null);
		firstNode.next = lastNode;
		size = 0;
	}

	@Override
	public boolean add(E e) {
		return add(size, e);
	}

	@Override
	public boolean add(int index, E e) {
		return addBefore(getNode(index), e);
	}

	@Override
	public E set(int index, E e) {
		Node<E> node = getNode(index);
		E old = node.ele;
		node.ele = e;
		return old;
	}

	@Override
	public E get(int index) {
		return getNode(index).ele;
	}

	@Override
	public E remove(int index) {
		return remove(getNode(index));
	}

	/**
	 * 在指定节点前加入节点
	 * 
	 * @param node
	 * @param e
	 */
	private boolean addBefore(Node<E> node, E e) {
		Node<E> newNode = new Node<E>(e, node.prev, node);
		newNode.prev.next = newNode;
		node.prev = newNode;
		size++;
		return true;
	}

	private E remove(Node<E> node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		size--;
		return node.ele;
	}

	/**
	 * 获取指定位置的节点
	 * 
	 * @param index
	 * @return
	 */
	private Node<E> getNode(int index) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		Node<E> node;
		if (index < size / 2) {
			node = firstNode;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
		} else {
			node = lastNode;
			for (int i = size; i > index; i--) {
				node = node.prev;
			}
		}
		return node;
	}

	@Override
	public boolean clear() {
		firstNode = new Node<E>(null, null, null);
		lastNode = new Node<E>(null, firstNode, null);
		firstNode.next = lastNode;
		size = 0;
		return true;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new MyLinkedListIterator();
	}

	private class MyLinkedListIterator implements Iterator<E> {

		private Node<E> current = firstNode.next;

		@Override
		public boolean hasNext() {
			return current != lastNode;
		}

		@Override
		public Object next() {
			E ele = current.ele;
			current = current.next;
			return ele;
		}

	}
}
