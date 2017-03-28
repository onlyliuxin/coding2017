package main.week01.data_structure;

import java.util.NoSuchElementException;

public class LinkedList implements List {

	private Node head;
	private int size = 0;

	public void add(Object o) {
		if (isEmpty()) {
			addFirst(o);
		} else {
			addLast(o);
		}
	}

	public boolean isEmpty() {
		return (size == 0) ? true : false;
	}

	public void add(int index, Object o) {
		rangeCheck(index);
		if (index == 0) {
			addFirst(o);
		} else if (index == size) {
			addLast(o);
		} else {
			Node pre = getNode(index - 1);
			Node node = new Node(o);
			node.next = pre.next;
			pre.next = node;
			size++;
		}
	}

	private void rangeCheck(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
	}

	public Object get(int index) {
		rangeCheck(index);
		Node dest = head;
		for (int i = 0; i < index; i++) {
			dest = dest.next;
		}
		return dest.data;
	}

	public Node getNode(int index) {
		rangeCheck(index);
		Node dest = head;
		for (int i = 0; i < index; i++) {
			dest = dest.next;
		}
		return dest;
	}

	public Object remove(int index) {
		rangeCheck(index);
		if (index == 0) {
			return removeFirst();
		} else if (index == size) {
			return removeLast();
		}
		Node pre = getNode(index - 1);
		Node dest = pre.next;
		pre.next = dest.next;
		size--;
		return dest.data;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node node = new Node(o);
		node.next = head;
		head = node;
		size++;
	}

	public void addLast(Object o) {
		Node last = getNode(size - 1);
		Node node = new Node(o);
		last.next = node;
		size++;
	}

	public Object removeFirst() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		Node newhead = head;
		Node dest = head;
		head = head.next;
		size--;
		return dest.data;
	}

	public Object removeLast() {
		Node newLastNode = getNode(size - 2);
		Node oldLastNode = newLastNode.next;
		newLastNode.next = null;
		size--;
		return oldLastNode;
	}

	private static class Node {
		Object data;
		Node next;

		Node(Object data) {
			this.data = data;
			next = null;
		}
	}

	public class LinkedListIterator implements Iterator {

		private LinkedList list;

		private int position = 0;

		private LinkedListIterator() {
		}

		private LinkedListIterator(LinkedList list) {
			this.list = list;
		}

		@Override
		public boolean hasNext() {
			return position + 1 <= list.size;
		}

		@Override
		public Object next() {
			return list.get(position++);
		}

	}

	public LinkedListIterator iterator() {
		return new LinkedListIterator(this);
	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		if(size <= 1){
			return;
		}
		Node A = head,B = head.next;
		head.next = null;
		Node temp;
		while(null != B.next){
			temp = B.next;
			B.next = A;
			B=temp;
		}
		head = B;
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf() {
		if(size <= 1){
			return;
		}
		size = size%2 == 0?size/2:size/2+1;
		head = getNode(size-1);
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		rangeCheck(i);
		rangeCheck(i+length-1);
		if(i==0){
			head = getNode(length);
			size -= length;
		}
		Node pre = getNode(i-1);
		pre.next = getNode(i+length);
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
}
