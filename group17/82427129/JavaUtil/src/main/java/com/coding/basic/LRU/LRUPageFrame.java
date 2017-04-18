package com.coding.basic.LRU;

public class LRUPageFrame {
	private int capacity;
	private Node first;// 链表头
	private Node last;// 链表尾
	private int length = 0;

	public LRUPageFrame(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		// this loop select for pageNum,grow it first when 'found'.
		for (Node n = first; n != null; n = n.next) {
			if (n.pageNum == pageNum) {
				growFirst(n);
				return;
			}
		}
		// if didn't found it
		if (ensureFull()) {
			removeLast();
		}
		add(pageNum);
	}

	private void add(int pageNum) {
		if (isEmpty()) {
			Node newNode = new Node(null, null, pageNum);
			first = newNode;
			last = newNode;
			length++;
		} else {
			addToFirst(pageNum);
		}
	}

	private void addToFirst(int pageNum) {
		Node newNode = new Node(null, null, pageNum);
		newNode.next = first;
		first.prev = newNode;
		first = newNode;
		length++;
	}

	/**
	 * ensure the LRUPageFrame is full or Not
	 * 
	 * @return if full return true,else false
	 */
	private boolean ensureFull() {
		if (length < capacity) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * grow the Node'position to first
	 * 
	 * @param n
	 */
	private void growFirst(Node n) {
		// if the node is already first ,return.
		if (first.pageNum == n.pageNum) {
			return;
		}
		remove(n);
		addToFirst(n.pageNum);
	}

	private void remove(Node n) {
		if (isEmpty()) {
			return;
		}
		if (n.next == null) {
			removeLast();
			return;
		}
		if (n.prev == null) {
			removeFirst();
			return;
		}
		Node prev = n.prev;
		Node next = n.next;
		n.next = null;
		n.prev = null;
		prev.next = next;
		next.prev = prev;
		length--;
	}

	private void removeFirst() {
		Node next = first.next;
		first.next = null;
		first = next;
	}

	private void removeLast() {
		Node prev = last.prev;
		prev.next = null;
		last.prev = null;
		last = prev;
		length--;
	}
	/**
	 * ensure the LRUPageFrame is empty or Not.
	 * @return
	 */
	public boolean isEmpty() {
		return length < 1;
	}

	public String toString() {
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while (node != null) {
			buffer.append(node.pageNum);

			node = node.next;
			if (node != null) {
				buffer.append(",");
			}
		}
		return buffer.toString();
	}

	private static class Node {
		Node prev;
		Node next;
		int pageNum;

		Node(Node prev, Node next, int pageNum) {
			this.prev = prev;
			this.next = next;
			this.pageNum = pageNum;
		}
	}
}
