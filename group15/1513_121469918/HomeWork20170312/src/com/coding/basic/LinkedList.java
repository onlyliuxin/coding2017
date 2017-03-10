package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	private Node head;
	private int size;

	public void add(Object o) {
		// 判断头是否有数据
		if (head == null) {
			head = new Node(o, null);
		} else {
			Node newNode = head;
			while (newNode.next != null) {
				newNode = newNode.next;
			}
			newNode.next = new Node(o, null);
		}
		size++;
	}

	public void add(int index, Object o) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index:" + index + "size:" + size);
		}
		Node node = head;
		if (index != 0) {
			// 不是第一个索引值就找到索引值的前面一个节点
			for (int i = 1; i < index; i++) {
				node = node.next;
			}
			Node newNode = new Node(o, node.next);
			node.next = newNode;
			size++;
		} else {
			// 第一个索引值就将头节点指向它
			Node newNode = new Node(o, head);
			head = newNode;
			size++;
		}
	}

	public Object get(int index) {
		indexCheck(index);
		Node node = head;
		for (int i = 1; i <= index; i++) {
			node = node.next;
		}
		return node.data;
	}

	public Object remove(int index) {
		indexCheck(index);
		
		Node node = head;
		Node removeNode;
		if (index == 0) {
			//删除第一个节点就把头节点指向原本的第二个节点
			removeNode = head;
			head = head.next;
		} else {
			//找到索引值的前一个节点
			for (int i = 1; i < index; i++) {
				node = node.next;
			}
			removeNode = node.next;
			//前一个节点指针，指向被删除节点所指向的节点
			node.next = removeNode.next;
		}
		size--;
		return removeNode.data;
	}
	
	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node newNode = new Node(o, head.next);
		head.next = newNode;
		size++;
	}

	public void addLast(Object o) {
		add(o);
	}
	public Object removeFirst() {
		//没有元素就抛异常
		if (size <= 0) {
			throw new IndexOutOfBoundsException("size:" + size);
		}
		Object val = head.data;
		head = head.next;
		size--;
		return val;
	}

	public Object removeLast() {
		if (size <= 0) {
			throw new IndexOutOfBoundsException("size:" + size);
		}
		Node node = head;
		while (node.next != null) {
			node = node.next;
		}
		Object val = node.data;
		node = null;
		size--;
		return val;
	}
	public Iterator iterator() {
		return new MyIterator(this);
	}

	private class MyIterator implements Iterator{
		private int poi = -1;
		private LinkedList list ;
		private MyIterator(LinkedList list) {
			this.list= list;
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (poi + 1) < list.size;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			poi++;
			if (poi >= list.size) {
				poi--;
				throw new IndexOutOfBoundsException();
			}

			return list.get(poi);
		}

		@Override
		public Object remove() {
			// TODO Auto-generated method stub
			if (poi < 0) {
				throw new NoSuchElementException();
			}
			Object val = list.removeLast();
			poi--;
			return val;
		}
		
	}
	
	private void indexCheck(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index:" + index + "size:" + size);
		}
	}

	
	
	private static class Node {
		Object data;
		Node next;

		Node(Object data, Node next) {
			this.data = data;
			this.next = next;
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
