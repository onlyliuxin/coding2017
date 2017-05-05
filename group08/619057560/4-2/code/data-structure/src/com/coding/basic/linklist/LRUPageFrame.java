package com.coding.basic.linklist;

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
	}

	private int capacity;
	
	
	private Node first;// 链表头
	private Node last;// 链表尾
	private int length;// 链表长度

	
	public LRUPageFrame(int capacity) {
		
		this.capacity = capacity;
		this.length = 0;
		
	}
	
	private Node findNode(int pageNum) {
		for (Node pNode = first; pNode != null; pNode = pNode.next) {
			if (pNode.pageNum == pageNum) {
				return pNode;
			}
		}
		
		return null;
	}
	
	private void ensureLength() {
		while (length > capacity && last != null) {
			last = last.prev;
			length--;
		}
		
		if (last == null) {
			first = null;
		}
		else {
			last.next = null;
		}
	}
	
	private void addFirstNode(Node pNode) {
		if (pNode == null) {
			return;
		}
		
		pNode.next = first;
		
		if (first != null) {
			first.prev = pNode;
		}
		
		first = pNode;
		
		if (last == null) {
			last = pNode;
		}
		
		length++;
	}
	
	private Node removeNode(Node pNode) {
		if (pNode == null) {
			return null;
		}
		
		Node prevN = pNode.prev;
		Node nextN = pNode.next;
		
		if (pNode == first) {
			first = nextN;
		}
		if (pNode == last) {
			last = prevN;
		}
		if (prevN != null) {
			prevN.next = nextN;
		}
		if (nextN != null) {
			nextN.prev = prevN;
		}
		
		pNode.prev = null;
		pNode.next = null;
		length--;
		
		return pNode;
	}
	
	private void addNewPage(int pageNum) {
		Node pNode = new Node();
		pNode.pageNum = pageNum;
		addFirstNode(pNode);
	}
	
	private void movePageToFirst(Node pNode) {
		if (pNode == null) {
			return;
		}
		
		addFirstNode(removeNode(pNode));
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		Node pNode = findNode(pageNum);
		if (pNode == null) {
			addNewPage(pageNum);
			ensureLength();
			return;
		}
		
		movePageToFirst(pNode);
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
