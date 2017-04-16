package com.coding.basic.linklist;


public class LRUPageFrame {
	
	private static class Node {
		
		Node prev;
		Node next;
		int pageNum;

		Node() { }
	}

	private int capacity;
	
	private int currentSize;
	private Node first;// 链表头
	private Node last;// 链表尾
	
	public LRUPageFrame(int capacity) {
		this.currentSize = 0;
		this.capacity = capacity;
		
	}
	
	/**
	 * 获取缓存中对象
	 */
	public void access(int pageNum) {
		if(capacity <= 0)
			throw new RuntimeException("capacity must > 0");
		Node newNode = new Node();
		newNode.pageNum = pageNum;
		if(currentSize == 0){
			makeNewPageFrame(newNode);
		}else if(currentSize == 1) {
			insertPageFrameOn1(newNode);//没有相等的才插入，否则就什么都不做
		}else if(currentSize == 2) {
			insertPageFrameOn2(newNode);
		}
		else{
			processPageFrame(newNode);
		}
	}

	private void makeNewPageFrame(Node newNode) {
		first = last = newNode;
		first.next = null;
		first.prev = null;
		currentSize++;
	}
	
	private void processPageFrame(Node newNode) {
		int pageNum = newNode.pageNum;
			process(pageNum, newNode);
		
	}

	private void insertPageFrameOn2(Node newNode) {
		if(first.pageNum == newNode.pageNum) { // 首尾节点的值交换即可
//			last = first;
//			first = first.next;
			int tmp = first.pageNum;
			first.pageNum = last.pageNum;
			last.pageNum = tmp;
		}
		
		if(last.pageNum != newNode.pageNum) { // 有两种情况，空间如果够就直接插入链表尾部成为last节点，空间不够就把该值赋给last节点即可
			insertOneFrame(newNode);
		}
	}

	// 链表中至少已经有3个数据了
	private void process(int pageNum, Node newNode) {
		Node node = first;
		boolean isNotFind = true;
		int index = 1;
		if(first.pageNum == pageNum) { //头节点就命中了
			insertPageFrameOn22(newNode);
		}else {
			
			while(index <= currentSize) {
				if(node.pageNum == pageNum) { //某个节点命中了，可能是中间节点或last节点
					if(index != currentSize) { // 如果是中间节点就处理，last节点不处理
						node.prev.next = node.next;
						node.next.prev = node.prev;
						last.next = node;
						first.prev = node;
						node.prev = last;
						node.next = first;
						last = node;
						
					}
					isNotFind = false;
					break;
				}
				node = node.next;
				index++;
			}
			
			// 没有找到相同页面
			if(isNotFind) {
				
				insertOneFrame1(newNode);
			}
		}
		
	}

	private void insertPageFrameOn22(Node newNode) {
		first.pageNum = newNode.pageNum;
		this.last = first;
		this.first = first.next;
	}

	private void insertOneFrame1(Node newNode) {
		if(currentSize < capacity){ // 还有空间可用，直接放到链表尾部成为新的last节点
			last.next = newNode;
			first.prev = newNode;
			newNode.next = first;
			newNode.prev = last;
			last = newNode;
			currentSize++;
		}else{ // 没有空间可用了
			first.pageNum = newNode.pageNum;
			this.last = first;
			this.first = first.next;
		}
	}
	
	private void insertOneFrame(Node newNode) {
		if(currentSize < capacity){ // 还有空间可用，直接放到链表尾部成为新的last节点
			last.next = newNode;
			first.prev = newNode;
			newNode.next = first;
			newNode.prev = last;
			last = newNode;
			currentSize++;
		}else{ // 没有空间可用了
			last.pageNum = newNode.pageNum;
			
		}
	}

	private void insertPageFrameOn1(Node newNode) {
		
		if(1 == capacity ) {// 当前已有一个元素且初始化容量为1
			first = newNode;
		}
		
		if(first.pageNum != newNode.pageNum){
			last = newNode;
			first.next = last;
			first.prev = last;
			last.next = first;
			last.prev = first;
			currentSize++;
		}
	}
	
	@Override
	public String toString() {
		if(currentSize == 0){
			return null;
		}
		Node node = last;
		String result = "";
		while( node.pageNum != first.pageNum){
			result = result + String.valueOf(node.pageNum) + ",";
			node = node.prev;
		}
		result = result + String.valueOf(first.pageNum);
		return result;
	}
	
	public int getSize() {
		return currentSize;
	}
}
