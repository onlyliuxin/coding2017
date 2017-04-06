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

		Node(int pageNum, Node first) {
			this.pageNum = pageNum;
			this.next = first;
			this.prev = null;
		}
	}

	private int capacity;
	private int currSize;
	
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
	public void access(int pageNum) throws Exception {
		if (pageNum < 0) {
			throw new Exception("error pageNum = " + pageNum);
		}
		if (currSize < capacity) {
			if (judgeAdd(pageNum, false)) {
				add(pageNum);
			}
		}else {
			if (judgeAdd(pageNum, true)) {
				add(pageNum);
			}
		}
	}

	public void add(int pageNum) {
		Node node = new Node(pageNum, first);
		if (currSize == 0) {
			first = node;
			last = node;
		}
		first.prev = node;
		first = node;
		currSize ++;
	}
	
	public boolean judgeAdd(int pageNum, boolean isFull) {
		Node node = first;
		//如果pageNum已存在于栈顶，则不添加
		if (first != null && first.pageNum == pageNum) {
			return false;
		}
		while (node != null) {
			//如果pageNum存在栈中间，则删除该页面
			if (node.pageNum == pageNum) {
				node.prev.next = node.next;
				if (node.next != null) {
					node.next.prev = node.prev;
				}else {
					last = node.prev;
				}
				node.next = null;
				node.prev = null;
				currSize--;
				return true;
			}
			node = node.next;
		}
		//如果现有栈中没有pageNum, 栈满则删除底部物理页
		if (isFull && currSize != 0) {
			Node temp = last;
			last = temp.prev;
			last.next = null;
			temp.prev = null;
			currSize--;
		}

		return true;
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
