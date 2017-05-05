package com.coderising.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class  LinkedList<E> implements List<E> {

	transient Node<E> head;
	transient int size;
	
	public LinkedList(){
		head  = new Node<E>(null,head);
		size = 0;
	}
	public void addFirst(E e){
		head.next = new Node<E>(e,head.next);
		size++;
	}
	
	
	public void addAfter(Node<E> n, E e){
		if(n == head){
			addFirst(e);
		}else{
			Node<E> curr = new Node<E>(e,n.next);
			n.next = curr;
			size++;
		}
	}
	
	
	private void checkRange(int index){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Index:"+index+",Size:"+size);
		}
	}
	
	private Node<E> getNode(int index){
		checkRange(index);
		Node<E> curr = head;
		for(; index >= 0 ; index--){
			curr = curr.next;
		}
		return curr;
	}

	public void add(E e) {
		add(size,e);
	}

	public void add(int index, E e) {
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("Index:"+index+",Size:"+size);
		}
		if(index == 0){
			addFirst(e);
		}else{
			addAfter(getNode(index-1), e);
		}
	}

	public E get(int index) {
		Node<E> n = getNode(index);
		return n.e;
	}

	
	public E remove(int index) {
		checkRange(index);
		Node<E> preN = null,
				currN = null;
		if(index == 0){
			preN = head;
		}else{
			preN = getNode(index-1);
		}
		currN = preN.next;
		preN.next = currN.next;
		E e = currN.e;
		currN.e = null;
		currN.next = null;
		size--;
		return e;
		
	}

	public int size() {
		return size;
	}
	
	
	private static class Node<E>{
		E e ;
		Node<E> next;
		
		public Node(E e,Node<E> next){
			this.e = e;
			this.next = next;
		}
	}
	
	private Iterator<E> iterator(){
		return new LinkedListIterator<E>();
	}

	private class LinkedListIterator<E> implements Iterator<E>{

		int index;
		public LinkedListIterator(){
			index = 0;
		}
		
		@Override
		public boolean hasNext() {
			if(index < size){
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			Node<E> curr = (Node<E>) getNode(index++);
			return curr.e;
		}
		
	}
	
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){	
		if(size > 1){
			int index = 0;
			Node<E> a = null,
					preA = null,
					b = null,
					preB = null;
			for(;index < size/2; index++){
				if(index == 0){
					preA = head;
					a = head.next;
					b = getNode(size-1);
					preB = getNode(size-2);
					
					head.next = b;
					preB.next = a;
					b.next = a.next;
					a.next = head;
				}else{
					preA = getNode(index-1);
					a = preA.next;
					preB = getNode(size-2-index);
					b = preB.next;
					
					preA.next = b;
					preB.next = a;
					Node<E> tmp = a.next;
					a.next = b.next;
					b.next = tmp;
				}
				
			}
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		remove(0, size/2);
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		while(length > 0){
			remove(i);
			length--;
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
	public  int[] getElements(LinkedList list){
		int[] a = new int[list.size];
		for(int i = 0; i < list.size; i++){
			Node<E> curr = getNode((int)list.get(i));
			a[i] = (int) curr.e;
		}
		return a;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		int i = 0,
			j = 0;
		for(; i < list.size; i++){
			E a = (E) list.get(i);
			while(j < size){
				E b = get(j);
				if(a.equals(b)){
					remove(j);
					break;
				}
				j++;
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Map<E,Integer> map = new HashMap<E,Integer>(size);
		for(int i = 0; i < size; ){
			if(map.get(getNode(i).e) == null){
				map.put(getNode(i).e, i);
				i++;
			}else{
				remove(i);
			}
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		if(min >= max){
			return ;
		}
		for(int i = 0; i < size; ){
			int a = (int)get(i);
			if(min < a && max > a){
				remove(i);
				continue;
			}
			i++;
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList<E> intersection( LinkedList<E> list){
		LinkedList<E> c = new LinkedList<E>();
		if(list.size == 0){
			return c;
		}
		Map<E,Boolean> map = new HashMap<E,Boolean>();
		for(int i = 0; i < list.size; i++){
			E a = list.get(i);
			if(null == map.get(a)){
				map.put(a, true);
			}
		}
		for(int i = 0; i < size; i++){
			E a = get(i);
			if(null == map.get(a)){
				map.put(a, true);
			}else if(map.get(a)){
				c.add(get(i));
			}
		}
		
		return c;
	}
}
