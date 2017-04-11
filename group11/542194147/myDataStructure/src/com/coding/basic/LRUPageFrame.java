package com.coding.basic;

/**
 * 用双向链表实现LRU算法
 * @author 小摩托
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
	private int currentSize;
	
	private Node first;// 链表头
	private Node last;// 链表尾

	
	public LRUPageFrame(int capacity) {
		this.currentSize = 0;
		this.capacity = capacity;
		
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		//检查数据是否存在队列中
		Node node=findNode(pageNum);
		if(node!=null){//如果存在,则提到队列头（LRU越活跃的数据越放在前面）
			moveExistingNodeToHead(node);		
		}else{
			Node newNode=new Node();
			newNode.pageNum=pageNum;
			if(currentSize>=capacity){//缓存已满删除最后的数据
				removeLastNode();
			}
			addNewNodeTOHead(newNode);//把新数据加到缓存头部
		}
	}
	
	private void addNewNodeTOHead(Node node) {
		if(first==null&&last==null){
			first=node;
			last=node;
			node.next=null;
			node.prev=null;
		}else{
			first.prev=node;
			node.prev=null;
			node.next=first;
			first=node;
		}
		currentSize++;
	}

	private void removeLastNode() {
		Node node=last.prev;
		node.next=null;
		last.prev=null;
		last=node;
		currentSize--;
	}

	private void moveExistingNodeToHead(Node node) {
		if(node==first){
			return;
		}else if(node==last){
			Node prevNode=node.prev;
			last=prevNode;
			prevNode.next=null;
		}else{
			node.prev.next=node.next;
			node.next.prev=node.prev;
		}
		node.prev=null;
		node.next=first;
		first.prev=node;
		first=node;
	}

	
	private Node findNode(int data) {
		Node node=first;
		while(node!=null){
			if(node.pageNum==data){
				return node;
			}
			node=node.next;
		}
		return null;
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
