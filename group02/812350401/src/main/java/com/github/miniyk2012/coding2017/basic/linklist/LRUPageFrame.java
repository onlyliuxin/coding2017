package com.github.miniyk2012.coding2017.basic.linklist;

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
	private int size;
	
	private Node first;// 链表头
	private Node last;// 链表尾

	
	public LRUPageFrame(int capacity) {
		
		this.capacity = capacity;
		
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param pageNum: page值
	 * @return
	 */
	public void access(int pageNum) {
        Node hitNode = get(pageNum);
        if (null == hitNode) {
            hitNode = new Node(pageNum);
            addTop(hitNode);
            if (size > capacity) {
                delBottom();
            }
        } else {
            switchTop(hitNode);
        }
	}

    /**
     * 获取值为pageNum的Node，如果没有返回null
     * @param pageNum
     * @return
     */
	private Node get(int pageNum) {
	    Node currentNode = first;
		while (currentNode != null) {
		    if (currentNode.pageNum == pageNum) return currentNode;
		    currentNode = currentNode.next;
        }
        return null;
	}

    /**
     * 往顶部放一个Node
     * @param node
     */
	private void addTop(Node node) {
        size++;
        if (first == null) {
            first = last = node;
        } else {
            node.next = first;
            first.prev = node;
            first = node;
        }
	}

    /**
     * 把node和顶部做交换
     * @param node
     */
	private void switchTop(Node node) {
        if (node == first) return;
        Node preNode = node.prev;
        Node nextNode = node.next;
        preNode.next = nextNode;
        if (nextNode != null) {
            nextNode.prev = preNode;
        } else {
            last = preNode;
        }
        node.next = node.prev = null;
        addTop(node);
        size--;
    }

    /**
     * 把底部的踢掉
     */
    private void delBottom() {
        size--;
        if (last == first) first = null;
        Node temp = last;
        last = last.prev;
        temp.prev = null;
        last.next = null;
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

    /**
     * 测试双向链表逆序输出
     * @return
     */
    public String lastToString(){
        StringBuilder buffer = new StringBuilder();
        Node node = last;
        while(node != null){
            buffer.append(node.pageNum);

            node = node.prev;
            if(node != null){
                buffer.append(",");
            }
        }
        return buffer.toString();
    }
	
}
