package com.coding.basic.linklist;


/**
 * 用双向链表实现LRU算法
 * @author 996108220
 *
 */
public class LRUPageFrame {
	
	private static class Node {
		
		Node prev;
		Node next;
		int pageNum;

		Node() {
		}

		public Node(int pageNum2) {
			this.pageNum=pageNum2;
			this.next=null;
			this.prev=null;
		}
		
	}

	private int capacity;
	private int size;
	private Node first;// 链表头
	private Node last;// 链表尾

	
	public LRUPageFrame(int capacity) {
		
		this.capacity = capacity;
		this.size=0;
		
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		Node node=remove(pageNum);
		if (node==null) {
			node=new Node(pageNum);
			push(node);
		}
		else {
			addTail(node);
		}
		
	
	}
	

	private void push(Node node) {
		if (this.size()==capacity) {
			removeFirst();
		}
		addTail(node);		
	}

	private void addTail(Node node) {
		if (size==0) {
			first=node;
			last=node;
		}
		else {
			node.prev=last;
			last.next=node;
			last=node;
			
		}
		size++;
		
	}

	private void removeFirst() {

		Node node=this.first;
		first=first.next;
		node.next=null;
		first.prev=null;
		size--;
	}

	private int size() {

		return size;
	}

	private Node remove(int pageNum) {
		Node node=first;
		while(node!=null){
			if (node.pageNum==pageNum) {
				if (node==first) {
					first=node.next;
					node.next=null;
					first.prev=null;
				}
				else if (node==last) {
					last=node.prev;
					last.next=null;
					node.prev=null;
				}
				else {
					node.prev.next=node.next;
					node.next.prev=node.prev;
					node.next=null;
					node.prev=null;
				}
				size--;
				break;
			}
			node=node.next;
		}
		return node;
	}

	public String toString(){
		StringBuilder buffer = new StringBuilder();
		Node node = last;
		while(node != null){
			buffer.append(node.pageNum);			
			
			node = node.prev;
			if(node != null){
				buffer.append(",");
			}
		}
		return buffer.toString();
	}
	
}
