package com.coding.week4.linklist;

/**
 * 用双向链表实现LRU算法
 * @author liuxin
 *
 */
public class LRUPageFrame {
	
	private static class Node {
		
		Node prev;
		Node next;
		int pageNum;

		Node() {
		}

		public Node(Node prev, int pageNum,  Node next) {
			this.prev = prev;
			this.next = next;
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
	 * @param
	 * @return
	 */
	public void access(int pageNum) {
		if (!isFull()) {
			addFirst(pageNum);
		} else {
			if (contains(pageNum)) {
				removePage(pageNum);
				addFirst(pageNum);
			} else {
				removeLast();
				addFirst(pageNum);
			}
		}
	}
	
	public boolean contains(int pageNum) {
		Node node = first;
		if (first == null)
			return false;
		do {
			if (pageNum == node.pageNum)
				return true;
			node = node.next;
		} while (node != null );
		return false;
	}


	public void addFirst(int pageNum){
		Node node = new Node(null, pageNum, null);
		if (first == null) {
			first = node;
			last  = node;
		} else {
			Node oldFirst = first;
			first = node;
			node.next = oldFirst;
			oldFirst.prev = node;
		}
		size++;
	}


	public boolean isFull () {
		return size == capacity;
	}


	public void addLast(int pageNum) {
		Node oldLast = last;
		Node node = new Node(oldLast, pageNum, null);
		oldLast.next = node;
		last = node;
		size++;
	}


	public int removeLast(){
		Node oldLast = last;
		last = oldLast.prev;
		last.next = null;
		size--;
		return oldLast.pageNum;
	}


	public void removePage(int pageNum){
		Node node = first;
		if (first == null) {
			return;
		}
		do {
			if (node.pageNum == pageNum) {
				if (node == first) {
					removeFirst();
				} else if (node == last) {
					removeLast();
				} else {
					Node pre = node.prev;
					Node nex = node.next;
					pre.next = nex;
					nex.prev = pre;
					size--;
				}
				return;
			}
			node = node.next;
		} while (node != null);
	}

	private void removeFirst() {
		Node node = first;
		first = node.next;
		first.prev = null;
		node.next = null;
		size--;
	}


	public String toString(){
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while(node != null){
			buffer.append(node.pageNum);			
			
			node = node.next;
			if(node != null){
				buffer.append(",");
			}
		}
		return buffer.toString();
	}
	
}
