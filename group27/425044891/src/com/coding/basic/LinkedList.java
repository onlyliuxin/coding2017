package com.coding.basic;

public class LinkedList implements List {

	private Node head = null;

	private int size = 0;

	public LinkedList() {
		this.head = new Node();
		this.head.next = null;
		this.head.data = null;
		this.size = 0;
	}

	public void add(Object o) {
		if (null == o) {
			return;
		}
		Node next = new Node();
		next.data = o;
		next.next = null;

		/**
		 * 寻找尾部节点
		 */
		Node p = head;
		while (p.next != null) {
			p = p.next;
		}
		p.next = next;
		size++;
	}

	public void add(int index, Object o) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (null == o) {
			return;
		}
		Node next = new Node();
		next.data = o;
		next.next = null;

		Node p = head.next;
		Node pre = head;
		int pos = 0;
		while (p != null && pos < index) {
			pre = p;
			p = p.next;
			pos++;
		}
		next.next = p;
		pre.next = next;
		size++;
	}

	public Object get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node p = head.next;
		int pos = 0;
		while (p != null && pos < index) {
			p = p.next;
			pos++;
		}
		return p == null ? null : p.data;
	}

	public Object remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node p = head.next;
		Node pre = p;
		int pos = 0;
		while (p != null && pos < index) {
			pre = p;
			p = p.next;
			pos++;
		}
		Object o = p.data;
		pre.next = p.next;
		size--;
		return o;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		if (null == o) {
			return;
		}
		Node next = new Node();
		next.next = null;
		next.data = o;

		next.next = head.next;
		head.next = next;
		size++;
	}

	public void addLast(Object o) {
		if (null == o) {
			return;
		}
		Node p = head;
		while (p.next != null) {
			p = p.next;
		}

		Node next = new Node();
		next.data = o;
		next.next = null;

		p.next = next;
		size++;
	}

	public Object removeFirst() {
		if (size <= 0) {
			throw new IndexOutOfBoundsException();
		}
		Node p = head.next;
		Object o = p.data;

		head.next = p.next;
		size--;
		return o;
	}

	public Object removeLast() {
		if (size <= 0) {
			throw new IndexOutOfBoundsException();
		}
		Node p = head.next;
		Node pre = head;
		while (p.next != null) {
			pre = p;
			p = p.next;
		}
		Object o = p.data;
		pre.next = p.next;
		size--;
		return o;
	}

	public Iterator iterator() {
		return new Iterator() {
			Node current = head;

			@Override
			public Object next() {
				Object o = current.next.data;
				current = current.next;
				return o;
			}

			@Override
			public boolean hasNext() {
				return current.next != null;
			}
		};
	}

	private static class Node {
		Object data;
		Node next;
	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		Node p = head.next.next;
		Node pre = head.next;
		pre.next = null;
		while (p != null) {
			Node pp = p.next;
			p.next = pre;
			pre = p;
			p = pp;
		}
		head.next = pre;
		// --- --- ---
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 * 
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
	 * 假定当前链表和listB均包含已升序排列的整数 从当前链表中取出那些listB所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		return null;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在listB中出现的元素
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

	public static void main(String[] args) {

		LinkedList list = new LinkedList();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.reverse();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			System.out.println(o);
		}

	}
}