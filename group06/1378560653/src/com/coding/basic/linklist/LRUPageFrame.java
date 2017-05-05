package com.coding.basic.linklist;

/**
 * 用双向链表实现LRU算法
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

	private int capacity;//容量
	
	private Node first;  // 链表头
	private Node last;   // 链表尾

	
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
		if(capacity == 0){
			return;
		}
		
		Node node = new Node(pageNum);
		
		//填满：从后往前填满
		if(!isFull()){
			if(first == null && last == null){
				first = node;
				last = node;
				first.prev = null;
				last.prev = null;
			} else {
				first.prev = node;
				node.next = first;
				first = node;
			}
		} else {
			if(!isFind(pageNum)){
				first.prev = node;
				node.next = first;
				first = node;
				last = last.prev;
				last.next = null;
			} else {
				Node pNode = first;
				if(first.pageNum == pageNum){
					return;
				} 
				
				//注意：while循环只是遍历了1~last.prev的节点
				while(pNode.next != null){
					if(pNode.pageNum == pageNum){
						pNode.next.prev = pNode.prev;
						pNode.prev.next = pNode.next;
						pNode.next = first;
						first.prev = pNode;
						first = pNode;
						break;
					}
					pNode = pNode.next;
				}
				
				if(last.pageNum == pageNum){
					last.next = first;
					first.prev = last;
					first = last;
					last = last.prev;
					last.next = null;
				}
			}
		}

	}
	
	private boolean isFind(int pageNum) {
		Node pNode = first;
		while(pNode != null){
			if(pNode.pageNum == pageNum){
				return true;
			}
			pNode = pNode.next;
		}
		return false;
	}
	
	public boolean isFull() {
		int count = 0;
		Node pNode = first;
		while(pNode != null){
			count++;
			pNode = pNode.next;
		}
		
		if(count < capacity){
			return false;
		} else {
			return true;
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
	
	public static void main(String args[]){
		LRUPageFrame frame = new LRUPageFrame(3);
		frame.access(7);
		frame.access(0);
		frame.access(1);
		System.out.println(frame.toString());
		frame.access(2);
		System.out.println(frame.toString());
		frame.access(0);
		System.out.println(frame.toString());
		frame.access(0);
		System.out.println(frame.toString());
		frame.access(3);
		System.out.println(frame.toString());
		frame.access(0);
		System.out.println(frame.toString());
		frame.access(4);
		System.out.println(frame.toString());
	}
}