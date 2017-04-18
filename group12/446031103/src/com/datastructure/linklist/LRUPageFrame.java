package com.datastructure.linklist;

import java.util.Objects;

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
		//在该队列中存在， 则提到队列头
		if (node != null) {
			
			moveExistingNodeToHead(node);		
			
		} else{
			
			node = new Node();
			node.pageNum = pageNum;
			
			// 缓存容器是否已经超过大小.
			if (currentSize >= capacity) {			
				removeLast();
							
			} 
			
			addNewNodetoHead(node);
			
			
			
						
		}
	}
	
	private void addNewNodetoHead(Node node) {
		
		if(isEmpty()){
			
			node.prev = null;
			node.next = null;
			first = node;
			last = node;
			
		} else{
			node.prev = null;
			node.next = first;
			first.prev = node;
			first = node;
		}
		this.currentSize ++;
	}

	private Node find(int data){
		
		Node node = first;
		while(node != null){
			if(node.pageNum == data){
				return node;
			}
			node = node.next;
		}
		return null;
		
	}

	

	
	

	/**
	 * 删除链表尾部节点 表示 删除最少使用的缓存对象
	 */
	private void removeLast() {
		Node prev = last.prev;
		prev.next = null;
		last.prev = null;
		last = prev;
		this.currentSize --;
	}

	/**
	 * 移动到链表头，表示这个节点是最新使用过的
	 * 
	 * @param node
	 */
	private void moveExistingNodeToHead(Node node) {
		
		if (node == first) {
			
			return;
		}
		else if(node == last){
			//当前节点是链表尾， 需要放到链表头
			Node prevNode = node.prev;
			prevNode.next = null;	
			last.prev = null;
			last  = prevNode;				
			
		} else{
			//node 在链表的中间， 把node 的前后节点连接起来
			Node prevNode = node.prev;
			prevNode.next = node.next;
			
			Node nextNode = node.next;
			nextNode.prev = prevNode;
			
			
		}
		
		node.prev = null;
		node.next = first;
		first.prev = node;
		first = node;	
		
	}
	private boolean isEmpty(){		
		return (first == null) && (last == null);
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
	
	/*private static class Node {
		
		Node prev;
		Node next;
		int pageNum;

		Node(Node prev,Node next,int pageNum) {
			this.prev = prev;
			this.next = next;
			this.pageNum = pageNum;
		}
		Node(){
			
		}
	}

	private int capacity;
	private int addCnt ;
	private int size;
	private Node first;// 链表头
	private Node last;// 链表尾

	
	public LRUPageFrame(int capacity) {
		
		this.capacity = capacity;
		first = new Node();
	}

	*//**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 *//*
	public void access(int pageNum) {
		if(null==this.last){
			this.last = new Node(this.first,null,pageNum);
			this.first.next = this.last;
			addCnt++;
			size++;
			return;
		}
		if(contain(pageNum)){
			 if(Objects.equals(this.first.next.pageNum, pageNum)){
				 return;
			 }
			 boolean temp = Objects.equals(getLastNode(size).pageNum, pageNum);
			 Node currentNode = getNode(pageNum);
			 Node nextNode = currentNode.next;
			 Node preNode = currentNode.prev;
			 
			 currentNode.prev = this.first;
			 currentNode.next = this.first.next;
			 this.first.next = currentNode;
			 
			 if(temp){
				 preNode.next = null;
				 if(null==preNode.prev)
					 preNode.prev = currentNode;
			 }else{
				 preNode.prev = currentNode;
				 preNode.next = nextNode;
				 nextNode.next = null;
				 nextNode.prev = preNode;
			 }
			 this.last = getLastNode(size);
			 return;
		}
		Node addNode = new Node(this.first,this.first.next,pageNum);
		Node oldFirstnode=this.first.next ;
		oldFirstnode.prev = addNode;
		this.first.next = addNode;
		if(isOut()){
			
			addCnt++;
			if(this.size !=this.capacity )
				size++;			
			if(addCnt>size)
				this.last.prev.next = null;
			this.last = getLastNode(size);
		}else{
			this.last.prev.next = null;
		}
		
	
	}
	
	private boolean isOut(){
		return this.addCnt<=this.capacity;
	}
	
	private Node getLastNode(int sizes) {		
		Node node=this.first;
		for (int i = 0; i < sizes; i++) {
			node = node.next;
		}
		return node;
	}

	private boolean contain(Object o){
		Node node = this.first;
		
		while(null!=node.next){	
			
			node = node.next;
			
			if(Objects.equals(node.pageNum,o)){
				return true;
			}	
			
		}		
		return false;		
	}
	
	private Node getNode(Object o){
		Node node = this.first;
		
		while(null!=node.next){	
			
			node = node.next;
			
			if(Objects.equals(node.pageNum,o)){
				return node;
			}	
			
		}
		return null;
	}
	
	
	

	public String toString(){
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while(node.next != null){
			node = node.next;
			buffer.append(node.pageNum);
			if(node.next != null){
				buffer.append(",");
			}
		}
		return buffer.toString();
	}*/
	
}