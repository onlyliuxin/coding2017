package com.coding.basic.linklist;


/**
 * 用双向链表实现LRU算法
 * 
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
	/**
	 * LRU算法:
	 */
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
		Node node = checkExist(pageNum);
		//如果这个页面在缓存中存在
		if (node != null) {
			// 将当前节点移动至第一个
			moveTofirst(node);
		} else {
			//不存在后比较当前是否已经满队列了
			if (size < capacity) {
				//如果还有空闲队列
				// 添加一个节点在栈顶
				final Node n = first;
				final Node firstNode = new Node();
				firstNode.next = n;
				firstNode.pageNum = pageNum;
				firstNode.prev = null;
				this.first = firstNode;
				if (n == null) {
					last = firstNode;
				} else {
					n.prev = firstNode;
				}
				size++;
			} else {
				//否则,添加一个节点在栈顶,栈底的节点移除
				addNode(pageNum);
			}
		}
	}
	//如果超出了缓存范围,则添加到栈顶,栈底的节点移除
	private void addNode(int pageNum) {
		Node node = new Node();
		node.pageNum = pageNum;
		node.next = first;
		first.prev = node;
		first = node;
		last = last.prev;
		last.next = null;
	}
	/**
	 * 如果在队列中存在,则移动至首位
	 * @param node
	 */
	private void moveTofirst(Node node) {
		if(node==first){
			return;
		}
		if(node==last){
			first.prev = node;
			node.next = first;
			first  = node;
			last = node.prev;
			last.next = null;
			first.prev = null;
			
		}else{
			node.prev.next = node.next;
			node.next.prev = node.prev;
			node.prev = null;
			node.next = first;
			first.prev = node;
			first = node;
		}
		
	}

	/**
	 * 检查是否在队列中存在页数
	 * @param pageNum
	 * @return
	 */
	private Node checkExist(int pageNum) {
		Node node = first;
		for(int i=0;i<size;i++){
			if(pageNum==node.pageNum){
				return node;
			}
			node=node.next;
		}
		return null;
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