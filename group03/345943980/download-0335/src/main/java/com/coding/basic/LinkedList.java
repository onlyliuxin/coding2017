package com.coding.basic;

public class LinkedList implements List {

	private Node head;
	private int size = 0;

	public void add(Object o) {
		Node newNode = new Node(o);
		if (null == head) {
			head = newNode;
			size++;
			return;
		}
		Node currNode = head;
		while (null != currNode.next) {
			currNode = currNode.next;
		}
		currNode.next = newNode;
		size++;
	}

	public void add(int index, Object o) {
		rangeCheck(index);
		if (index == size) {
			add(o);
			return;
		}
		if (index == 0) {
			Node newNode = new Node(o);
			newNode.next = head;
			head = newNode;
			size++;
			return;
		}
		Node newNode = new Node(o);
		Node currNode = head;
		for (int i = 0; i < index - 1; i++) {
			currNode = currNode.next;
		}
		newNode.next = currNode.next;
		currNode.next = newNode;
		size++;
	}

	public Object get(int index) {
		rangeCheck(index);
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}

	private void rangeCheck(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}

	public Object remove(int index) {
		rangeCheck(index);
		if (index == 0) {
			return this.removeFirst();
		}
		if (index == size - 1) {
			return this.removeLast();
		}
		Node currNode = head;
		for (int i = 0; i < index - 1; i++) {
			currNode = currNode.next;
		}
		Node removeNode = currNode.next;
		currNode.next = removeNode.next;
		//removeNode = null;
		size--;
		return removeNode;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		if (null == head) {
			head = new Node(o);
			size++;
			return;
		}
		Node newNode = new Node(o);
		newNode.next = head;
		head = newNode;
		size++;
	}

	public void addLast(Object o) {
		this.add(o);
	}

	public Object removeFirst() {
		if (null == head) {
			return null;
		}
		Node currNode = head;
		head = currNode.next;
		size--;
		return head;
	}

	public Object removeLast() {
		if (null == head) {
			return null;
		}
		if (null == head.next) {
			Node currNode = head;
			head = null;
			size--;
			return currNode;
		}
		Node currNode = head;
		while (null != currNode.next) {
			currNode = currNode.next;
		}
		currNode = null;
		size--;
		return null;
	}

	public Iterator iterator() {
		return new MyIterator();
	}

	private static class Node {
		Object data;
		Node next;

		Node(Object data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return this.data.toString();
		}
	}

	private class MyIterator implements Iterator {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {

	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf() {

	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {

	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 = 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public static int[] getElements(LinkedList list) {
		return null;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list) {

	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {

	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {

	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同） 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public LinkedList intersection(LinkedList list) {
		return null;
	}
}
