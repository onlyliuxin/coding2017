package com.vvv.basic;

public class LRUPageFrame {
	/**
	 * 用双向链表实现LRU算法
	 *
	 */
	
private static class Node {
		
		Node prev;
		Node next;
		int pageNum;

		Node() {
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
        if(size < capacity){
        	addToFirst(pageNum);
        	return;
        }
        
        if(first.pageNum == pageNum){
        	return;
        }
        
        Node n = getNode(pageNum);
        if(n == null){    
        	last.prev.next = null;
        	last = last.prev;
        	size--;
        	addToFirst(pageNum);
        }else{
        	moveToFirst(n);
        }
	}
	
	 public void moveToFirst(Node node) {
		   node.prev.next = node.next;
		   node.next.prev = node.prev;
		   node.prev = null;
		   node.next = first;
		   first.prev = node;
		   first = node;
	 }
	 
	 public void addToFirst(int pageNum) {
	     	Node node = new Node();
	        if(first == null){
	        	node.pageNum = pageNum;
	        	node.next = node.prev = null;
	        	first = 	last = node;
	        	size++;
	        	return;
	        }
	        
		 node.pageNum = pageNum;
		 node.prev = null;
		 node.next = first;
		 first.prev = node;
		 first = node;
		 size++;
	 }
	 
	private Node getNode(int pageNum){
		Node node = first;
        while(node.next!=null){        	
        	if(node.pageNum == pageNum){
        		return node;
        	}
        	node = node.next;
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
		System.out.println(buffer.toString());
		return buffer.toString();
	}
}
