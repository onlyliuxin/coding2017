package com.basic.linklist;

/**
 * 用双向链表实现LRU算法
 * 
 * @author liuxin
 *
 */
public class LRUPageFrame {

	private static class Node {

		private Node prev;
		private Node next;
		private int pageNum;

		Node(int pageNum) {
			this.pageNum = pageNum;
		}
	}

	private int capacity;

	private int size;

	private Node first;// 链表头
	private Node last;// 链表尾

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
		if (first == null && last == null) {
			handleEmptyContainer(pageNum);
			size++;
		} else if (existingPage(pageNum) != null) {
			if (size == 1) {
				return;
			} else if(existingPage(pageNum).pageNum == first.pageNum) {
				return;
			} else{
				moveToFirst(existingPage(pageNum));
			}
		} else {
			addFirst(pageNum);
			if (!ensureCapacity()) {
				removeLast();
			}
		}
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

	private boolean ensureCapacity() {
		return size <= capacity;
	}

	private void handleEmptyContainer(int pageNum) {
		first = new Node(pageNum);
		last = first;
	}

	private Node existingPage(int pageNum) {
		Node node = first;
		while (node != null) {
			if (node.pageNum == pageNum) {
				return node;
			}
			node = node.next;
		}
		return null;
	}

	private void addFirst(int pageNum) {
		Node newerFirstNode = new Node(pageNum);
		newerFirstNode.next = first;
		first.prev = newerFirstNode;
		first = newerFirstNode;
		size++;
	}

	private void removeLast() {
		Node lastPreNode = last.prev;
		last.prev = null;
		lastPreNode.next = null;
		last = lastPreNode;
		size--;
	}

	private void moveToFirst(Node existingPage) {
		if(existingPage.pageNum == last.pageNum){
			addFirst(existingPage.pageNum);
			removeLast();
		}else{
			int tempPageNum = first.pageNum;
			first.pageNum = existingPage.pageNum;
			existingPage.pageNum = tempPageNum;		
		}
	}

}