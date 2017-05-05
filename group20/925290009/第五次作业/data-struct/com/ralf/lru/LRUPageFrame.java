package com.ralf.lru;

/**
 * 双向链表实现LRU算法
 * @author Ralf
 *
 */
public class LRUPageFrame {

	private static class Node {
		private Node prev;
		private Node next;
		int pageNum;

		public Node(int pageNum) {
			this.pageNum = pageNum;
		}
	}

	private int capacity;
	private Node head;
	private Node tail;
	private int size;

	private void addFirst(int PageNum) {
		Node node = new Node(PageNum);
		if (head == null) {
			node.next = null;
			node.prev = null;
			head = node;
			tail = node;
			this.size++;
		} else {
			node.next = head;
			node.prev = null;
			head.prev = node;
			head = node;
			this.size++;
		}
	}

	public LRUPageFrame(int capacity) {
		this.capacity = capacity;
		this.head = null;
		this.tail = null;
	}

	/**
	 * 获取缓冲对象
	 * @param PageNum
	 */
	public void access(int PageNum) {

		Node node = getNode(PageNum);
		if (node == null) {
			if (size < capacity) {
				addFirst(PageNum);
			} else {
				removeLast();
				addFirst(PageNum);
			}
		} else if (this.head.pageNum == PageNum) {
			return;
		}
		else {
			moveToHead(node);
		}
	}

	private void moveToHead(Node node) {
		Node current = node;
		if (node.pageNum == this.tail.pageNum) {
			node.prev.next = null;
			tail = node.prev;

		} else {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		}
		current.next = head;
		current.prev = null;
		this.head = current;

	}

	private void removeLast() {

		Node preNode = tail.prev;
		tail.prev.next = null;
		tail.prev = null;
		tail = preNode;
		this.size--;
	}

	private Node getNode(int PageNum) {
		Node current = this.head;
		while (current != null) {
			if (current.pageNum == PageNum) {
				return current;
			}
			current = current.next;
		}
		return null;
	}

	public String toString() {
		if (this.head == null) {
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		Node current = this.head;
		while (current != null) {
			stringBuilder.append(current.pageNum);
			if (current.next != null) {
				stringBuilder.append(",");
			}
			current = current.next;
			
		}
		return stringBuilder.toString();
	}
}
