package com.coding.basic;

import java.util.Stack;

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
		// removeNode = null;
		size--;
		return removeNode;
	}

	public void remove(Object obj) {
		if (head == null) {
			throw new NullPointerException();
		}
		// 如果要删除的结点是第一个，则把下一个结点赋值给第一个结点
		if (head.data.equals(obj)) {
			head = head.next;
			size--;
		} else {
			Node pre = head; // 上一节点
			Node cur = head.next; // 当前结点
			while (cur != null) {
				if (cur.data.equals(obj)) {
					pre.next = cur.next;
					size--;
				}
				pre = pre.next;
				cur = cur.next;
			}
		}
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

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		Node node = head;
		while (node != null) {
			sb.append(node.data);
			if (node.next != null) {
				sb.append(",");
			}
			node = node.next;
		}
		sb.append("]");
		return sb.toString();
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
		int cursor = 0;
		Node curNode;

		@Override
		public boolean hasNext() {
			return cursor != size;
		}

		@Override
		public Object next() {
			if (curNode == null) {
				curNode = head;
			} else {
				curNode = curNode.next;
			}
			cursor++;
			return curNode;
		}

	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		if (head == null || head.next == null) {
			return;
		}
		java.util.Stack<Node> stack = new Stack<>();
		Node curNode = head;
		while (curNode != null) {
			stack.push(curNode);
			Node nextNode = curNode.next;
			curNode.next = null; // 断开指向下一个元素的指针
			curNode = nextNode;
		}

		head = stack.pop();
		curNode = head;
		while (!stack.isEmpty()) {
			Node nextNode = stack.pop();
			curNode.next = nextNode;
			curNode = nextNode;
		}
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf() {
		int num = size / 2;
		for (int i = 0; i < num; i++) {
			removeFirst();
		}
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		if (i < 0 || i >= size) {
			throw new IndexOutOfBoundsException();
		}

		int len = size - i >= length ? length : size - i;

		int k = 0;
		while (k < len) {
			remove(i);
			k++;
		}
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 = 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		int[] arr = new int[list.size()];

		for (int i = 0; i < list.size(); i++) {
			arr[i] = Integer.parseInt(get(Integer.parseInt(list.get(i).toString())).toString());
		}
		return arr;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list) {
		for (int i = 0; i < list.size(); i++) {
			this.remove(list.get(i).toString());
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		if (head == null || head.next == null) {
			throw new RuntimeException("LinkedList is empty!");
		}

		Node pre = head;
		Node cur = head;
		while (cur.next != null) {
			cur = cur.next;
			Object data = pre.data;
			while (cur.data == data) {
				if (cur.next == null) {
					pre.next = null;
					break;
				}
				pre.next = cur.next;
				size--;
				cur = cur.next;
				if (cur == null) {
					break;
				}
			}
			pre = pre.next;
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {

		if (head == null) {
			return;
		}

		Node node = head;
		int start = -1;
		int end = -1;
		int i = 0;
		while (node != null) {
			if ((start == -1) && (int) node.data <= min) {
				start = i;
			}
			if ((int) node.data >= max) {
				end = i;
				break;
			}
			node = node.next;
			i++;
		}

		if (start == -1) {
			start = 0;
		}
		if (end == -1) {
			end = size;
		}
		this.remove(start, end - start);
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同） 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public LinkedList intersection(LinkedList list) {
		if (list == null) {
			return null;
		}

		LinkedList result = new LinkedList();

		int i1 = 0;
		int i2 = 0;

		while (i1 < this.size && i2 < list.size()) {

			int value1 = Integer.valueOf(this.get(i1).toString());
			int value2 = Integer.valueOf(list.get(i2).toString());

			if (value1 == value2) {
				result.add(value1);
				i1++;
				i2++;

			} else if (value1 < value2) {
				i1++;

			} else {
				i2++;

			}
		}
		return result;
	}
}
