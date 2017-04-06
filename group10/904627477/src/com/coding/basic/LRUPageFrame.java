package com.coding.basic;

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
		Node node = findPageNumNode(pageNum);
		if(node==null){
			if(size()>=capacity){
				removeLast();
			}
			push(pageNum);
		}else{
			moveToFirst(node);
		}
	}

	public Node findPageNumNode(int pageNum) {
		Node node = first;
		while(node!=null){
			if(node.pageNum==pageNum){
				break;
			}else{
				node = node.next;
			}
		}
		return node;
	}

	public void moveToFirst(Node node) {
		if(first==null||node==null||first.pageNum==node.pageNum){
			return;
		}
		if(node.pageNum == last.pageNum){
			node.prev.next = null;
			last = node.prev;
		}else{
			Node tPrev = node.prev;
			Node tNext = node.next;
			tPrev.next = tNext;
			tNext.prev = tPrev;
		}
		node.prev = null;
		node.next = first;
		first = node;
	}

	public void push(int pageNum) {
		if(size()==0){
			first = new Node();
			first.pageNum = pageNum;
			last = first;
			return ;
		}
		Node node;
		node = new Node();
		node.pageNum = pageNum;
		node.next = first;
		first.prev = node;
		first = node;
	}

	public void removeLast() {
		if(last==null){
			return ;
		}
		if(size()==1){
			first = null;
			last = null;
			return ;
		}
		Node temp = last.prev;
		last.prev = null;
		last = temp;
		last.next = null;
	}

	public int size() {
		int size = 0;
		Node temp = first;
		while (temp!=null) {
			size++;
			temp = temp.next;
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
