package com.coderising.week03.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.coding.basic.week01.List;


public class LinkedListEx implements List {
	

	
	private Node head;
	
	private int size;
	
	public void add(Object o){
		if (head == null) {
			head = new Node(o);
			size++;
		} else{
			addLast(o);
		}
	}
	public void add(int index , Object o){
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} 
		if (index == 0) {
			addFirst(o);
		} else {
			//定义标记节点sentinelNode，标记节点的下一个节点即为要新加的元素
			Node sentinelNode = head;
			for (int i = 0; i < index - 1; i++) {
				sentinelNode = sentinelNode.next;
			}
			Node node = new Node(o);
			node.next = sentinelNode.next;
			sentinelNode.next = node;
			size++;
		}
	}
	public Object get(int index){
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		} else {
			Node indexNode = head;
			for (int i = 0; i < index; i++) {
				indexNode = indexNode.next;
			}
			return indexNode.data;
			}
	}
	public Object remove(int index){
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} else {
			/**
			 * sentinelNode是所删除节点的上一个节点；
			 * indexNode是需要被删除的节点
			 */
			Node sentinelNode = head;
			Node indexNode = head;
			for (int i = 0; i < index - 1; i++) {
				sentinelNode = sentinelNode.next;
			}
			for (int i = 0; i < index; i++) {
				indexNode = indexNode.next;
			}
			Node nextIndexNode = indexNode.next;
			sentinelNode.next = nextIndexNode;
			indexNode.next = null;
			size--;
			return indexNode.data;
		}
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node(o);
		node.next = head;
		head = node;
		size++;
	}
	public void addLast(Object o){
		//定义尾节点并通过while循环找到当前链表的尾节点
		Node tailNode = head;
		while (tailNode.next != null) {
			tailNode = tailNode.next;
		}
		Node node = new Node(o);
		tailNode.next = node;
		size++;
	}
	public Object removeFirst(){
		if (head == null) {
			throw new NoSuchElementException();
		}
		Node newNode = head;
		head = head.next;
		size--;
		return newNode.data;
	}
	public Object removeLast(){
		if (head == null) {
			throw new NoSuchElementException();
		}
		Node newNode = head;
		while (newNode.next.next != null) {
			newNode = newNode.next;
		}
		Node lastNode = newNode.next;
		newNode.next = null;
		size--;
		return lastNode.data;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next; // 下一个节点
		
		private Node(Object data) {
			this.data = data;
			next = null;
		}
	}

	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		//节点逆置后的下一个节点;原头节点变尾节点所以初始为null
        Node reverseNode = null;
        while (head != null) {
            Node temp = head; //临时节点temp存放旧头节点 
            head = head.next; //旧头节点的下一个节点变为新头节点
            temp.next = reverseNode; //将reverseNode赋给旧头节点的下一个节点
            reverseNode = temp; //将temp(旧头节点)赋给reverseNode
        }
        head = reverseNode;
		
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		
		int startIndex = size/2;
		for(int i = 0; i < startIndex; i++) {
			head = head.next;
			size--;
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if (i < 0) {
			throw new IllegalArgumentException();
		}
		//要考虑到超出size的情况
		if (i + length >= size) {
			length = size - i;
		}
		if (i == 0) {
			for (int j = 0; j < length; j++) {
				head = head.next;
			}
			size = size - length;
		} else {
			Node startNode = head;  //startNode的下一个节点指向第i个元素
			for (int j = 0; j < i - 1; j++) {
				startNode = startNode.next;
			}
			Node endNode = startNode;
			for (int j = 0; j < length; j++) {
				endNode = endNode.next;
			}
			startNode.next = endNode.next;
			size = size - length;
		}

	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public static int[] getElements(LinkedListEx list){
		return null;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedListEx list){
		
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedListEx intersection( LinkedListEx list){
		return null;
	}
}
