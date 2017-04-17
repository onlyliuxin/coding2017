package com.coding.lru;

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
		
		this.capacity = capacity;
		
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		
		if (first == null) {
			accessFirst(pageNum);
			return;
		}
		if (capacity > 0) {  
			accessNormally(pageNum);
			capacity --;
		} else {
			Node node = first;
			while(node != null) {
				
				if (node.pageNum == pageNum) {
					accessNodeExisting(node);
					return;
				}
				
				node = node.next;
			}
			
			accessNormally(pageNum);
			
			removeLast();
		}
		
	}

	private void accessNodeExisting(Node node) {
		if (node.next == null) {  //最后一个元素为要添加的元素
			removeLast();          
			exchangeFirstWithNode(node);
		} else if (node.prev != null) {  //要添加的元素在中间
			Node n = node.next;
			Node p = node.prev;
			p.next = n;
			n.prev = p;
			
			exchangeFirstWithNode(node);
		}
	}

	private void exchangeFirstWithNode(Node node) {
		node.next = first;
		first.prev = node;
		node.prev = null;	//忘记这个就会导致找不到第一个添加的元素，测试出现堆溢出
		first = node;
	}

	private void removeLast() {
		last = last.prev;
		last.next = null;
	}

	private void accessNormally(int pageNum) {
		
		Node temp = first;
		first = new Node();
		first.pageNum = pageNum;
		first.next = temp;
		temp.prev = first;
		
	}

	private void accessFirst(int pageNum) {
		
		first = new Node();
		first.pageNum = pageNum;
		last = first;
		capacity --;
		
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
