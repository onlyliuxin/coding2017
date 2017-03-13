package com.github.AminLiu.dataStructure;

import java.util.NoSuchElementException;

public class LinkedList implements List {

	private Node head;
	private Node last;
	private int size = 0;

	public void add(Object o) {
		addLast(o);
	}

	public void add(int index, Object o) {
		ckeckPosition(index);
		if (this.size == index) {
			addLast(o);
		} else if (index == 0) {
			addFirst(o);
		} else if (index != 0 && this.size > index) {
			Node arg0 = getIndexNode(this.head, index);
			Node arg1 = arg0.next;
			Node newNode = new Node(o, null);
			arg0.next = newNode;
			newNode.next = arg1;
			this.size++;
		}
	}

	/**
	 * 找到index前一节点元素
	 * 
	 * @param o
	 * @param index
	 * @return
	 */
	private Node getIndexNode(Node o, int index) {
		if (index == 0) {
			return this.head;
		}
		Node arg0 = o;
		for (int i = 0; i < index - 1; i++) {
			arg0 = arg0.next;
		}
		return arg0;
	}

	public Object get(int index) {
		if (this.size <= index || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
		}
		Node arg0 = getIndexNode(this.head, index);
		return arg0.next.data;
	}

	public Object remove(int index) {
		ckeckPosition(index);
		if (this.size - 1 == index) {
			Object arg5 = removeLast();
			return arg5;
		}
		if (index == 0) {
			Object arg4 = removeFirst();
			return arg4;
		}
		Node arg0 = getIndexNode(this.head, index);
		Node arg1 = arg0.next;
		Node arg2 = arg1.next;
		arg1.next = arg2;
		this.size--;
		return arg0.data;
	}

	public int size() {
		return this.size;
	}

	public void addFirst(Object o) {
		linkedFirst(o);
	}

	private void linkedFirst(Object o) {
		Node f = this.head;
		Node newNode = new Node(o, f);
		this.head = newNode;
		if (f == null) {
			this.last = f;
		}
		this.size++;
	}

	public void addLast(Object o) {
		Node newNode = new Node(o, null);
		if (this.size == 0) {
			this.head = newNode;
			this.last = newNode;
		} else if (this.size == 1) {
			this.head.next = newNode;
			this.last = newNode;
		} else {
			Node arg0 = getIndexNode(this.head, this.size);
			arg0.next = newNode;
			this.last = newNode;
		}
		this.size++;
	}

	public Object removeFirst() {
		Node first = this.head;
		this.head = first.next;
		this.size--;
		return first.data;
	}

	public Object removeLast() {
		Node lastNode = this.last;
		Node arg0 = getIndexNode(this.head, this.size - 1);
		arg0.next = null;
		this.last = arg0;
		this.size--;
		return lastNode.data;

	}

	private static class Node {
		Object data;
		Node next;

		public Node(Object date, Node next) {
			this.data = date;
			this.next = next;
		}
	}

	public Iterator iterator() {
		return new ite();
	}

	private class ite implements Iterator {
		int arg0;
		Node node = head;

		public boolean hasNext() {
			return arg0 != size;
		}

		public Object next() {
			int i = arg0;
			if (i >= size)
				throw new NoSuchElementException();
			if (i == 1) {
				arg0++;
				return head.data;
			}
			node = node.next;
			arg0++;
			return node.data;
		}

	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {

	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
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
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
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
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public LinkedList intersection(LinkedList list) {
		return null;
	}

	private void ckeckPosition(int index) {
		if (index < 0 || this.size < index) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
		}
	}

	public String toString() {
		if (this.size == 0) {
			return null;
		}
		if (this.size == 1) {
			return "[" + this.head.data.toString() + "]";
		}
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("[" + this.head.data.toString());
		Node arg0 = this.head;
		for (int i = 0; i < this.size - 1; i++) {
			arg0 = arg0.next;
			sBuffer.append("," + arg0.data.toString());
		}
		sBuffer.append("]");
		return sBuffer.toString();
	}
}
