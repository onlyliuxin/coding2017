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
		
		Node(int pageNum, Node prev, Node next){
			this.pageNum = pageNum;
			this.next = next;
			this.prev = prev;
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
		System.out.println("CurrentList= "+this.toString()+"; accessing - "+pageNum);
		Node target = find(pageNum);
		if(target==null){
			createNewNodeAsHead(pageNum);
		}else{
			moveExistingNodeToHead(target);
		}
	
	}
	
	private void removeLast(){
		Node secToLast = last.prev;
		last = null;
		secToLast.next = null;
		last = secToLast;
		size--;
	}
	
	private void moveExistingNodeToHead(Node node){
		
		Node prev = node.prev;
		Node next = node.next;
		
		if(prev==null){
			//already in the head,do nothing;
			return;
		}
		
		if(next==null){
			//currently in the tail
			last = prev;
		}
		
		//in the middle
		prev.next = next;
		if(next!=null){
			next.prev = prev;
		}
		node.prev = null;
		node.next = first;
		first = node;
	}
	
	private void createNewNodeAsHead(int value){
		Node node = new Node(value,null,null);
		//first node
		if(size==0){
			this.first = node;
			this.last = node;
			this.size ++;
		}else{
			//linklist already exists
			this.first.prev = node;
			node.next = this.first;
			this.first = node;
			this.size++;
	
			if(size>capacity){
				removeLast();
			}
		}
		
	}
	
	private Node find(int value){
		if(size==0){
			return null;
		}
		Node temp = first;
		while(temp!=null){
			if(temp.pageNum==value){
				return temp;
			}else{
				temp = temp.next;
			}
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
