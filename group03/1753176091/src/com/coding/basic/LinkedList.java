package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {

	private Node head;
	private int size;

	public void add(Object o) {

		Node newHead = new Node(o, null);
		Node f = head;
		for (int i = 0; i < size - 1; i++) {
			f = f.next;
		}
		f.next = newHead;
		size++;

	}

	public void add(int index, Object o) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node newHead = new Node(o, null);
		Node f = head;
		for (int i = 0; i < index - 1; i++) {
			f = f.next;
		}
		newHead.next = f.next;
		f.next = newHead;
		size++;
	}

	public Object get(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node f = head;
		for (int i = 0; i < index; i++) {
			f = f.next;
		}
		return f.data;
	}

	public Object remove(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node f = head;
		for (int i = 0; i < index - 1; i++) {
			f = f.next;
		}
		f.next = f.next.next;
		final Node d = f.next;
		final Object element = d.data;
		d.data = null;
		d.next = null;
		size--;
		return element;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node newHead = new Node(o, head);
		head = newHead;
		size++;
	}

	public void addLast(Object o) {
		Node newHead = new Node(o, null);
		Node f = head;
		for (int i = 0; i < size - 1; i++) {
			f = f.next;
		}
		f.next = newHead;
		size++;
	}

	public Object removeFirst() {
		final Node f = head;
		if (f == null)
			throw new NoSuchElementException();
		final Object element = f.data;
		head = f.next;
		f.data = null;
		f.next = null;
		size--;
		return element;
	}

	public Object removeLast() {
		Node f = head;
		for (int i = 0; i < size - 2; i++) {
			f = f.next;
		}
		Object element = f.next;
		f.next = null;
		size--;
		return element;
	}

	public Iterator iterator() {
		return null;
	}

	private static class Node {
		Object data;
		Node next;

		private Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse() {
         Node p = head;
         Node q = head.next;
         Node t = null;
        while(q!= null){
        	t = q.next;
        	q.next = p;
        	p = q;
        	q = t;
        }
        head = p ;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public void removeFirstHalf(){
		int l = size/2;
		for(int i = 0; i < l-1 ; i++){
		  removeFirst();
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		
	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public static int[] getElements(List list){
		return null;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues(){
		
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
	public  LinkedList intersection( LinkedList list){
		return null;
	}
}
