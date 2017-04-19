package com.sprint.basic;

public class LRUPageFrame {
	
	/**
	 * 用双向链表实现LRU算法
	 */

	private static class Node {
		Node prev;
		Node next;
		int pageNum;

		Node()  {
		
		}
	}

	private int capacity;
	private int currentSize;
	private Node first;
	private Node last;

	public LRUPageFrame(int capacity) {
		this.currentSize = 0;
		this.capacity = capacity;	
	}

	/**
	 *
	 */
	public void access(int pageNum) {
		Node node = find(pageNum);
		if (node != null) {
			moveExistingNodeToHead(node);
		} else {
			node = new Node();
			node.pageNum = pageNum;
			if (currentSize >= capacity) {
				removeLast();
			}
			addNewNodeToHead(node);
		}
	}	

	private Node find(int pageNum) {
		Node node = first;
		while (node != null) {
			if (node.pageNum == pageNum) {
				return node;
			}
			node = node.next;
		}
		return null;
	}

	private void moveExistingNodeToHead(Node node) {
		if (node == first) {
			return;
		} else if (node == last) {
			Node prevNode = node.prev;
			prevNode.next = null;
			last.prev = null;
			last = prevNode;
		} else {
			Node prevNode = node.prev;
			prevNode.next = node.next; 
			Node nextNode = node.next;
			nextNode.prev = prevNode;
		}

		node.prev = null;
		node.next = first;
		first.prev = node;
		first = node;
	}

	private void removeLast() {
		Node prev = last.prev;
		prev.next = null;
		last.prev = null;
		last = prev;
		this.currentSize--;
	}

	private void addNewNodeToHead(Node node) {
		if (isEmpty()) {
			node.prev = null;
			node.next = null;
			first = node;
			last = node;
		} else {
			node.prev = null;
			node.next = first;
			first.prev = node;
			first = node;
		}	
		this.currentSize++;
	} 

	private boolean isEmpty() {
		return (first == null) && (last == null);
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
