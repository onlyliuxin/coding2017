package com.github.ipk2015.coding2017.basic.linkedlist;



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
	}

	private int capacity;
	private int size=0;
	
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
		if(this.capacity<=1){
			throw new RuntimeException("MeaningLess");
		}
		int pos=searchPageNum(pageNum);
		if(pos==-1){
			addFirst(pageNum);
			return;
		}
		if(pos==0){
			return;
		}
		Node tempNode=first;
		for(int i=0;i<pos;i++){
			tempNode=tempNode.next;
		}
		tempNode.prev.next=tempNode.next;
		if(null!=tempNode.next){
			tempNode.next.prev=tempNode.prev;
		}else{
			last=tempNode.prev;
		}
		tempNode.prev=null;
		tempNode.next=first;
		first=tempNode;
	}
	private int searchPageNum(int num){
		int pos=-1;
		boolean flag=false;
		Node tempNode=first;
		while(null!=tempNode){
			pos++;
			if(tempNode.pageNum==num){
				flag=true;
				break;
			}
			tempNode=tempNode.next;
		}
		return flag?pos:-1;
	}
	private void addFirst(int num){
		Node addNode=new Node();
		addNode.pageNum=num;
		addNode.next=first;
		if(size<capacity){
			if(null!=first){
				first.prev=addNode;
			}else{
				last=addNode;
			}
			first=addNode;
			size++;
			return;
		}
		first.prev=addNode;
		first=addNode;
		Node tempNode=last;
		last=last.prev;
		last.next=null;
		tempNode.prev=null;
		
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
