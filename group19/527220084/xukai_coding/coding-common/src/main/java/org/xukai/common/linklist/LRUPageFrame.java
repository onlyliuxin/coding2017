package org.xukai.common.linklist;

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

		Node(int pageNum){
			this.pageNum = pageNum;
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
	 * @param pageNum
	 * @return
	 */
	public void access(int pageNum) {
		//先遍历，看是否已存在缓存中
		if (first != null) {
			Node node = first;
			while (node != null){
				if (node.pageNum == pageNum) {
					if (node.prev != null) {
						node.prev.next = node.next;
						if (node.next != null) {
							node.next.prev = node.prev;
						} else {
							last = node.prev;
						}
						node.next = first;
						node.prev = null;
						first.prev = node;
						first = node;
					}
					return;
				}
				node = node.next;
			}
		}
		//遍历不到，插入缓存中，并去除最少用的缓存
		if (last == null) {
			if (!(capacity > 0)) {
				return;
			}
			//一开始没有缓存的边界条件
			last = new Node(pageNum);
			first = last;
			size++;
			return;
		}
		Node node = new Node(pageNum);
		node.next = first;
		first.prev = node;
		first = node;
		size++;
		if (size > capacity) {
			//缓存已满，去除最少用缓存
			Node node2 = last.prev;
			node2.next = null;
			last = node2;
			size--;
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
