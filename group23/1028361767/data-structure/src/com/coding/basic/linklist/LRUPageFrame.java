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

		Node(int pageNum) {
			this.pageNum = pageNum;
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
		/**
		 * 1、链表为空，直接插入first
		 * 2、链表未满，往链尾追加
		 * 3、链表已满，判断链表中是否存在pageNum，若存在，把pageNum，移到链尾；若不存在，把链头删除，把pageNum追加到链尾
		 */
		Node newNode = new Node(pageNum);
		if(size < capacity){
			if(size == 0){
				first = newNode;
			}else{
				last.next = newNode;
				newNode.prev = last;
			}
			last = newNode;
			size++;
			return ;
		}
		Node existNode = null;
		Node tmp = first;
		do{
			if(tmp.pageNum == pageNum){
				existNode = tmp;
			}
			tmp = tmp.next;
		}while(tmp != null);
		if(existNode == null || existNode == first){
			tmp = first.next;
			tmp.prev = null;
			first = tmp;//链首变为链首后一个节点
			last.next = newNode;
			newNode.prev = last;
			last = newNode;//链尾追加新节点
		}else if(existNode == last){
				return ;
		}else{
			/**
			 * 已存在节点的上一个节点的下一个节点指向已存在节点的下一个节点
			 * 
			 */
			Node prev = existNode.prev;
			Node next = existNode.next;
			prev.next = next;
			next.prev = prev;
			if(prev.prev == null){
				first = prev;
			}
			newNode.prev = last;
			last.next = newNode;
			last = newNode;
			existNode = null;
		}
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
