package com.coding.basic.linklist;

import java.util.Hashtable;

/**
 * 用双向链表实现LRU算法
 * @author liuxin
 *
 */
public class LRUPageFrame {
	
	private static class Node {
		
		Node prev;
		Node next;
		Object value;
		Object key;

		Node() {
		}
	}

	private int capacity;
	private int currentSize;
	private Hashtable nodes;
	private Node first;// 链表头
	private Node last;// 链表尾

	
	public LRUPageFrame(int i) {
		currentSize = 0;
		capacity = i;
		nodes = new Hashtable(capacity);
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return 
	 * @return
	 */
	public Object get(Object key) {
		Node node = (Node) nodes.get(key);	
		if (node != null) {
			moveToHead(node);
			return node.value;
		}
		else {
            return null;
        }
	}
	 /**
     * 添加缓存
     *
     * @param key
     * @param value
     */
    public void put(Object key, Object value) {
        Node node = (Node) nodes.get(key);

        if (node == null) {
            // 缓存容器是否已经超过大小.
            if (currentSize >= capacity) {
                if (last != null)// 将最少使用的删除
                    nodes.remove(last.key);
                removeLast();
            } else {
                currentSize++;
            }

            node = new Node();
        }
        node.value = value;
        node.key = key;
        // 将最新使用的节点放到链表头，表示最新使用的.
        moveToHead(node);
        nodes.put(key, node);
    }

    /**
     * 将缓存删除
     *
     * @param key
     * @return
     */
    public Object remove(Object key) {
        Node node = (Node) nodes.get(key);
        if (node != null) {
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            if (last == node)
                last = node.prev;
            if (first == node)
                first = node.next;
        }
        return node;
    }

    public void clear() {
        first = null;
         last = null;
    }

    /**
     * 删除链表尾部节点 表示 删除最少使用的缓存对象
     */
    private void removeLast() {
        // 链表尾不为空,则将链表尾指向null. 删除连表尾（删除最少使用的缓存对象）
        if (last != null) {
            if (last.prev != null)
                last.prev.next = null;
            else
                first = null;
            last = last.prev;
        }
    }

    /**
     * 移动到链表头，表示这个节点是最新使用过的
     *
     * @param node
     */
    private void moveToHead(Node node) {
        if (node == first)
            return;
        if (node.prev != null)
            node.prev.next = node.next;
        if (node.next != null)
            node.next.prev = node.prev;
        if (last == node)
            last = node.prev;
        if (first != null) {
            node.next = first;
            first.prev = node;
        }
        first = node;
        node.prev = null;
        if (last == null)
            last = first;
    }

	public String toString(){
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while(node != null){
			buffer.append(node.key);			
			
			node = node.next;
			if(node != null){
				buffer.append(",");
			}
		}
		return buffer.toString();
	}
	
}