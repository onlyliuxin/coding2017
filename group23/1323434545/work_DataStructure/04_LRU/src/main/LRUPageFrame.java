package main;

public class LRUPageFrame {

	private static class Node {

		Node prev;
		Node next;
		int pageNum;

		Node() {
		}
	}

	private int capacity;
	private int currentSize;
	private Node first;// 链表头
	private Node last;// 链表尾

	public LRUPageFrame(int capacity) {
		this.capacity = capacity;
		this.currentSize = 0;
	}

	/**
	 * 获取缓存中的对象
	 * 
	 * @param pageNum
	 */
	public void access(int pageNum) {
		if (first == null && last == null) {
			first = new Node();
			first.pageNum = pageNum;
			last = first;
			currentSize++;
			return;
		}
		Node tagNode = find(pageNum);
		if (tagNode != null) {
			moveExistingNodeToHead(tagNode);
			return;
		}
		Node node = new Node();
		node.pageNum = pageNum;
		addNewNodeToHead(node);

	}

	private void addNewNodeToHead(Node node) {
		if (currentSize == capacity) {
			removeLast();
		}
		Node temp = first;
		temp.prev = node;
		node.next = temp;
		first = node;
		currentSize++;
	}

	private Node find(int data) {
		Node node = first;
		while (node != null) {
			if (node.pageNum == data) {
				return node;
			}
			node = node.next;
		}
		return null;
	}

	/**
	 * 删除链表尾部节点，表示删除最小使用的缓存对象
	 */
	private void removeLast() {
		Node temp = last.prev;
		temp.next = null;
		last = temp;
		currentSize--;
	}

	/**
	 * 移动到表头，表示这个节点是最新使用过的
	 */
	private void moveExistingNodeToHead(Node node) {
		if (node.prev == null) {
			return;
		}
		Node prev = node.prev;
		Node next = node.next;
		if (next == null) {
			last = prev;
		}
		prev.next = next;
		if (next != null) {
			next.prev = prev;
		}
		Node temp = first;
		temp.prev = node;
		node.prev = null;
		node.next = temp;
		first = node;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node node = first;
		while (node != null) {
			sb.append(node.pageNum);
			node = node.next;
			if (node != null) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

}
