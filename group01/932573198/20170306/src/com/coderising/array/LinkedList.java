package com.coderising.array;

import java.util.NoSuchElementException;

public class LinkedList {

	private int size = 0;

	private Node head;

	public Node getHead() {
		return head;
	}

	public LinkedList() {
		this.head = new Node();
	}

	@Override
	public String toString() {
		return "[" + head + "]";
	}

	private void outOfBoundsForAdd(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException("数组下标越界");
	}

	private void outOfBoundsForOther(int index) {
		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException("数组下标越界");
	}

	public void add(Object o) {
		Node node = head;
		while (node.next != null) {
			node = node.next;
		}
		node.next = new Node(o);
		size++;
	}

	public void add(int index, Object o) {
		outOfBoundsForAdd(index);
		if (size == index)
			add(o);
		else {
			Node prevNode = head;
			for (int i = 0; i < index; i++) {
				prevNode = prevNode.next;
			}
			Node nextNode = prevNode.next;
			Node node = new Node(o);
			prevNode.next = node;
			node.next = nextNode;
			size++;
		}
	}

	public Object get(int index) {
		outOfBoundsForOther(index);
		Node node = head;
		for (int i = 0; i <= index; i++) {
			node = node.next;
		}
		return node.data;
	}

	public Object remove(int index) {
		outOfBoundsForOther(index);
		Node prevNode = head;
		for (int i = 0; i < index; i++) {
			prevNode = prevNode.next;
		}
		Node node = prevNode.next;
		prevNode.next = node.next;
		size--;
		return node.data;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node newNode = new Node(o);
		Node node = head.next;
		head.next = newNode;
		newNode.next = node;
		size++;
	}

	public void addLast(Object o) {
		Node node = head;
		while (node.next != null) {
			node = node.next;
		}
		node.next = new Node(o);
		size++;
	}

	private void noSuchEle() {
		if (head.next == null)
			throw new NoSuchElementException("没有这个元素");
	}

	public Object removeFirst() {
		noSuchEle();
		Node node = head.next;
		head.next = node.next;
		size--;
		return node.data;
	}

	public Object removeLast() {
		noSuchEle();
		Node node = head;
		for (int i = 0; i < size - 1; i++) {
			node = node.next;
		}
		Node reNode = node.next;
		node.next = null;
		size--;
		return reNode.data;
	}

	public static class Node {
		Object data;
		Node next;

		@Override
		public String toString() {
			return data + ", " + next;
		}

		public Node() {
		}

		public Node(Object data) {
			this.data = data;
		}

	}

	// ------------------------------------------------------------------------------------------

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		Node node = head.next;
		Object[] arr = new Object[size];
		int i = size - 1;
		while (i >= 0) {
			arr[i--] = node.data;
			node = node.next;
		}
		node = head.next;
		for (int j = 0; j < size; j++) {
			node.data = arr[j];
			node = node.next;
		}
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 * 
	 */
	public void removeFirstHalf() {
		int n = size / 2;
		Node node = head.next;
		for (int i = 0; i < n; i++) {
			node = node.next;
		}
		head.next = node;
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		Node node = head;
		for (int j = 0; j < i; j++) {
			node = node.next;
		}
		Node newNode = node;
		for (int j = 0; j < length; j++) {
			newNode = newNode.next;
		}
		node.next = newNode.next;
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		int length = list.size();
		int[] arr = new int[length];
		int index = -1;
		for (int i = 0; i < length; i++) {
			index = (int) list.get(i);
			arr[i] = (int) get(index);
		}
		return arr;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */
	public void subtract(LinkedList list) {
		Node preNode = head;
		Node node = head.next;
		int data = 0;
		for (int i = 0; i < list.size(); i++) {
			data = (int) list.get(i);
			while ((int) node.data < data) {
				preNode = node;
				node = node.next;
			}
			if ((int) node.data == data) {
				preNode.next = node.next;
				node = node.next;
			}
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		Node preNode = head.next;
		Node node = preNode.next;
		while (true) {
			if (preNode.data == node.data)
				preNode.next = node.next;
			else
				preNode = node;
			if (node.next == null)
				break;
			node = node.next;
		}

	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {
		Node preNode = head;
		Node node = preNode.next;
		for (int i = 0; i < size; i++) {
			if ((int) node.data <= min) {
				preNode = node;
				node = node.next;
			} else if ((int) node.data < max) {
				node = node.next;
			} else {
				break;
			}
		}
		preNode.next = node;
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public LinkedList intersection(LinkedList list) {
		int size1 = size;
		int size2 = list.size();
		int i = 0, j = 0;
		LinkedList newList = new LinkedList();
		while (i < size1 && j < size2) {
			if ((int) get(i) < (int) list.get(j))
				newList.add(get(i++));
			else
				newList.add(list.get(j++));
		}
		if (i == size1) {
			for (; j < size2; j++) {
				newList.add(list.get(j));
			}
		} else {
			for (; i < size1; i++) {
				newList.add(get(i));
			}
		}
		return newList;
	}
}
