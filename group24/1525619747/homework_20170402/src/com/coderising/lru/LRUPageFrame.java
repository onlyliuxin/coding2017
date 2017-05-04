package com.coderising.lru;


public class LRUPageFrame {
	
	private static class Node {
		
		Node prev;
		Node next;
		int pageNum;

		Node() {
			prev = null;
			next = null;
		}
		Node(int pageNum) {
			this.pageNum = pageNum;
			prev = null;
			next = null;
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
//		System.out.println(node + " " + isFull());
		if (node != null) { // 队列中含有节点，移到头部
			moveExistedNodeToHead(node);
		} else { // 队列中没有该节点，需添加
			node = new Node(pageNum);
			if (!isFull()) {
				// 在头部添加节点
				addNodeToHead(node);
			} else {
				// 删掉尾部节点，再在头部添加节点
				deleteLastNode();
				addNodeToHead(node);
			}
		}
	}
	
	private void deleteLastNode() {
		// TODO Auto-generated method stub
		if (currentSize == 0) {
			return;
		}
		if (currentSize == 1) {
			first = last = null;
			--currentSize;
			return;
		}
		Node toDelete = last;
		Node lastPrev = last.prev;
		lastPrev.next = last.next;
		last.next.prev = lastPrev;
		last = lastPrev;
		toDelete = null;
		--currentSize;
	}

	private void addNodeToHead(Node node) {
		// TODO Auto-generated method stub
		if (first == null) {
			first = last = node;
			++currentSize;
			return;
		}
		if (currentSize == 1) {
			node.prev = first;
			node.next = first;
			first.prev = node;
			first.next = node;
			first = node;
			++currentSize;
			return;
		}
		node.prev = first.prev;
		first.prev.next = node;
		node.next = first;
		first.prev = node;
		first = node;
		++currentSize;
	}

	private void moveExistedNodeToHead(Node node) {
		// TODO Auto-generated method stub
		if (node == first) {
			return;
		}
		if (node == last) {
			Node lastPrev = last.prev;
			first = last;
			last = lastPrev;
			return;
		}
		node.prev.next = node.next;
		node.next.prev = node.prev;
		
		node.next = first;
		node.prev = first.prev;
		first.prev.next = node;
		first.prev = node;
		first = node;
	}

	private Node find(int pageNum) {
		Node pointer = first;
		while (pointer != null && pointer != last) {
			if (pointer.pageNum == pageNum) {
				return pointer;
			}
			pointer = pointer.next;
		}
		// pointer == last now
		if (pointer != null && pointer.pageNum == pageNum) {
			return pointer;
		}
		
		return null;
	}
	
	private boolean isFull() {
		return (currentSize == capacity);
	}

	public String toString() {
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		// 由于个人实现的双链表是环链的，所以这里做了更改
		while(node != null && node != last){
			buffer.append(node.pageNum);			
			
			node = node.next;
			if(node != null){
				buffer.append(",");
			}
		}
		if (node != null) {
			buffer.append(node.pageNum);
		}
		System.out.println(buffer.toString());
		return buffer.toString();
	}

}
