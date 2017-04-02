package com.coding.basic;

public class LinkedList implements List {

	private Node head;

	private int size = 0;

	public void add(Object o) {
		if (size == 0) {
			head = new Node();
			head.data = o;
			size++;
			return;
		}
		Node last = head;
		for (int i = 0; i < size - 1; i++) {
			last = last.next;
		}
		Node added = new Node();
		last.next = added;
		added.data = o;
		size++;
	}

	public void add(int index, Object o) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		Node pre = getNode(index - 1);
		Node next = getNode(index);
		Node addedNode = new Node();
		addedNode.data = o;
		pre.next = addedNode;
		addedNode.next = next;
		size++;
	}

	private Node getNode(int index) {
		Node node = head;

		for (int i = 0; i < index; i++) {
			node = node.next;
		}

		return node;
	}

	public Object get(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0 && head == null) {
			return null;
		}
		return getNode(index).data;

	}

	public Object remove(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		Node pre = getNode(index - 1);
		Node next = getNode(index + 1);
		pre.next = next;
		size--;
		return getNode(index);
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		if (head == null) {
			head = new Node();
			head.data = o;
			size++;
			return;
		}
		Node addNode = new Node();
		addNode.data = o;
		addNode.next = head;
		head = addNode;
		size++;
	}

	public void addLast(Object o) {
		Node preLast = getNode(size - 1);
		Node addNode = new Node();
		addNode.data = o;
		preLast.next = addNode;
		size++;
	}

	public Object removeFirst() {
		Node preHead = head;
		head = head.next;
		size--;
		return preHead.data;
	}

	public Object removeLast() {
		Node preLast = getNode(size - 1);
		Node last = getNode(size - 2);
		last.next = null;
		size--;
		return preLast.data;
	}

	public LinkedListIterator iterate() {
		return new LinkedListIterator();
	}

	public class LinkedListIterator implements Iterator {

		private int position;

		@Override
		public boolean hasNext() {
			return position < size();
		}

		@Override
		public Object next() {
			return get(position++);
		}

	}

	private static class Node {

		Object data;
		Node next;

	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		Node newHead = getNode(size - 1);
		for (int i = size - 1; i > 0; i--) {
			getNode(i).next = getNode(i - 1);
		}
		head = newHead;
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 * 
	 */
	public void removeFirstHalf() {
		int half = size / 2;
		Node newHead = getNode(half);
		for (int i = 0; i < half - 1; i++) {
			Node node = getNode(i);
			node = null;
		}
		size -= half;
		head = getNode(half);
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		int p = i;
		if (i == 0) {
			Node newHead = getNode(length);
			do {
				Node node = getNode(p);
				node = null;
				p++;
			} while (p < length);
			head = newHead;
			size -= length;
			return;
		}

		getNode(i - 1).next = getNode(i + length);
		size -= length;
		for (; p < i + length; p++) {
			Node node = getNode(p);
			node = null;
		}
	}

	/**
	 * 假定当前链表和listB均包含已升序排列的整数 从当前链表中取出那些listB所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			int index = (int) list.get(i);
			result[i] = (int) get(index);
		}
		return result;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在listB中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list) {

		for (int i = 0; i < list.size(); i++) {
			int dataB = (int) list.get(i);
			for (int j = size - 1; j > 0; j--) {
				int data = (int) get(j);
				if (data == dataB) {
					remove(j);
				}
			}
		}

	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		int i = size - 2;
		int right = (int) get(i + 1);
		int left = (int) get(i);

		do {
			if (right == left) {
				remove(i);
			} else {
				right = (int) get(i);
			}
			i--;
			left = (int) get(i);
		} while (i > 0);
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {
		int indexMin=0;	
		int i = 0;
		while (i<size) {
			int dataMin=(int) get(i);
			if (dataMin>min) {
				indexMin=i;
				break;
			}else {
				i++;
			}
		}
		if (i==size) {
			return;
		}
		
		int indexMax=0;
		int j=size-1;
		while (j>=0) {
			int dataMax=(int) get(j);
			if (dataMax<max) {
				indexMax=j;
				break;
			}else {
				j--;
			}
		}
		if (j<0) {
			return;
		}
		
		remove(indexMin, indexMax-indexMin + 1);
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public LinkedList intersection(LinkedList list) {
		LinkedList result = new LinkedList();
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < size(); j++) {
				int data1=(int) list.get(i);
				int data2=(int) get(j);
				if (data1==data2) {
					result.add(data1);
				}
			}
		}
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size(); i++) {
			sb.append(get(i) + " ");
		}
		return sb.toString();
	}

}
