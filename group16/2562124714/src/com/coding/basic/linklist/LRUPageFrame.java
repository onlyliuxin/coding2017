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
	}

	private int capacity;
	
	
	private Node first;// 链表头
	private Node last;// 链表尾

	
	public LRUPageFrame(int capacity) {
		if (capacity <= 0)
		{
			capacity = 0;

		}

		this.capacity = capacity;
		
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param pageNum
	 * @return
	 */
	public void access(int pageNum) {
		if (first == null)
		{
			if (capacity == 0)
			{
				return;
			}

			Node newnode = new Node();
			newnode.pageNum = pageNum;
			newnode.prev = null;
			newnode.next = null;

			this.first = newnode;
			this.last = newnode;
			this.capacity--;
		}

		//遍历 最近访问过
		for (Node item = first; item.next != null; item = item.next)
		{
			if (item.pageNum == pageNum && item == first)
			{
				return;
			}
			if (item.pageNum == pageNum && item == last)
			{
				item.prev.next = null;
				last = item.prev;
				first.prev = item;
				item.next = first;
				item.prev = null;
				first = item;
				return;
			}
			if (item.pageNum == pageNum)
			{
				item.prev.next = item.next;
				item.next.prev = item.prev;
				item.next = first;
				first.prev = item;
				first = item;
				return;
			}
		}

		//没有访问过
		Node newnode = new Node();
		newnode.pageNum = pageNum;
		newnode.prev = null;
		newnode.next = null;
		if (this.capacity > 0)
		{
			first.prev = newnode;
			newnode.next = first;
			first = newnode;
			this.capacity --;
		}
		else
		{
			first.prev = newnode;
			newnode.next = first;
			first = newnode;
			Node templast = last;
			last = last.prev;
			last.next = null;
			templast = null;
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
