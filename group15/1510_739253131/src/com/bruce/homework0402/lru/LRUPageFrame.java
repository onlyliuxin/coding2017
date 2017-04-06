package com.bruce.homework0402.lru;

/**
 * 用双向链表实现LRU算法
 *
 */
public class LRUPageFrame {

	private int capacity;//页容量
	private Node first;// 链表头
	private Node last;// 链表尾
	private int size = 0;//缓存的页面数量

	public LRUPageFrame(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * 获取缓存中对象
	 *
	 * @param
	 */
	public void access(int pageNum) {
		//第一次存放
		if (size == 0) {
			Node node = new Node(pageNum);
			node.next = null;
			node.prev = null;
			this.first = this.last = node;
			size++;
		} else if (size == 1 && size < capacity) {
            addToFirst(pageNum);
        } else {
            if (first != null && first.pageNum == pageNum) {//目标页已经存放在first节点 ，不做操作
				return;
			}
            if (last != null && last.pageNum == pageNum) {//目标页已经存放在last节点 ，将该节点放到第一个
                moveLastToFirst();
                return;
            }
            //目标页不在第一个和最后一个节点，从第一个节点往后查找
            Node node = first;
            while (node.next  != null) {
                node = node.next;
                if (node.pageNum == pageNum) {
                    break;
                }
            }
            if (node == last) {
                addToFirst(pageNum);
                if (size > capacity) {//添加新节点后，size大于capacity，把最后一个节点去掉，last 指针向前移动一位
                    last = last.prev;
                    last.next = null;
                }
            } else {
                moveToFirst(node);
            }
		}
	}

	private Node exist(int pageNum) {
		Node node = first;
		while(node != null) {
			if (node.pageNum == pageNum) {
				return node;
			}
			node = node.next;
		}
		return null;
	}

	private static class Node {
		Node prev;
		Node next;
		int pageNum;
		Node() {}
		Node(int pageNum) {
			this.pageNum = pageNum;
		}
	}

	private void moveLastToFirst() {
		//将first和last双向链接起来
		last.next = first;
		first.prev = last;
		//移动first和last的“指针”
		first = last;
		last = last.prev;
		//打断first和last的双向链接
		last.next = null;
		first.prev = null;
	}

	private void addToFirst(int pageNum) {
		Node node = new Node(pageNum);
		node.next = first;
		node.prev = null;
		first.prev = node;
		first = node;
		size++;
	}

	private void moveToFirst(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.next = first;
		node.prev = null;
		first.prev = node;
		first = node;
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
