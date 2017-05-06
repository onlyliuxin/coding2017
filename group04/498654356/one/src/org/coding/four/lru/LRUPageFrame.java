package org.coding.four.lru;

/**
 * 用双向链表实现LRU(最近最少使用)算法
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
	}

	private int capacity;
	
	
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
		if(first == null) {
			first = new Node();
			first.pageNum = pageNum;
			last = first;
		} else {
			if(isFull()) { 
				Node node = findNode(pageNum);
				if(node == null) { // not found
					removeLastNode();
					push(pageNum);
				} else {
					if(node == first) {
						return ;
					}
					if(node == last) {
						removeLastNode();
						node.next = first;
						first.prev = node;
						first = node;
						return;
					}
					node.prev.next = node.next;
					node.next.prev = node.prev;
					node.prev = null;
					node.next = first;
					first = node;
				}
			} else {
				push(pageNum);
			}
		}
	
	}

	private boolean isFull() {
		return getSize() == capacity;
	}

	private void removeLastNode() {
		last = last.prev;
		last.next = null;
	}

	private void push(int pageNum) {
		Node node = new Node();
		node.pageNum = pageNum;
		node.next = first;
		first.prev = node;
		first = node;
	}
	
	

	private Node findNode(int pageNum) {
		if(first.pageNum == pageNum) {
			return first;
		}
		Node node = first;
		while(node.next != null)  {
			node = node.next;
			if(node.pageNum == pageNum) {
				return node;
			}
		}
		return null;
	}

	private int getSize() {
		if(first == null) {
			return  0;
		}
		Node node = first;
		int size = 1;
		while(node.next != null) {
			size++;
			node = node.next;
		}
		return size;
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
