package com.coding.basic;

/**
 * 用双向链表实现LRU算法
 * @author liuxin
 *
 */
public class LRUPageFrame {
	
	private static class Node {
		
		Node prev;
		Node next;
		//Modified by Yang:set initialize value is -1
		int pageNum = -1;

		Node() {
		}
	}

	private int capacity;
	private Node first;// 链表头
	private Node last;// 链表尾

	
	public LRUPageFrame(int capacity) {
		
		this.capacity = capacity;
		//added by Yang
		this.first = new Node();
		--capacity;
		this.first.prev = null;
		Node secondNode = new Node();
		--capacity;
		this.first.next = secondNode;
		secondNode.prev = this.first;
		if(capacity==0){
			secondNode.next=null;
			this.last = secondNode;
			return;
		}
		Node temp = secondNode;
		while(--capacity != 0){
			Node node = new Node();
			temp.next = node;
			node.prev = temp;
			temp = node;
		}
		this.last = new Node();
		temp.next = this.last;
		this.last.prev = temp;//invocation if no this.last = new Node();
		this.last.next = null;
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
			Node curNode = this.first;
			while(curNode!=null && curNode.pageNum != pageNum){//查找一下栈里有没有当前页数
				curNode = curNode.next;
			}
			if(curNode!=null && curNode.pageNum == pageNum){//找到，设置栈顶
				setTop(curNode);
				return;
			}
			else if(this.first.pageNum!=-1){//没找到，并且栈满则POP栈底，push栈顶
				pop();
				pushTop(pageNum);
				return;
			}
			else push(pageNum);//栈没满，直接压进
	}
	
	//Added by yang
	public void push(int pageNum){
		Node pushNode = this.last;
		if(this.last.pageNum == -1){
			this.last.pageNum = pageNum;
			return;
		}
		while(pushNode!= null && pushNode.pageNum!=-1){
			pushNode = pushNode.prev;
		}
		pushNode.pageNum = pageNum;
	}
	
	//Added by yang
	public void pop(){
		Node popNode = this.last;
		while(popNode!=this.first){
			popNode.pageNum = popNode.prev.pageNum;
			popNode = popNode.prev;
		}
	}
	
	//Added by yang
	public void setTop(Node freqCode){
		int temp = freqCode.pageNum; //将要移动到top的节点值
		Node node = freqCode;
		while(node!=this.first && node.prev.pageNum!=-1){//加上了node.pageNum!=-1的条件
			node.pageNum = node.prev.pageNum;
			node = node.prev;
		}
		node.pageNum = temp;
	}
	
	//Added By yang
	public void pushTop(int pageNum){
		this.first.pageNum = pageNum;
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
