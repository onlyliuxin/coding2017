package com.coding.basic.linkedList;

/**
 * 用双向链表实现LRU算法
 * @author liuxin
 *
 */
public class LRUPageFrame {
	
	private static int stateAtHead = 0;
	private static int stateAtLast = 1;
	private static int stateAtMid = 2;
	
	private static class Node {
		
		Node prev;
		Node next;
		int pageNum;

		Node(){
			
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
		/**
		 * 空链表
		 */
		size ++;
		Node newNode = new Node();
		newNode.pageNum = pageNum;
		if(size <= capacity){
			if(last == null){
				last= newNode;
				first = newNode;
			}else{
				Node preHead = first;//记录原来的头节点
				first = newNode;
				first.next = preHead;
				preHead.prev = first;
			}
		}else{
			Node currentNode = first;
			int judgeNum = currentNode.pageNum;
			Node preHead = first;//记录原来的头节点
			Node preLast = last;//记录原来的尾节点
			int state = -1;//记录当前状态
			/**
			 * 当前插入的值为等于链表头的位置
			 */
			
			int k = 0;
			if(judgeNum == pageNum){
				state = stateAtHead;
			}else if(preLast.pageNum == pageNum){
				state = stateAtLast;
			}
			while(currentNode != null && k < capacity - 1){
				k++;
				currentNode = currentNode.next;
				judgeNum = currentNode.pageNum;
				if(judgeNum == pageNum && judgeNum != preLast.pageNum){
					state = stateAtMid;
					break;
				}
			}
			
			switch (state) {
			case -1:
				first = newNode;
				last = preLast.prev;
				first.next = preHead;
				first.prev = null;
				preHead.next = last;
				preHead.prev = first;
				last.next = null;
				break;
			case 0:
				
				break;

			case 1:
				last = preLast.prev;
				last.next = null;//原来尾节点的上一个节点变成了尾节点
				first = newNode;
				first.prev = null;
				first.next = preHead;
				break;
			case 2:
				first = newNode;
				first.next = preHead;
				first.prev = null;
				preHead.next = last;
				preHead.prev = first;
				last.prev = preHead;
				break;
			}
			
		}
		
		
		
	
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
