package org.wsc.coding.basic.linklist;

/**
 * <h3>LRU算法</h3>
 * <p>
 * 最近最少使用
 * </p>
 *
 * @author Administrator
 * @date 2017年3月28日上午10:56:33
 * @version v1.0
 *
 */
public class LRUPageFrame {

	private static class Node {

		Node prev;
		int pageNum;
		Node next;

		Node() {
		}

		Node(Node prev, int pageNum, Node next) {
			super();
			this.prev = prev;
			this.pageNum = pageNum;
			this.next = next;
		}

	}

	private int capacity;
	private Node first;// 链表头
	private Node last;// 链表尾
	private int size;// 长度

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
		// 首先检查链表中是否已存在改页码
		Node node = find(pageNum);
		if (node == null) {
			// 满则删除尾部后插入
			if (isFull())
				unlinkLast();
			put(pageNum);
		} else {
			Node prev = node.prev;
			Node next = node.next;
			Node oldFirst = first;
			if(prev != null){
				first = node;
				prev.next = next;
				node.prev = null;
				node.next = oldFirst;
				oldFirst.prev = node;
				if(next == null)
					last = prev;
				else
					next.prev = prev;
					
			}
		}

	}

	/**
	 * 添加至头节点
	 * 
	 * @param pageNum
	 */
	void put(int pageNum) {
		Node oldFirst = first;
		Node newNode = new Node(null, pageNum, oldFirst);
		first = newNode;
		if (oldFirst == null) {
			last = newNode;
		} else {
			oldFirst.prev = newNode;
		}
		size++;
	}

	/**
	 * 删除尾节点
	 */
	void unlinkLast() {
		Node oldLast = last;
		Node prev = oldLast.prev;
		if (oldLast != null) {
			if (prev == null)
				first = null;
			else {
				prev.next = null;
			}
			last = prev;
		}
		size--;
	}

	Node find(int pageNum) {
		Node node = last;
		while (node != null) {
			if (node.pageNum == pageNum)
				return node;
			node = node.prev;
		}
		return node;
	}

	boolean isFull() {
		return size == capacity ? true : false;

	}

	public String toString() {
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while (node != null) {
			buffer.append(node.pageNum);

			node = node.next;
			if (node != null) {
				buffer.append(",");
			}
		}
		return buffer.toString();
	}
}
