package com.basic;

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

		Node(int pageNum) {
			this.pageNum = pageNum;
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
	 * @param pageNum
	 * @return
	 */
	public void access(int pageNum) {

		// 遍历链表, 如果存在, 则将对应结点移至表头
		if (!moveToHead(pageNum)) {
			// 如果不存在, 则新建并放入表头
			// 新建时如果容量已满, 删除表尾结点
			if (size() == capacity) delTailNode();
			headInsert(new Node(pageNum));
		}
	}

	public int getFirst() {
		return first.pageNum;
	}
	// 头插法
	private void headInsert(Node headNode) {

		// 链表不为空
		if (first != null) {
			first.prev = headNode;
			headNode.next = first;
			first = headNode;
		} else {
			first = last = headNode;
		}
	}


	// 删除尾结点
	private void delTailNode() {

		if (this.first == this.last) {
			return;
		}

		last.prev.next = null;
		last = last.prev;
	}

	// 取得链表长度
	private int size() {

		if (first == null) return 0;

		int size = 1;
		Node current = this.first;
		while (current.next != null) {
			size++;
			current = current.next;
		}
		return size;
	}

	// 将链表中已存在的结点移至表头并返回true, 若该结点不存在则返回false
	private boolean moveToHead(int pageNum) {

		if (first == null) return false;
		// 如果是表头,则直接返回。 考虑到局部性原理, 表头命中的可能性较高, 故放在开头判断。
		if (pageNum == first.pageNum) return true;

		Node current = this.first.next;
		while (current != null) {
			if (current.pageNum == pageNum) {
				// 查找成功, 将此结点移至表头
				if (current != last) {
					current.prev.next = current.next;
					current.next.prev = current.prev;
					current.next = first;
					current.prev = null;
					first.prev = current;
					first = current;
					return true;
				}
				// 表尾
				current.prev.next = null;
				current.next = first;
				last = current.prev;
				first = current;
				return true;
			}
			current = current.next;
		}
		return false;
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
