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
	private int currentSize;
	
	private Node first;// 链表头
	private Node last;// 链表尾

	
	public LRUPageFrame(int capacity) {
		
		this.capacity = capacity;
		currentSize = 0;
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		
		// 判断缓存是否命中
		Node n = find(pageNum);
		if(n != null){
			// 命中，则把节点提到表头
			moveNodeToHead(n);
		}else{
			// 若未命中，新增节点放到表头，如果链表长度超过capacity，移除链表尾部节点
			n = new Node();
			n.pageNum = pageNum;
			
			if(currentSize >= capacity){
				removeLast();
			}
			addNodeToHead(n);
		}
	}
	
	

	private void addNodeToHead(Node n) {
		if(first == null){
			first = n;
			last = n;
		}else{
			n.next = first;
			first.prev = n;
			first = n;
		}
		currentSize++;
	}

	private void removeLast() {
		Node prevN = last.prev;
		prevN.next = null;
		last.prev = null;
		last = prevN;
		currentSize--;
	}

	private void moveNodeToHead(Node n) {
		if(n == first){
			return ;
		}else if(n == last){
			Node preN = n.prev;
			preN.next = null;
			last.prev = null;
			last = preN;
		}else{
			// 中间节点 
			Node preN = n.prev;
			Node nextN = n.next;
			preN.next = nextN;
			nextN.prev = preN;
		}
		n.prev = null;
		n.next = first;
		first.prev = n;
		first = n;
	}

	private Node find(int pageNum) {
		Node n = first;
		while(n != null){
			if(n.pageNum == pageNum){
				return n;
			}
			n = n.next;
		}
		return null;
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
