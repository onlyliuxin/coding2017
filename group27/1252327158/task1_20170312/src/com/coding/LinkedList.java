package com.coding;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {

	private int size;

	private Node<T> head;
	
	private int modCount;

	public LinkedList(){
		size = 0;
		head = null;
	}

	@Override
	public void add(T o){
		addFirst(o);
	}

	@Override
	public void add(int index , T o){
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> item = new Node<T>(o, null);
		if (index == 0) {
			item.next = head;
			head = item;
		} else {
			Node<T> temp = head;
			for (int i = 1; i < index; i++) {
				temp = temp.next;
			}
			item.next = temp.next;
			temp.next = item;
		}
		size++;
		modCount++;
	}

	@Override
	public T get(int index){
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> result = head;
		for (int i = 0; i < index; i++) {
			result = result.next;
		}
		return result.data;
	}

	@Override
	public T remove(int index){
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> result = head;
		if (index == 0) {
			head = head.next;
		} else {
			Node<T> temp = head;
			for (int i = 1; i < index; i++) {
				temp = temp.next;				
			}
			result = temp.next;
			temp.next = temp.next.next;
		}
		size--;
		modCount++;
		return result.data;
	}

	@Override
	public int size(){
		return size;
	}

	public void addFirst(T o){
        Node<T> item = new Node<T>(o, null);
        if (head == null) {
        	head = item;
        } else {
        	item.next = head;
        	head = item;
        }
        size++;
        modCount++;
	}
	
	public void addLast(T o){
		Node<T> item = new Node<T>(o, null);
		if(head == null) {
			head = item;
		} else {
			Node<T> tag = head;
			while (tag.next != null) {
				tag = tag.next;
			}
			tag.next = item;
		}
		size++;
		modCount++;
	}
	
	public T removeFirst(){
		if (size == 0) {
			throw new IndexOutOfBoundsException(); 
		}
		Node<T> result = head;
		head = head.next;
		size--;
		modCount++;
		return result.data;
	}
	
	public T removeLast(){
		if (size == 0) {
			throw new IndexOutOfBoundsException(); 
		}
		Node<T> result = head;
		if (size == 1) {
			head = null;
		} else {
			Node<T> temp = head;
			while (result.next != null) {
				temp = result;
				result = result.next;
			}
			temp.next = null;
		}
		size--;
		modCount++;
		return result.data;
	}

	public Iterator<T> iterator(){
		return new Iter();
	}
	
	private class Iter implements Iterator<T> {
		int cursor;        
        int expectedModCount = modCount;
        
        @Override
        public boolean hasNext() {
        	return cursor != size;
        }
        
        @Override
    	public T next() {
        	checkForComodification();
        	if (cursor >= size) {
        		throw new NoSuchElementException();
        	}
        	T item = get(cursor);
        	cursor++;
        	return item;
        }
        
        final void checkForComodification() 
        {
             if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
	}

	private static  class Node<T>{
		T data;
		Node<T> next;
		public Node(T data, Node<T> node) {
			this.data = data;
			this.next = node;
		}
	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){

	}

	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){

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
	public static int[] getElements(LinkedList list){
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
	public  LinkedList intersection( LinkedList list){
		return null;
	}
}
