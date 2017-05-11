package com.coding.basic.linklist;

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

		Node(int pageNum) {
			this.pageNum = pageNum;
		}
		
	}

	private int capacity;
	private int size = 0;
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
		removeElement(pageNum);
		addFirst(pageNum);
		if(this.size > this.capacity){
			removeLast();
		}
	}
	
	//删除链表的尾节点
	private void removeLast(){
		if(this.size == 0){
			return;
		}
		if(this.size == 1){
			this.first = null;
			this.last = null;
			this.size--;
		}
		Node curr = this.last;
		this.last = curr.prev;
		this.last.next = null;
		curr.prev = null;
		this.size--;
	}
	
	//根据参数删除双向链表中的某个节点
	private void removeElement(int pageNum){
		if(this.size == 0){
			return;
		}
		Node curr = this.first;
		while(curr.pageNum != pageNum){
			if(curr.next == null){
				break;
			}
			curr = curr.next;
		}
		//此时curr指向被删除节点or链表最后一个节点
		if(curr.pageNum == pageNum){
			if(this.first == this.last){//size为1，且该节点需要被删除
				this.first = null;
				this.last = null;
				this.size--;
				return;
			}
			if(curr == this.first){//删除头节点
				this.first = curr.next;
				this.first.prev = null;
				curr.next = null;
				this.size--;
				return;
			}
			if(curr == this.last){//删除尾节点
				this.last = curr.prev;
				this.last.next = null;
				curr.prev = null;
				this.size--;
				return;
			}
			
			//删除中间节点
			//此时size至少为3
			curr.next.prev = curr.prev;
			curr.prev.next = curr.next;
			curr.prev = null;
			curr.next = null;
			this.size--;
		}
	}
	
	//向双向链表的头部添加节点
	private void addFirst(int pageNum){
		Node curr = new Node(pageNum);
		if(this.size == 0){
			this.first = curr;
			this.last = curr;
			this.size++;
		}else{
			curr.next = this.first;
			this.first.prev = curr;
			this.first = curr;
			this.size++;
		}
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
