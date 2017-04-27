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
	private int size;

	
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
		Node target = findNode(pageNum);
		if(target != null){//已经在内存
			//移动到头部
			moveToFirst(target);
			return;
		}
		//没有在内存 需要换页
		
		target = new Node();
		target.pageNum = pageNum;
		target.prev = null;
		if(size < capacity){
			size++;
		}else{
			removeLast();
		}
		moveToFirst(target);
	}
	
	

	private void removeLast() {
		last.prev.next = null;
		last.next = null;
	}

	private void moveToFirst(Node target) {
		Node prev = target.prev;
		Node next = target.next;
		if(null != prev){
			prev.next = target;
		}
		if(null != next){
			next.prev = prev;
		}
		if(null != first){
			first.prev = target;
		}
		target.next = first;
		first = target;
		
	}

	private Node findNode(int pageNum) {
		Node current = first;
		while(current.next != null){
			if(pageNum == current.pageNum){
				return current;
			}
			current = current.next;
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
