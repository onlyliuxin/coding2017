package com.coding.basic;

import java.util.Arrays;

public class LinkedList implements List {

	private Node head;
	private Node last;
	private int size = 0;

	public void add(Object o) {
		if (head == null) {
			head = new Node(o, null);
			size++;
			return;
		}
		Node n = new Node(o, null);
		if (last == null) {
			last = n;
			head.next = last;
		}
		last.next = n;
		last = n;
		size++;
	}

	public void add(int index, Object o) {
		if (index < 0 || index > size) {
			System.out.println("linkedList.add: index < 0 || index > size");
			return;
		}
		if (index == size) {
			add(o);
			return;
		}
		if (index == 0) {
			addFirst(o);
			return;
		}
		Node pre = head;
		for (int i = 1; i < index; i++) {
			pre = pre.next;
		}
		Node post = pre.next;
		Node n = new Node(o, post);
		pre.next = n;
		size++;
	}

	public Object get(int index) {
		return this.getNode(index).data;
	}

	public Node getNode(int index) {
		if (index == 0) {
			return this.head;
		}
		Node n = head;
		for (int i = 1; i <= index; i++) {
			n = n.next;
		}
		return n;
	}

	public Object remove(int index) {
		if (index < 0 || index >= size) {
			System.out.println("remove :index < 0 || index >= size");
			return null;
		}
		if (index == 0) {
			return removeFirst();
		}
		if (index == size - 1) {
			return removeLast();
		}
		Node pre = head;
		for (int i = 1; i < index; i++) {
			pre = pre.next;
		}
		Node n = pre.next;
		Node post = n.next;
		n.next = null;
		pre.next = post;
		size--;
		return n.data;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node n = new Node(o, head);
		head = n;
		size++;
		return;
	}

	public void addLast(Object o) {
		Node n = new Node(o, null);
		last.next = n;
		last = n;
		size++;
		return;
	}

	public Object removeFirst() {
		Object o = head.data;
		Node n = head.next;
		head.next = null;
		head = n;
		size--;
		return o;
	}

	public Object removeLast() {
		Node preLast = head;
		for (int i = 1; i < size; i++) {
			preLast = preLast.next;
		}
		preLast.next = null;
		Object o = last.data;
		last = preLast;
		size--;
		return o;
	}

	public Iterator iterator() {
		return new Iterator() {
			int cusor = 0;
			Node current = head;

			@Override
			public Object next() {
				if (!hasNext()) {
					System.out.println("next : !hasNext");
					return null;
				}
				Object o = current.data;
				current = current.next;
				cusor++;
				return o;
			}

			@Override
			public boolean hasNext() {
				return cusor < size;
			}
		};
	}

	private static class Node {

		Object data;
		Node next;

		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		if (this.size() < 2) {
			return;
		}
		for (int i = this.size() - 1; i > 0; i--) {
			this.getNode(i).next = this.getNode(i - 1);
		}
		Node temp = this.last;
		this.last = this.head;
		this.head = temp;
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf() {

		int delSize = this.size() >> 1;

		Node temp = this.getNode(delSize);// getNode(index)
											// 方法依赖前面元素的next指针，所以此语句在for循环前面

		for (int i = delSize - 1; i >= 0; i--) {
			this.getNode(i).next = null;
			this.size--;
		}

		this.head = temp;// 由于getNode(index) 方法如果index传入0
							// ，返回head，所以此语句要方法for循环后面
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		if (i < 0 || length < 0 || this.size() < i + length) {
			return;
		}
		if (i == 0 && this.size() == length) {
			this.head = null;
			this.size = 0;
			this.last = null;
			return;
		}
		Node iNode = this.getNode(i - 1);
		Node pre = this.getNode(i + length - 1);
		Node post = this.getNode(i + length);
		pre.next = null;
		iNode.next = post;
		this.size = this.size() - length;
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		Iterator select = list.iterator();
		Iterator original = this.iterator();
		int[] result = new int[this.size()];
		int cosur = 0;
		while (select.hasNext()) {
			int s = (int) select.next();
			String selStr = String.valueOf(s);
			while (original.hasNext()) {
				int o = (int) original.next();
				String oriStr = String.valueOf(o);
				if (oriStr.contains(selStr)) {
					result[0] = o;
					cosur++;
					break;
				}
			}
		}
		return Arrays.copyOf(result, cosur - 1);
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list) {
		Iterator select = list.iterator();
		Iterator original = this.iterator();
		int[] result = new int[this.size()];
		int cosur = 0;
		while (select.hasNext()) {
			int sel = (int) select.next();

			while (original.hasNext()) {
				int ori = (int) original.next();
				cosur++;
				if (ori == sel) {
					remove(cosur);
				}
			}
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		if (this.size() == 0) {
			return;
		}
		for (int i = this.size(); i > 0; i--) {
			if ((int) get(i) == (int) get(i - 1)) {
				this.remove(i);
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
		int minIndex = 0;
		int maxIndex = 0;
		for (int i = this.size(); i > 0; i--) {
			if (max > (int) get(i)) {
				maxIndex = i;
			}
		}
		for (int i = 0; i < maxIndex; i++) {
			if (min < (int) get(i)) {
				minIndex = i;
			}
		}
		remove(minIndex, maxIndex - minIndex);
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public LinkedList intersection(LinkedList list) {
		LinkedList result = new LinkedList();
		Iterator select = list.iterator();
		Iterator original = this.iterator();

		int sel = (int) select.next();
		int ori = (int) original.next();
		while (original.hasNext() && select.hasNext()) {
			if (ori == sel) {
				result.add(ori);
			} else if (ori < sel) {
				ori = (int) original.next();
			} else {
				sel = (int) select.next();
			}
		}
		return result;
	}
}
