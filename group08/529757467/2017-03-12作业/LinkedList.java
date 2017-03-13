package com.coding.basic;

import java.util.NoSuchElementException;

/LinkedList添加新的方法/
public class LinkedList implements List {

	private int size = 0;
	// 头结点
	private Node head;
	// 尾结点
	private Node tail;

	private void rangeCheck(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void add(Object o) {
		Node node = new Node(o, null);
		if (head == null) {
			head = tail = node;
		}
		Node oldTail = tail;
		tail = node;
		oldTail.next = tail;
		size++;
	}

	@Override
	public void add(int index, Object o) {
		rangeCheck(index);
		if (index == size) {
			this.add(o);
		} else {
			// 保存index处节点
			Node x = head;
			// 保存index-1处的节点
			Node y = null;
			for (int i = 0; i < index; i++) {
				y = x;
				x = x.next;
			}
			Node node = new Node(o, x);
			y.next = node;
			size++;
		}
	}

	@Override
	public Object get(int index) {
		rangeCheck(index);
		Node x = head;
		for (int i = 0; i < index; i++) {
			x = x.next;
		}
		return x.data;
	}

	@Override
	public Object remove(int index) {
		rangeCheck(index);
		Object removeData;
		if (index == 0) {
			removeData = removeFirst();
		} else if (index == size - 1) {
			removeData = removeLast();
		} else {
			Node x = head;
			Node y = head;
			for (int i = 0; i < index; i++) {
				y = x;
				x = x.next;
			}
			y.next = x.next;
			size--;
			removeData = x.data;
		}
		return removeData;
	}

	@Override
	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node oldHead = head;
		head = new Node(o, oldHead);
		size++;
	}

	public void addLast(Object o) {
		Node oldTail = tail;
		tail = new Node(o, null);
		oldTail.next = tail;
		size++;
	}

	public Object removeFirst() {
		Node next = head.next;
		Object data = head.data;
		head.data = null;
		head.next = null;
		head = next;
		size--;
		return data;
	}

	public Object removeLast() {
		Node oldTail = tail;
		Node temp = head;
		for (int i = 0; i < size - 2; i++) {
			temp = temp.next;
		}
		tail = temp;
		size--;
		return oldTail.data;
	}

	public void clear() {
		for (Node x = head; x != null;) {
			Node next = x.next;
			x.data = null;
			x.next = null;
			x = next;
		}
		head = tail = null;
		size = 0;
	}

	public Object getTail() {
		Node l = tail;
		// 如果链表为空
		if (l == null) {
			throw new NoSuchElementException();
		}
		return l.data;
	}

	public Object getHead() {
		Node l = head;
		// 如果链表为空
		if (l == null) {
			throw new NoSuchElementException();
		}
		return l.data;
	}

	public Iterator iterator() {
		return new LinkedListIterator();
	}

	private static class Node {
		Object data;
		Node next;

		Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}

	}

	private class LinkedListIterator implements Iterator {
		Node x = head;

		@Override
		public boolean hasNext() {
			return x != null;
		}

		@Override
		public Object next() {
			Object data = x.data;
			x = x.next;
			return data;
		}

	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		Object[] temp = new Object[size];
		for (int i = 0; i < size; i++) {
			temp[i] = this.get(i);
		}
		this.clear();
		for (int i = temp.length - 1; i >= 0; i--) {
			this.add(temp[i]);
		}
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 *
	 */
	public void removeFirstHalf() {
		int s = size;
		for (int i = 0; i < s / 2; i++) {
			this.removeFirst();
		}
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 *
	 * @param i
	 * @param length
	 * @throws Exception
	 */
	public void remove(int i, int length) throws Exception {
		if (i + length > size) {
			throw new Exception();
		}
		for (int j = i; j < i + length; j++) {
			this.remove(i);
		}
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 *
	 * @param list
	 * @throws Exception
	 */
	public int[] getElements(LinkedList list) throws Exception {
		if ((int) list.getTail() > this.size()) {
			throw new Exception();
		}
		int[] temp = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			temp[i] = (int) this.get((int) list.get(i));
		}
		return temp;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 *
	 * @param list
	 */

	public void subtract(LinkedList list) {
		Node x = head;
		Node y = list.head;
		while (x != null && y != null) {
			if ((int) x.data < (int) y.data) {
				x = x.next;
				continue;
			}
			if ((int) x.data == (int) y.data) {
				Node next = x.next;
				continue;
			}
			if ((int) x.data > (int) y.data) {
				y = y.next;
				continue;
			}
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		for (Node x = head; x != null;) {
			Node next = x.next;
			if (next == null) {
				return;
			}
			if (x.data == next.data) {
				x.next = next.next;
				size--;
			} else {
				x = x.next;
			}
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 *
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {
		if ((int) this.getTail() < min) {
			this.clear();
		}

		if ((int) this.getHead() > max) {
			return;
		}

		for (Node x = head; x != null; x = x.next) {
			if ((int) x.data <= min) {
				continue;
			}

		}
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 *
	 * @param list
	 */
	public LinkedList intersection(LinkedList list) {
		LinkedList linkedList = new LinkedList();
		Node x = head;
		Node y = list.head;
		while (x != null && y != null) {
			if ((int) x.data < (int) y.data) {
				x = x.next;
				continue;
			}
			if ((int) x.data == (int) y.data) {
				linkedList.add(x.data);
				x = x.next;
				y = y.next;
				continue;
			}
			if ((int) x.data > (int) y.data) {
				y = y.next;
				continue;
			}
		}
		return linkedList;
	}
}
