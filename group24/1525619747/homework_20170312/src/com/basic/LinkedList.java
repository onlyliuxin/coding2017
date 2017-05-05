package com.basic;

public class LinkedList implements List
{

	private Node head;

	public void add(Object o) {
		if (head == null) {
			head = new Node(o, null);
		} else {
			Node nodePointer = head;
			while (nodePointer.next != null) {
				nodePointer = nodePointer.next;
			}
			nodePointer.next = new Node(o, null);
		}

	}

	public void add(int index, Object o) {
		int size = size();
		if (index < 0 || index > size) {
			String errorInfo = "Invalid index to add:" + index
					+ " out of range: [0," + size + "]";
			throw new ArrayIndexOutOfBoundsException(errorInfo);
		}
		int step = 0;
		Node nodePointer = head;
		while (step < index) {
			nodePointer = nodePointer.next;
			++step;
		}
		nodePointer.next = new Node(o, nodePointer.next);
	}

	public Object get(int index) {
		int size = size();
		if (index < 0 || index > size) {
			String errorInfo = "Invalid index to get:" + index
					+ " out of range: [0," + size + "]";
			throw new ArrayIndexOutOfBoundsException(errorInfo);
		}

		int step = 0;
		Node nodePointer = head;
		while (step < index) {
			nodePointer = nodePointer.next;
			++step;
		}

		return nodePointer.data;
	}

	public Object remove(int index) {
		int size = size();
		if (index < 0 || index > size) {
			String errorInfo = "Invalid index to remove:" + index
					+ " out of range: [0," + size + "]";
			throw new ArrayIndexOutOfBoundsException(errorInfo);
		}

		int step = 0;
		Node nodePointer = head;
		Node lastPointer = head;

		while (step < index) {
			lastPointer = nodePointer;
			nodePointer = nodePointer.next;
			++step;
		}

		Object o = null;

		if (lastPointer == nodePointer) {
			Node toDelete = head;
			o = toDelete.data;
			head = head.next;
			toDelete = null;
		} else {
			o = nodePointer.data;
			lastPointer.next = nodePointer.next;
			nodePointer = null;
		}

		return o;
	}

	public int size() {
		int size = 0;
		if (head != null) {
			++size;
			Node nodePointer = head;
			while (nodePointer.next != null) {
				++size;
				nodePointer = nodePointer.next;
			}
		}
		return size;
	}

	public void addFirst(Object o) {
		if (head == null) {
			head = new Node(o, null);
			return;
		}
		head = new Node(o, head);
	}

	public void addLast(Object o) {
		if (head == null) {
			head = new Node(o, null);
			return;
		}

		Node nodePointer = head;
		while (nodePointer.next != null) {
			nodePointer = nodePointer.next;
		}
		nodePointer.next = new Node(o, null);
	}

	@SuppressWarnings ("unused")
	public Object removeFirst() {
		if (head == null) {
			return null;
		}

		Node toDelete = head;
		Object o = head.data;
		head = head.next;
		toDelete = null;

		return o;
	}

	public Object removeLast() {
		if (head == null) {
			return null;
		}

		Node nodePointer = head;
		Node lastPointer = head;

		while (nodePointer.next != null) {
			lastPointer = nodePointer;
			nodePointer = nodePointer.next;
		}
		lastPointer.next = null;
		Object o = nodePointer.data;
		nodePointer = null;

		return o;
	}

	public Iterator iterator() {
		return new Iterator() {

			private Node nodePointer = head;

			public boolean hasNext() {
				// TODO Auto-generated method stub
				return (nodePointer != null);
			}

			public Object next() {
				// TODO Auto-generated method stub
				if (hasNext()) {
					Object o = nodePointer.data;
					nodePointer = nodePointer.next;
					return o;
				}
				return null;
			}
		};
	}

	private static class Node
	{
		Object data;
		Node next;

		public Node (Object o, Node n) {
			this.data = o;
			this.next = n;
		}
	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		if (head == null) {
			return;
		}

		Node reverse = null;
		while (size() > 0) {
			reverse = new Node(removeFirst(), reverse);
		}

		head = reverse;
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 */
	@SuppressWarnings ("unused")
	public void removeFirstHalf() {
		int removeLength = size() / 2;
		int step = 0;
		Node toDelete = null;
		while (step < removeLength) {
			toDelete = head;
			head = head.next;
			toDelete = null;
			++step;
		}
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		int size = size();
		if (i >= size) {
			return;
		}
		Node nodePointer = head;
		Node lastPointer = head;
		int step = 0;
		while (step < i) {
			lastPointer = nodePointer;
			nodePointer = nodePointer.next;
			++step;
		}

		step = 0;
		Node toDelete = null;
		if (lastPointer == head) {
			while (step < length) {
				toDelete = head;
				head = head.next;
				toDelete = null;
				++step;
			}
		} else {
			while (step < length) {
				toDelete = nodePointer;
				nodePointer = nodePointer.next;
				toDelete = null;
				++step;
			}
			lastPointer.next = nodePointer;
		}

	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		int[] elements = new int[list.size()];
		Iterator it = list.iterator();
		int index = 0;
		for (; it.hasNext();) {
			elements[index++] = (Integer) get((Integer) (it.next()));
		}
		return elements;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list) {
		if (head == null) {
			return;
		}
		Node nodePointer = head;
		Node lastPointer = head;
		Node toDelte = null;
		while (nodePointer != null) {
			if (list.contain(nodePointer.data)) {
				if (nodePointer == head) {
					toDelte = head;
					head = head.next;
					toDelte = null;
					nodePointer = head;
					lastPointer = head;
				} else {
					toDelte = nodePointer;
					lastPointer.next = nodePointer.next;
					toDelte = null;
				}
			}
			lastPointer = nodePointer;
			nodePointer = nodePointer.next;
		}

	}

	private boolean contain(Object o) {
		Node nodePointer = head;
		while (nodePointer != null) {
			if (nodePointer.data.equals(o)) {
				return true;
			}
			nodePointer = nodePointer.next;
		}
		return false;
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		Node nodePointer = head;
		if (nodePointer.next == null) {
			return;
		}

		Node toDelete = null;
		while (nodePointer.next != null) {
			while (nodePointer.next != null
					&& nodePointer.data.equals(nodePointer.next.data)) { // delete
																			// nodePointer.next
				toDelete = nodePointer.next;
				nodePointer.next = nodePointer.next.next;
				toDelete = null;
			}
			nodePointer = nodePointer.next;
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {
		Node nodePointer = head;
		Node lastPointer = head;
		if (nodePointer == null) {
			return;
		}
		while (nodePointer != null
				&& ((Integer) nodePointer.data) <= (new Integer(min))) {
			lastPointer = nodePointer;
			nodePointer = nodePointer.next;
		}
		Node toDelete = null;
		while (nodePointer != null
				&& ((Integer) nodePointer.data) < (new Integer(max))) {
			if (nodePointer == head) {
				toDelete = head;
				head = head.next;
				toDelete = null;
				nodePointer = head;
				lastPointer = head;
			} else {
				toDelete = nodePointer;
				lastPointer.next = nodePointer.next;
				nodePointer = nodePointer.next;
				toDelete = null;
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
		Iterator it1 = iterator();
		Iterator it2 = list.iterator();
		Object o1 = null;
		Object o2 = null;

		if (size() == 0 || list.size() == 0) {
			return null;
		}

		o1 = it1.next();
		o2 = it2.next();

		while (o1 != null && o2 != null) {
			// System.out.println(o1 + " " + o2);
			if (((Integer) o1) == ((Integer) o2)) {
				linkedList.add(o1);
				o1 = it1.next();
				o2 = it2.next();
			} else {
				if (((Integer) o1) > ((Integer) o2)) {
					o2 = it2.next();
				} else {
					o1 = it1.next();
				}
			}
		}

		return linkedList;
	}
}
