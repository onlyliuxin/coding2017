package com.dudy.learn01.data_structure.list;

public class LRUPageFrame {

	private static class Node {

		Node prev;
		Node next;
		int pageNum;

		Node() {
		}
	}

	private int capacity;

	private int currentSize;
	private Node first;// 链表头
	private Node last;// 链表尾

	public LRUPageFrame(int capacity) {
		this.currentSize = 0;
		this.capacity = capacity;

	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		Node node = find(pageNum);

		if (node != null) {
			moveNodeToHead(node);
		} else {
			node = new Node();
			node.pageNum = pageNum;
			// 缓存容器是否已满
			if (currentSize >= capacity) {
				removeLastNode();
			}

			addNewNodeToHeader(node);
		}
	}

	private void addNewNodeToHeader(Node node) {
		// TODO Auto-generated method stub
		
		if (first == null) {
			first = node;
			last = node;
			currentSize++;
			return;
		}
		
		node.next = first;
		if(first.next == null){
			last.prev = node;
		} else {
			first.prev = node;
		}
		first = node;
		currentSize++;

	}

	private void removeLastNode() {
		// TODO Auto-generated method stub
		last.prev.next = null;
		last = last.prev;
		currentSize--;
	}

	private void moveNodeToHead(Node node) {
	
		if(first.pageNum == node.pageNum){
			return;
		}
		
		if(last.pageNum == node.pageNum){			
			Node pLast = last.prev;
			pLast.next = null;
			
			last.next = first;
			last.prev = null;
			
			first.prev = last;
			first = last;
			last = pLast;
			
			return;
		}
		
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.prev = null;
		node.next = first;
		first.prev = node;
		first = node;
		
	}

	private Node find(int pageNum) {
		Node n = first;
		for (int i = 0; i < currentSize; i++) {
			if (n.pageNum == pageNum) {
				return n;
			}

			n = n.next;
		}

		return null;
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
		
		StringBuilder reverse = new StringBuilder();
		Node n = last;
		while(n != null){
			reverse.append(n.pageNum);
			n = n.prev;
			if(n != null){
				reverse.append(",");
			}
		}
		System.out.println("buffer: "+ buffer.toString());
		System.out.println("reverse: "  + reverse.toString());
		return buffer.toString();
	}

}