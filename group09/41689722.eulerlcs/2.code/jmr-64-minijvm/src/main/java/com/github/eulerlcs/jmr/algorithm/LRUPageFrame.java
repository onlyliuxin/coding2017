package com.github.eulerlcs.jmr.algorithm;

/**
 * 用双向链表实现LRU算法
 * 
 * @author liuxin, eulerlcs
 */
public class LRUPageFrame {
	private static class Node {
		Node prev;
		Node next;
		int pageNum;
	}

	private int capacity;
	private int length = 0;
	private Node first;// 链表头
	private Node last;// 链表尾

	public LRUPageFrame(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param pageNum
	 */
	public void access(int pageNum) {
		Node node = findNode(pageNum);

		if (node != null) {
			moveToFirst(node);
		} else {
			node = new Node();
			node.pageNum = pageNum;
			addToFirst(node);
		}
	}

	private Node findNode(int pageNum) {
		Node node = first;

		while (node != null) {
			if (node.pageNum == pageNum) {
				return node;
			} else {
				node = node.next;
			}
		}

		return null;
	}

	private void moveToFirst(Node node) {
		if (node == first) {
			return;
		} else if (node == last) {
			last = node.prev;
		}

		if (node.prev != null) {
			node.prev.next = node.next;
		}
		if (node.next != null) {
			node.next.prev = node.prev;
		}

		first.prev = node;
		node.prev = null;
		node.next = first;

		first = node;
	}

	private void addToFirst(Node node) {
		if (first == null) {
			first = node;
			last = first;
		} else {
			first.prev = node;
			node.next = first;
			first = node;
		}

		length++;
		if (length > capacity) {
			last.prev.next = null;
			last = last.prev;

			length = capacity;
		}
	}

	@Override
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
}
