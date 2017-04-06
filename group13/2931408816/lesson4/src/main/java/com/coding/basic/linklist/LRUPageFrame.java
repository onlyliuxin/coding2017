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

		public Node(int pageNum) {
			this.pageNum = pageNum;
		}
	}

	private int capacity;
	private int curSize = 0;
	
	private Node first;// 链表头
	private Node last;// 链表尾

	
	public LRUPageFrame(int capacity) {
		
		this.capacity = capacity;
		
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param pageNum
	 * @return
	 */
	public void access(int pageNum) {
		if (first == null){
			first = new Node(pageNum);
			if (last == null){
				last =first;
			}
			curSize++;
			return;
		}
		if (curSize < capacity){
			Node node = new Node(pageNum);
			node.next=first;
			first.prev=node;
			first=node;
			curSize++;
			return;
		}
		Node node = first;
		// 是否已存在
		while (node.next!=null){
			if (node.pageNum == pageNum){
				// 存在即交换
				if (first.pageNum == pageNum){
					return;
				}
				if (node.prev!=null){
					node.prev.next=node.next;
				}
				if (node.next!=null){
					node.next.prev=node.prev;
				}
				node.prev=null;
				node.next=first;
				first.prev=node;
				first=node;
				return;
			}
			node=node.next;
		}
		// 把最后一个节节点移到开头
		node = last;
		last=last.prev;
		last.next=null;
		node.next=first;
		first.prev=node;
		first=node;
		node.pageNum=pageNum;
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

	public static void main(String[] args) {

		LRUPageFrame frame = new LRUPageFrame(3);
		frame.access(7);
		frame.access(0);
		frame.access(1);
		System.out.println(frame);
		frame.access(2);
		System.out.println(frame);
	}
}
