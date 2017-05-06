package com.github.HarryHook.coding2017.linklist;

/**
 * 用双向链表实现LRU算法
 * 
 * @author HarryHook
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
    private int currentSize = 0;

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

	if (currentSize == 0) {
	    Node node = new Node();
	    node.pageNum = pageNum;
	    node.prev = node.next = null;
	    first = last = node;
	    currentSize++;
	} else {

	    if (first.pageNum == pageNum) {
		return;
	    }

	    if (currentSize == 1) {
		addToFirst(pageNum);
	    } else {

		if (last.pageNum == pageNum) {
		    moveLastToFirst();
		    return;
		}
		Node iteratorNode = first;
		while (iteratorNode.next != null && iteratorNode.pageNum != pageNum) {
		    iteratorNode = iteratorNode.next;
		}
		if (iteratorNode == last) {
		    addToFirst(pageNum);
		    // 若缓存已满，移除最后一个节点
		    if (currentSize > capacity) {
			last = last.prev;
			last.next = null;
		    }
		} else {
		    moveToFirst(iteratorNode);
		}
	    }
	}
    }

    // 将节点/缓存页添加到fitrst
    public void addToFirst(int pageNum) {
	Node node = new Node();
	node.pageNum = pageNum;
	node.prev = null;
	node.next = first;
	first.prev = node;
	first = node;
	currentSize++;
    }

    // 将last节点移动到first
    public void moveLastToFirst() {
	last.next = first;
	first.prev = last;
	first = last;
	last = last.prev;
	last.next = null;
	first.prev = null;
    }

    // 将最近使用的已有缓存页移动到first
    public void moveToFirst(Node iteratorNode) {
	iteratorNode.prev.next = iteratorNode.next;
	iteratorNode.next.prev = iteratorNode.prev;
	iteratorNode.prev = null;
	iteratorNode.next = first;
	first.prev = iteratorNode;
	first = iteratorNode;
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
