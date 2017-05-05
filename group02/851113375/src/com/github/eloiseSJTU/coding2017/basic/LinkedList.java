package com.github.eloiseSJTU.coding2017.basic;

import java.security.InvalidParameterException;
import java.util.NoSuchElementException;

public class LinkedList implements List {

	private int size = 0;

	private Node head;

	public void add(Object o) {
		addLast(o);
	}

	public void add(int index, Object o) {
		checkBoundsForAdd(index);

		if (index == 0) {
			addFirst(o);
		} else if (index == size) {
			addLast(o);
		} else {
			Node cur = head;
			while (--index > 0) {
				cur = cur.next;
			}
			Node newNode = new Node(o, cur.next);
			cur.next = newNode;
			size++;
		}
	}

	public Object get(int index) {
		checkBounds(index);

		Node cur = head;
		while (index-- > 0) {
			cur = cur.next;
		}
		return cur.data;
	}

	public Object remove(int index) {
		checkBounds(index);

		if (index == 0) {
			return removeFirst();
		} else if (index == size - 1) {
			return removeLast();
		} else {
			Node cur = head;
			int i = 0;
			while (++i < index) {
				cur = cur.next;
			}
			Node node = cur.next;
			Object o = node.data;
			cur.next = node.next;
			node.data = null;
			node.next = null;
			size--;
			return o;
		}
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node newNode = new Node(o, head);
		head = newNode;
		size++;
	}

	public void addLast(Object o) {
		Node newNode = new Node(o, null);
		if (head == null) {
			head = newNode;
		} else {
			Node cur = head;
			while (cur.next != null) {
				cur = cur.next;
			}
			cur.next = newNode;
		}
		size++;
	}

	public Object removeFirst() {
		if (head == null) {
			throw new NoSuchElementException();
		}

		Object o = head.data;
		Node node = head.next;
		head.data = null;
		head.next = null;
		head = node;
		size--;
		return o;
	}

	public Object removeLast() {
		if (head == null) {
			throw new NoSuchElementException();
		}

		if (head.next == null) {
			return removeFirst();
		}

		Node cur = head;
		int index = 0;
		while (++index < size - 1) {
			cur = cur.next;
		}
		Object o = cur.next.data;
		cur.next.data = null;
		cur.next = null;
		size--;
		return o;
	}

	public Iterator iterator() {
		return new Itr();
	}

	private class Itr implements Iterator {

		private Node cur = head;

		@Override
		public boolean hasNext() {
			return cur != null;
		}

		@Override
		public Object next() {
			if (cur == null) {
				throw new NoSuchElementException();
			}
			Object o = cur.data;
			cur = cur.next;
			return o;
		}

	}

	/**
	 * 把该链表逆置 
	 * 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		if (head == null) {
			return;
		}
		Node cur = head.next;
		head.next = null;
		while (cur != null) {
			Node tmp = new Node(cur.data, head);
			head = tmp;
			tmp = cur;
			cur = cur.next;
			tmp.data = null;
			tmp.next = null;
		}
	}

	/**
	 * 删除一个单链表的前半部分 
	 * 例如：list = 2->5->7->8, 删除以后的值为 7->8 
	 * 如果list = 2->5->7->8->10, 删除以后的值为7,8,10
	 * 
	 */
	public void removeFirstHalf() {
		Node fast = head;
		Node slow = head;
		while (fast != null && fast.next != null) {
			Node tmp = slow;
			slow = slow.next;
			fast = fast.next.next;
			tmp.data = null;
			tmp.next = null;
			size--;
		}
		head = slow;
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		if (length < 0) {
			throw new InvalidParameterException();
		}
		while (length-- > 0) {
			remove(i);
		}
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 
	 * 例如当前链表 = 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		if (list == null || list.size() == 0) {
			return new int[0];
		}
		int size = list.size();
		int[] result = new int[size];
		Iterator iter = list.iterator();
		for (int i = 0; i < size; i++) {
			result[i] = (int) get((int) iter.next());
		}
		return result;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 
	 * 从当前链表中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list) {
		if (list == null || list.size() == 0) {
			return;
		}
		int index = 0;
		Node cur = head;
		Iterator iter = list.iterator();
		Object tmp = iter.next();
		while (cur != null) {
			if ((int) cur.data < (int) tmp) {
				cur = cur.next;
				index++;
			} else if ((int) cur.data > (int) tmp) {
				if (!iter.hasNext()) {
					return;
				}
				tmp = iter.next();
			} else {
				cur = cur.next;
				remove(index);
			}
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		if (size <= 1) {
			return;
		}
		int index = 0;
		Node cur = head;
		while (cur.next != null) {
			if ((int) cur.data == (int) cur.next.data) {
				cur = cur.next;
				remove(index);
			} else {
				cur = cur.next;
				index++;
			}
		}
	}

	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {
		if (min >= max || head == null) {
			return;
		}
		Node left = head;
		while (left != null && (int) left.data <= min && left.next != null && (int) left.next.data <= min) {
			left = left.next;
		}
		Node right = left.next;
		while (right != null && (int) right.data < max) {
			Node tmp = right;
			right = right.next;
			tmp.data = null;
			tmp.next = null;
			size--;
		}
		left.next = right;
		if ((int) head.data > min && (int) head.data < max) {
			head = right;
			left.data = null;
			left.next = null;
			size--;
		}
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素也依值递增有序排列
	 * 
	 * @param list
	 */
	public LinkedList intersection(LinkedList list) {
		if (list == null) {
			return null;
		}
		LinkedList linkedList = new LinkedList();
		if (list.size() == 0) {
			return linkedList;
		}
		Node cur = head;
		Iterator iter = list.iterator();
		Object tmp = iter.next();
		while (cur != null) {
			if ((int) cur.data < (int) tmp) {
				cur = cur.next;
			} else if ((int) cur.data > (int) tmp) {
				if (!iter.hasNext()) {
					return linkedList;
				}
				tmp = iter.next();
			} else {
				linkedList.add(tmp);
				if (!iter.hasNext()) {
					return linkedList;
				}
				tmp = iter.next();
				cur = cur.next;
			}
		}
		return linkedList;
	}

	private static class Node {
		Object data;
		Node next;

		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	private void checkBounds(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
	}

	private void checkBoundsForAdd(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
	}
}
