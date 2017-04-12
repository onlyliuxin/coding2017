package com.coderising.week03.basic;

import java.util.NoSuchElementException;
import java.util.Stack;

import com.coding.basic.week01.Iterator;
import com.coding.basic.week01.List;

public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	public LinkedList() {
		size = 0;
		head = null;
	}
	
	public void add(Object o){
		Node node = new Node(o);
		if(head == null) {
			head = node;
		} else {
			//p为游标 从头遍历到尾
			Node p = head;
			while(p.next != null) {
				p = p.next;
			}
			p.next = node;
		}
		size++;
	}
	public void add(int index , Object o){
		//判断链表不为空链表
		if(head != null) {
			Node p = head;
			int k = 0;
			//扫描单链表查找第index-1个节点
			while(k < index-1 && p.next != null) {
				k++;
				p = p.next;
			}
			//判断是否找到第index-1个节点
			if(p != null) {
				Node node = new Node(o);
				node.next = p.next;
				p.next = node;
			}
			size++;
		}
	}
	public Object get(int index){
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		} else {
			Node p = head;
			int k = 0;
			while(k < index && p.next != null) {
				k++;
				p = p.next;
			}
			return p.data;
		}
	}
	public Object remove(int index){
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if(head == null) {
			return null;
		}
		if(index == 0) {
			head = head.next;
			size--;
			return head.data;
		} else {
			if(head != null) {
				int k = 0;
				Node p = head;
				while(k < index -1 && p != null) {
					k++;
					p = p.next;
				}
				Node pn = p.next;
				if(pn != null) {
					p.next = pn.next;
					size--;
					return pn.data;
				}
			}
		}
		return null;
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
		Node node = new Node(o);
		if(head == null) {
			head = node;
		} else {
			Node p = head;
			while(p.next != null) {
				p = p.next;
			}
			p.next = node;
		}
		size++;
	}
	public Object removeFirst(){
		if(head == null) {
			throw new NoSuchElementException();
		}
		Node node = head;
		head = node.next;
		size--;
		return node.data;
	}
	public Object removeLast(){
		if(head == null) {
			throw new NoSuchElementException();
		} else {
			Node p = head;
			int k = 0;
			while(k < size-1 && p.next != null ) {
				k++;
				p = p.next;
			}
			Node last = p.next;
			p.next = null;
			size--;
			return last.data;
		}
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		private Node(Object o) {
			this.data = o;
			this.next = null;
		}
		
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		if(null == head || null == head.next) {
			return;
		}
		Stack<Node> s = new Stack<Node>();
		
		Node currentNode = head;
		while(currentNode != null) {
			
			s.push(currentNode);
			
			Node nextNode = currentNode.next;
			currentNode.next = null;  //把链接断开
			currentNode = nextNode;
		}
		
		head = s.pop();
		
		currentNode = head;
		while(!s.isEmpty()) {
			Node nextNode = s.pop();
			currentNode.next = nextNode;
			currentNode = nextNode;
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		int num = size/2;
		for(int i = 0; i < num; i++) {
			removeFirst();
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		
		if(i < 0 || i >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		int len = size-i>=length ? length :size-i;
		
		int k = 0;
		while(k < len) {
			remove(i);
			k++;
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
	public int[] getElements(LinkedList list){
		
		int[] arr = new int[list.size()];
		
		for(int i = 0; i < list.size(); i++) {
			arr[i] = (int) this.get((int) list.get(i));
		}
		return arr;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		for (int i = 0; i < list.size(); i++) {
			this.remove(list.get(i));
		}
	}
	
	/**
	 * 传入数据删除节点
	 * @param obj
	 */
	public void remove(Object obj) {
		if(head==null) {
			throw new RuntimeException("LinkedList is empty!");
		}
		//如果要删除的节点是第一个,则把下一个节点赋值给第一个节点
		if(head.data.equals(obj)){
			head = head.next;
			size--;
		} else {
			Node pre=head; //上一节点
			Node cur=head.next; //当前节点
			while(cur != null) {
				if(cur.data.equals(obj)){
					pre.next=cur.next;
					size--;
				}
				pre=pre.next;
				cur=cur.next;
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		if(head == null) {
			throw new RuntimeException("LinkedList is empty!");
		}
		
		Node pre = head;
		Node cur = head;
		while(cur.next != null) {
			cur = cur.next;
			Object data = pre.data;
			while(cur.data == data) {
				if(cur.next == null) {
					pre.next = null;
					break;
				}
				pre.next = cur.next;
				size--;
				cur = cur.next;
				if(cur == null) {
					break;
				}
			}
			pre = pre.next;
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		if(head == null) {
			return;
		}
		
		Node node = head;
		int start = -1;
		int end = -1;
		int i = 0;
		while(node != null) {
			if( (start == -1) && (int)node.data <= min) {
				start = i;
			}
			if( (int)node.data >= max ) {
				end = i;
				break;
			}
			node = node.next;
			i++;
		}
		
		if(start == -1) {
			start = 0;
		}
		if(end == -1) {
			end = size;
		}
		this.remove(start, end-start);
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		Node node = head;
		while (node != null) {
			buffer.append(node.data);
			if(node.next != null) {
				buffer.append(",");
			}
			node = node.next;
		}
		buffer.append("]");
		return buffer.toString();
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		
		if(list == null) {
			return null;
		}
		
		LinkedList result = new LinkedList();
		
		int i1 = 0;
		int i2 = 0;
		
		while( i1 < this.size && i2 < list.size() ) {
			int value1 = (int)this.get(i1);
			int value2 = (int)list.get(i2);
			
			if(value1 == value2) {
				result.add(value1);
				i1++;
				i2++;
			} else if (value1 < value2) {
				i1++;
			} else {
				i2++;
			}
		}
		return result;
	}
}
