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
	
	private int curreantSize;
	private Node first;// 链表头
	private Node last;// 链表尾
	public LRUPageFrame(){
		this.first = null;
		this.last = first;
	}
	
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
		Node node = find(pageNum);
		//在该队列中存在
		if(node!= null){
			moveExistingNodeToHead(node);
		}
		else{
			node = new Node();
			node.pageNum = pageNum;
			
			//缓存容器是否已经超过大小
			if(curreantSize>=capacity){
				removeLast();
			}
			addNewNodeAtHead(node);
		
		}
	
	}
	
	

	private void moveExistingNodeToHead(Node node) {
		if(first.pageNum == node.pageNum ){
			return;
		}else if(last.pageNum == node.pageNum) {
			Node lastToHead = last;
			last = last.prev;
			last.next = null;
			lastToHead.prev = null;
			lastToHead.next = first;
			first.prev = lastToHead;
			first = lastToHead;
			
			
			
		}else {
			Node MiddleNode = first.next;
			first.next = last;
			last.prev = first;
			MiddleNode.next = first;
			MiddleNode.prev = null;
			first.prev = MiddleNode;
			first = MiddleNode;
			
			
		}
		
	}

	private void addNewNodeAtHead(Node node) {
		if(isEmpty()){
			node.prev = null;
			node.next = null;
			first = node;
			last = node;
		}else{
			node.prev = null;
			node.next =first;
			first.prev = node;
			first = node;
			
			
		}
		curreantSize++;
		
		
	}

	private boolean isEmpty() {
		// TODO Auto-generated method stub
		return (curreantSize==0);
	}

	private void removeLast() {
		Node prev = last.prev;
		prev.next = null;
		last.prev = null;
		last = prev ;
		this.curreantSize--;
		
	}

	private Node find(int pageNum) {
		Node node = first;
		while(node!=null){
			if(node.pageNum==pageNum){
				return node;
			}
			node = node.next;
			
			
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
