package com.coding.basic.linklist;

import java.util.HashMap;

/**
 * 用双向链表实现LRU算法
 * @author liuxin
 *
 */
public class LRUPageFrame {
	
	public static void main(String[] args){
		LRUPageFrame frame = new LRUPageFrame(3);
		frame.access(7);
		frame.access(0);
		frame.access(1);
		System.out.println("array: " + frame.toString());
		frame.access(2);
		System.out.println("array: " + frame.toString());
		
		frame.access(0);
		System.out.println("array: " + frame.toString());
		frame.access(0);
		System.out.println("array: " + frame.toString());
		frame.access(3);
		System.out.println("array: " + frame.toString());
		//frame.printList();
		frame.access(0);
		//frame.printList();
		System.out.println("array: " + frame.toString());
		System.out.println();
		frame.access(4);
		//frame.printList();
		System.out.println(frame);
	}
	
	private static class Node {
		
		Node prev;
		Node next;
		int pageNum;

		Node() {
		}
	}

	private int capacity;
	
	private int count;
	private Node first;// 链表头
	private Node last;// 链表尾

	private HashMap<Integer, Node> keyHash;
	
	public LRUPageFrame(int capacity) {
		
		this.capacity = capacity;
		this.count = 0;
		keyHash = new HashMap<Integer, Node>();
		first = new Node();
		first.pageNum = -99;
		last = new Node();
		last.pageNum = -99;
		first.next = last;
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		Node targetPageNode = keyHash.get(new Integer(pageNum));
		
		if(targetPageNode == null){
			targetPageNode = new Node();
			targetPageNode.pageNum = pageNum;
			addNewPage(targetPageNode);
		}
		else{
			moveToTop(targetPageNode);
		}
	
	}
	/*
	 * null - first - f1 - f2 -f3
	 */
	private void moveToTop(Node targetNode){
		if(targetNode != first.next){
			targetNode.prev.next = targetNode.next;
			if(targetNode.next != null){
				targetNode.next.prev = targetNode.prev;
			}
			
			targetNode.next = first.next;
			if(first.next != null){
				first.next.prev = targetNode;
			}
			
			first.next = targetNode;
			targetNode.prev = first;
		}
	}
	
	private void addNewPage(Node targetPageNode){
		//first.next ?= null
		//
		targetPageNode.next = first.next;
		if(first.next != null){
			first.next.prev = targetPageNode;
		}
		
		first.next = targetPageNode;
		targetPageNode.prev = first;
		
		keyHash.put(targetPageNode.pageNum, targetPageNode);
		count++;
		if(count > capacity){
			popOutLast();
		}	
	}
	/*
	 * t3 - t2 - t1 - last - null
	 */
	private void popOutLast(){
		Node tailNode = last.prev; //t1
		if(tailNode != null){
			Node preTailNode = tailNode.prev; //t2
			if(preTailNode != null){
				keyHash.remove(preTailNode.pageNum);
				preTailNode.next = last; //t2 -> last
				last.prev = preTailNode; 
				count --;
			}
		}
	}
	public void printList(){
		int tmpcount = 0;
		Node node = first.next;
		while(node != last && tmpcount++<20){
			System.out.println("current: "+node.pageNum+ " parent: " + node.prev.pageNum);
			node = node.next;
		}
	}

	public String toString(){
		StringBuilder buffer = new StringBuilder();
		Node node = first.next;
		while(node != null){
			if(node == last) break;
			buffer.append(node.pageNum);			
			
			node = node.next;
			if(node != null){
				buffer.append(",");
			}
			
		}
		
		String returnStr = null;
		if(buffer.charAt(buffer.length() -1 ) == ','){
			returnStr = buffer.substring(0, buffer.length() -1);
		}else{
			returnStr = buffer.toString();
		}
		return returnStr;
	}
	
}