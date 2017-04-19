package com.donaldy.basic;

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

		Node( int pageNum) {
			this.pageNum = pageNum;
		}
	}

	private int capacity;
	
	
	private Node first;// 链表头
	private Node last;// 链表尾

	private int spareNum = 3;

	
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

		/**
		 * 1.找到，
		 * 	则将此元素提到队首
		 * 2.找不到，则new一个，并加在队首
		 * 	1.队伍是满，则踢掉队尾
		 * 	2.队伍不满，则添加在队首
		 */
		Node node = first;

		while (node != null) {

			if (pageNum == node.pageNum) {

				if (node == first)
					return;

				if (node == last) {
					final Node prevNode = node.prev;
					prevNode.next = null;
					last = prevNode;
					node.prev = null;
					node.next = first;
					first.prev = node;
					first = node;

					return ;
				}

				final Node prevNode = node.prev;
				final Node nextNode = node.next;
				prevNode.next = nextNode;
				nextNode.prev = prevNode;

				node.prev = null;
				node.next = first;
				first = node;

				return;
			}

			node = node.next;
		}

		Node newNode = new Node(pageNum);

		if (spareNum == 0) {
			final Node f = first;
			final Node l = last;

			f.prev = newNode;
			newNode.next = f;
			first = newNode;

			last = l.prev;
			last.next = null;
			l.prev = null;

			return ;
		}

		if (spareNum == capacity) {
			first = newNode;
			last = newNode;
			spareNum --;
			return;
		}

		first.prev = newNode;
		newNode.next = first;
		first = newNode;
		spareNum --;
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
