package com.LinkedList;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListUtil<T extends Comparable> {
	private Node<T> head;
	private Node<T> current;
	private int size = 0;
		
	public void add(T o){
		if(head == null){
			head = new Node<T>(o);
			current = head;
		}else{
			Node<T> tmp = new Node<T>(o);
			current.next = tmp;
			current = tmp;
		}
		size++;
	}
	
	public int[] toIntArray(){
		if(size == 0) return null;
		int[] intArray = new int[this.size()];
		Node<T> tmp = head;
		for(int i=0; i<size; i++){
			intArray[i] = (int)tmp.data;
			tmp = tmp.next;
		}
		return intArray;
	}
	
	public void add(int index , Object o){
		
	}
	public Node<T> get(int index){
		checkBounds(index);
		if (index == 0) {
			return head;
		} else {
			Node<T> pos = findIndexPosition(index);
			return pos;
		}
	}
	public Object remove(int index){
		return null;
	}
	
	public void remove(Node<T> n){
		Node<T> former = head;
		while(former.next != n) former = former.next;
		former.next = n.next;
		size--;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		
	}
	
	public void addLast(Object o){
		
	}
	public Node<T> removeFirst(){
		if(head == null) return null;
		Node<T> tmp = head;
		head = head.next;
		this.size--;
		return tmp;
	}
	public Object removeLast(){
		return null;
	}
	public Iterator iterator(){
		return null;
	}
	
	public void checkBounds(int index) {
		if (index < 0 || index > size - 1) {
			// System.out.println("From MyLinkedList: Index out of bounds");
			throw new IndexOutOfBoundsException(OutOfBoundsMsg(index));
		}
	}
	
	public String OutOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + size;
	}
	
	public Node<T> findIndexPosition(int index) {
		Node<T> pos = head;
		for (int i = 0; i < index; i++) {
			pos = pos.next;
		}
		return pos;
	}
	
	
	private static class Node<T>{
		T data;
		Node<T> next;
		
		public Node(T o){
			this.data = o;
			this.next = null;
		}
		
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){		
		if(head == null) return;
		Node<T> newHead = head;
		head = head.next;
		newHead.next = null;
		while(head != null){
			Node<T> tmp = head;
			head = head.next;
			tmp.next = newHead;
			newHead = tmp;
		}
		head = newHead;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public void removeFirstHalf(){
		int midLen = this.size()/2;
		for(int i=0; i<midLen; i++){
			removeFirst();
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length){
		if(i < 0 || i >= size) return;
		if(length < 0 ||length >= size) return;
		if(i == 0){
			for(int j=0; j<length; j++){
				removeFirst();
			}
		}else{
			Node<T> startNode = head;
			for(int j=0; j<i-1; j++){
				startNode = startNode.next;
			}
			int cnt = 0;
			Node<T> pointer = startNode;
			while(cnt<length && pointer != null){
				pointer = pointer.next;
				cnt++;
			}
			startNode.next = pointer.next;
			this.size = size - length;
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
		Iterator<Integer> it = list.iterator();
		int[] restArray = new int[list.size()];
		int cnt = 0;
		while(it.hasNext()){
			int index = it.next();
			restArray[cnt++] = (int)get(index).data;
		}
		return restArray;
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
		if(head == null) return;
		Node<T> cur = head.next;
		Node<T> former = head;
		while(cur != null){
			if(former.data.equals(cur.data)){
				remove(cur);
				cur = cur.next;
			}else{
				former = former.next;
				cur = cur.next;
			}
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max){
		if((int)head.data > min){
			head = findMaxLocation(head, max);
		}else{
			Node<T> former = head;
			while((int)former.data < min && former != null){
				former = former.next;
			}
			Node<T> behind = findMaxLocation(former.next, max);
			former.next = behind;
			
		}	
	}
	
	public Node<T> findMaxLocation(Node<T> n, int max){
		Node<T> tmp = n;
		while((int)tmp.data < max && tmp != null){
			tmp = tmp.next;
			size--;
		}
		return tmp;
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection(LinkedList list){
		if(head == null) return list;
		
		return null;
	}
}
