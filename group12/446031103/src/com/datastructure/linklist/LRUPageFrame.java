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

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
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
	}
	
}