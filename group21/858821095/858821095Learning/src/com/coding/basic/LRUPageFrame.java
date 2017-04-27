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

		Node(int pageNum) {
			this.pageNum=pageNum;
		}
	}

	private int capacity;
	
//	TlinkedList tl=new TlinkedList();
	private Node first;// 链表头
	private Node last;// 链表尾
	private  int size=0;

	
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
		Node node = findNode(pageNum);
		if(node!=null){
			moveToFirst(pageNum);
		}else{
			if(isFull()){
				removeLast();
			}
			addNode(pageNum);
		}
	}



	private boolean isFull() {
		
		return this.size==this.capacity;
	}

	private void addNode(int pageNum) {
		Node newNode = new Node(pageNum);
		if(isEmpty()){
			newNode.prev=null;
			newNode.next=null;
			first=last=newNode;
		}else{
			newNode.prev=null;
			newNode.next=first;
			first.prev=newNode;
			
			
			first=newNode;
		}
		size++;
	}
	
	public int size(){
		return this.size;
	}

	private boolean isEmpty() {
		
		return this.size==0;
	}

	private void removeLast() {
		last=last.prev;
		last.next=null;
		size--;
	}

	private void moveToFirst(int pageNum) {
		if(first.pageNum==pageNum)
			return;
		if(last.pageNum==pageNum){
			Node node = last.prev;
			last.prev=null;
			last.next=first;
			first.prev=last;
			first=last;
			
			
			last=node;
			last.next=null;
			
		}else{
			Node pnode = findNode(pageNum);
			
			pnode.prev.next=pnode.next;
			pnode.next.prev=pnode.prev;
			
			pnode.next=first;
			first.prev=pnode;
			first=pnode;
		}
		
	}

	public Node findNode(int pageNum) {
		Node node=first;
		while(node!=null){
			if(node.pageNum==pageNum){
				return node;
			}
			node=node.next;
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
