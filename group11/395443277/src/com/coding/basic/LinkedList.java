package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {

	private Node head;

	public void add(Object o) {
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = null;

		if (head == null) {
			head = newNode;
		} else {
			Node curr = head;
			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = newNode;
		}
	}

	public void add(int index, Object o) {
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = null;

		if (index == 0) {
			this.addFirst(o);
			return;
		}

		if (head == null) {
			head = newNode;
		} else {
			Node curr = head;
			Node prev = curr;
			while (index > 0 && curr.next != null) {
				prev = curr;
				curr = curr.next;
				index--;
			}

			prev.next = newNode;
			newNode.next = curr;
		}
	}

	public Object get(int index) {
		if (index >= this.size()) {
			return null;
		}

		Node curr = head;
		while (index > 0) {
			curr = curr.next;
			index--;
		}
		return curr.data;
	}

	public Object remove(int index) {
		if (index == 0) {
			return this.removeFirst();
		}

		Node curr = head;
		Node prev = curr;
		while (index > 0 && curr.next != null) {
			prev = curr;
			curr = curr.next;
			index--;
		}

		Object target = curr.data;
		prev.next = curr.next;
		curr.next = null;

		return target;
	}

	public int size() {
		int size = 0;
		Node curr = head;
		while (curr != null) {
			size++;
			curr = curr.next;
		}
		return size;
	}

	public void addFirst(Object o) {
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = null;

		if (head == null) {
			head = newNode;
		} else {
			newNode.next = head.next;
			head.next = newNode;
		}
	}

	public void addLast(Object o) {
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = null;

		Node curr = head;
		if (head == null) {
			head = newNode;
		} else {
			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = newNode;
		}
	}

	public Object removeFirst() {
		if (head == null) {
			return null;
		}

		Object target = head.data;
		head = head.next;
		return target;
	}

	public Object removeLast() {
		if (head == null) {
			return null;
		}
		Node curr = head;
		Node prev = curr;
		while (curr.next != null) {
			prev = curr;
			curr = curr.next;
		}
		Object target = curr.data;
		prev.next = null;
		return target;
	}

	public void print() {
		Node curr = head;

		while (curr != null) {
			System.out.println(curr.data);
			curr = curr.next;
		}
	}

	public Iterator iterator() {
		return new SeqIterator();
	}

	private class SeqIterator implements Iterator {
		Node curr = head;

		@Override
		public boolean hasNext() {
			return curr != null;
		}

		@Override
		public Object next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Object target = curr.data;
			curr = curr.next;
			return target;
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
		if (head != null) {
			Node curr = head;
			Node nextNode = null;
			Node prev = null;

			// move curr node link
			while (curr != null) {
				// move link
				nextNode = curr.next;
				curr.next = prev;

				// move forward
				prev = curr;
				curr = nextNode;
			}

			head = prev;
		}
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 * 
	 */
	public void removeFirstHalf() {
		if (head != null) {
			int listSize = this.size();
			int half = (int) Math.ceil(listSize / 2);

			Node curr = head;
			while (half > 0) {
				curr = curr.next;
				head = curr;
				half--;
			}
		}
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		if (head == null) {
			return;
		}

		if (i < 0) {
			return;
		}

		if (i > this.size() - 1) {
			return;
		}

		Node curr = head;
		Node prev = head;
		// move to index i
		while (i > 0 && curr != null) {
			prev = curr;
			curr = curr.next;
			i--;
		}

		// if curr is out of bound return
		if (curr == null) {
			return;
		}

		// else move length
		while (length > 0 && curr != null) {
			curr = curr.next;
			length--;
		}

		// special case to head
		if (prev == head) {
			head = curr;
		} else {
			prev.next = curr;
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
		if (list.size() == 0) {
			return null;
		}

		if (head == null) {
			return null;
		}

		// TODO: remove list which not existed in the original list
		// special case: 1->3->4->20

		int[] newArr = new int[list.size()];
		int i = 0;
		Iterator it = list.iterator();
		while (it.hasNext()) {
			int id = (int) it.next();

			// if element is not existed
			if (this.get(id) != null) {
				int listElement = (int) this.get(id);
				newArr[i] = listElement;
				i++;
			}
		}

		return newArr;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list) {
		if (list.size() == 0) {
			return;
		}

		if (head == null) {
			return;
		}

		Iterator it = list.iterator();
		while (it.hasNext()) {
			int id = (int) it.next();
			this.remove(id);
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		if (head == null) {
			return;
		}

		Node curr = head;

		// note:
		// duplicate value in the first or last
		// all values the same
		while (curr.next != null) {
			if (curr.data.equals(curr.next.data)) {
				curr.next = curr.next.next;
			} else {
				curr = curr.next;
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
		if (head == null) {
			return;
		}

		if (min > max) {
			return;
		}

		// only works for integer
		Node curr = head;
		Node prev = head;
		// include last node
		while (curr != null) {
			if ((int) curr.data > min && (int) curr.data < max) {
				// special case for head
				if (curr == head) {
					head = curr.next;
				} else {
					prev.next = curr.next;
					curr = curr.next;
				}
			} else {
				prev = curr;
				curr = curr.next;
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
		if (list.size() == 0) {
			return null;
		}

		if (head == null) {
			return null;
		}

		// find size
		int l1Size = this.size();
		int l2Size = list.size();

		// runner
		int i = 0;
		int j = 0;

		LinkedList rtnList = new LinkedList();

		while (i < l1Size && j < l2Size) {
			if (this.get(i).equals(list.get(j))) {
				rtnList.add(this.get(i));
				i++;
				j++;
			} else if ((int) this.get(i) < (int) list.get(j)) {
				i++;
			} else {
				j++;
			}
		}

		return rtnList;
	}
}
