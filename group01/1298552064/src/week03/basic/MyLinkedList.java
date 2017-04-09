package week03.basic;

import java.util.Objects;

import week01.basic.Iterator;
import week01.basic.List;

public class MyLinkedList implements List {

	private Node head;
	private int size;

	/**************************************** 第一次作业 ***************************************/
	public void add(Object o) {
		// 空链表
		if (head == null) {
			head = new Node();
			head.data = o;
			head.next = null;
		} else {
			Node p = head;
			while (p.next != null) {
				p = p.next;
			}

			Node target = new Node();
			target.data = o;
			target.next = null;
			p.next = target;
		}
		size++;
	}

	public void add(int index, Object o) {
		// index 是否合法
		checkPositionIndex(index);
		if (head == null) {
			head = new Node();
			head.data = o;
			head.next = null;
		} else {
			if (index == 0) {
				addFirst(o);
			} else if (index == size) {
				addLast(o);
			} else {
				Node p = new Node();
				Node p1 = head;
				for (int i = 0; i < index - 1; i++) {
					p1 = p1.next;
				}
				p.data = o;
				p.next = p1.next;
				p1.next = p;

				size++;
			}
		}
	}

	public Object get(int index) {
		checkElementIndex(index);
		Node p = head;
		for (int i = 0; i < index; i++) {
			p = p.next;
		}
		return p.data;
	}

	public Object remove(int index) {
		checkElementRemove();
		checkElementIndex(index);

		Object removeObject = null;
		if (index == 0) {
			removeObject = removeFirst();
		} else if (index == (size - 1)) {
			removeObject = removeLast();
		} else {
			Node p = head;
			for (int i = 1; i < index; i++) {
				p = p.next;
			}
			removeObject = p.next.data;
			p.next = p.next.next;
			size--;
		}
		return removeObject;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		if (head == null) {
			head = new Node();
			head.data = o;
			head.next = null;
		} else {
			Node p = new Node();
			p.data = o;
			p.next = head;
			head = p;
		}
		size++;
	}

	public void addLast(Object o) {
		add(o);
	}

	public Object removeFirst() {
		checkElementRemove();
		Object removeObject = head.data;
		head = head.next;
		size--;
		return removeObject;
	}

	public Object removeLast() {
		checkElementRemove();

		Object removeObject = null;

		if (size == 1) {
			removeObject = head.data;
			head = head.next;
		} else {
			Node p = head;
			for (int i = 0; i < size; i++) {
				if (p.next.next == null) {
					removeObject = p.next.data;
					p.next = null;
					break;
				} else {
					p = p.next;
				}
			}
		}
		size--;
		return removeObject;
	}

	private boolean isEmpty() {
		return size == 0;
	}

	private void checkElementRemove() {
		if (isEmpty()) {
			throw new NullPointerException("The list is empty.");
		}
	}

	private void checkElementIndex(int index) {
		if (!isElementIndex(index)) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}

	private boolean isElementIndex(int index) {
		return index >= 0 && index < size;
	}

	private void checkPositionIndex(int index) {
		if (!isPositionIndex(index)) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}

	private boolean isPositionIndex(int index) {
		return index >= 0 && index <= size;
	}

	public Iterator iterator() {
		return new MyLinkedListIterator(this);
	}

	private class MyLinkedListIterator implements Iterator {
		private MyLinkedList list = null;
		private int position = 0;

		private MyLinkedListIterator(MyLinkedList list) {
			this.list = list;
		}

		@Override
		public boolean hasNext() {
			if ((position + 1) > size()) {
				return false;
			}
			return true;
		}

		@Override
		public Object next() {
			return list.get(position++);
		}
	}

	private static class Node {
		Object data;
		Node next;
	}

	/****************************************
	 * 第三次作业 ***************************************
	 * 
	 * 
	 * /** 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		Node p = this.head.next;
		head.next = null;
		while (p != null) {
			addFirst(p.data);
			size--;
			p = p.next;
		}
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 * 
	 */
	public void removeFirstHalf() {
		int removeSize = size / 2;
		for (int i = 0; i < removeSize; i++) {
			head = head.next;
		}
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		for (int index = 0; index < length; index++) {
			remove(i);
		}
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(MyLinkedList list) {
		int[] array = new int[list.size];
		int i = 0;
		for (Node head = list.head; head != null; head = head.next) {
			array[i++] = (int) this.get((int) head.data);
		}
		return array;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(MyLinkedList list) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object data = iterator.next();
			Node p = head;
			int index = 0;
			while (p != null) {
				if (Objects.equals(p.data, data)) {
					this.remove(index);
					break;
				} else {
					p = p.next;
					index++;
				}
			}
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		if (size == 0 || size == 1) {
			return;
		}

		Node p1 = head;
		Node p2 = head.next;

		while (p1 != null && p2 != null) {
			if (Objects.equals(p1.data, p2.data)) {
				p2 = p2.next;
				p1.next = p2;
				size--;
			} else {
				p1 = p2;
				p2 = p2.next;
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
		Node p1 = head;
		Node p2 = head;
		while ((int) p2.data < max) {
			if ((int) p1.next.data <= min) {
				p1 = p1.next;
			}

			if ((int) p2.data < max) {
				p2 = p2.next;
			}
		}
		p1.next = p2;

	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public MyLinkedList intersection(MyLinkedList list) {
		MyLinkedList rsList = new MyLinkedList();
		Node p1 = this.head, p2 = list.head;
		while (p1 != null && p2 != null) {
			if (Objects.equals(p1.data, p2.data)) {
				rsList.add(p1.data);
				p1 = p1.next;
				p2 = p2.next;
			} else if ((int) p1.data > (int) p2.data) {
				p2 = p2.next;
			} else {
				p1 = p1.next;
			}
		}
		return rsList;
	}

	@Override
	public String toString() {
		String elementStr = "";
		Node p = head;
		while (p != null) {
			elementStr += p.data + ",";
			p = p.next;
		}

		return "MyLinkedList: { size=" + size + ", elementData=" + "["
				+ elementStr.substring(0, elementStr.length() - 1) + "]" + " }";
	}
}
