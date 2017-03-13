package com.coding.basic;

import java.awt.Dimension;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

public class LinkedList implements List {

	private Node head;
	private int size;
	private Node last;
	
	public void add(Object o) {
		addLast(o);
	}

	public void add(int index, Object o) {
		checkIndexRange(index);
		if (index == 0) {
			head = new Node(o, head);
			last = head;
			size++;
		}else if(index==size()){
			last.next = new Node(o, null);
			last = last.next;
			size++;
		}else {
			Node nd = getNode(index - 1);
			nd.next = new Node(o, nd.next);
			size++;
		}
	}

	public Object get(int index) {
		return getNode(index).data;
	}

	private Node getNode(int index) {
		if (isLastIndex(index)){
			return last;
		}
		Node nd = head;
		for (int i = 0; i < index; i++) {
			nd = nd.next;
		}
		return nd;
	}

	private boolean isLastIndex(int index) {
		return index==size()-1;
	}

	public Object remove(int index) {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		checkIndexRange(index);
		if (index == 0) {
			Object o = head.data;
			head = head.next;
			size--;
			return o;
		}else if(isLastIndex(index)){
			Object o = last.data;
			last=getNode(index-1);
			last.next =null;
			size--;
			return o;
		}else {
			Node nd = getNode(index - 1);
			Object o = nd.next.data;
			nd.next = nd.next.next;
			size--;
			return o;
		}
	}

	private void checkIndexRange(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("index超出数组界限?");
		}
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		add(0, o);
	}

	public void addLast(Object o) {
		if (size == 0) {
			addFirst(o);
		} else {
			add(size, o);
		}
	}

	public Object removeFirst() {
		return remove(0);
	}

	public Object removeLast() {
		return remove(size - 1);
	}

	public Iterator iterator() {
		return new MyIterator();
	}

	public void clear() {
		head = null;
		size = 0;
	}

	private class MyIterator implements Iterator {

		public Node current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Object next() {
			Object o = current.data;
			current = current.next;
			return o;
		}
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
		if (size() < 2) {
			return;
		}
		Node newHeadNode = head;
		Node curNode = newHeadNode.next;
		newHeadNode.next = null;
		Node nextNode;
		do {
			nextNode = curNode.next;
			curNode.next = newHeadNode;
			newHeadNode = curNode;
			curNode = nextNode;
		} while (curNode != null);
		head = newHeadNode;
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 * 
	 */
	public void removeFirstHalf() {
		if (size() < 2) {
			return;
		}
		int i = (size()) / 2;
		head = getNode(i);
		size = size - i;
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		if ((i + length) > size) {
			throw new IndexOutOfBoundsException("index超出数组界限.");
		}
		if (i == 0) {
			head = getNode(length);
		}else if(i+length==size){
			last= getNode(i-1);
			last.next = null;
		}else {
			getNode(i - 1).next = getNode(i + length);
		}
		size -= length;
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		if (list == null || list.size() == 0) {
			int[] arrElem = new int[size()];
			Node node = head;
			for (int i = 0; i < size(); i++) {
				arrElem[i] = (int) node.data;
				node = node.next;
			}
			return arrElem;
		} else {
			if (list.size() > size() || (int) list.get(list.size() - 1) + 1 > size()) {
				throw new IndexOutOfBoundsException("超出数组界限。");
			}
			int[] arrElem = new int[list.size()];
			int cur = 0;
			Node node = head;
			for (int i = 0; i < (int) list.get(list.size() - 1) + 1; i++) {
				if (i == (int) list.get(cur)) {
					arrElem[cur++] = (int) node.data;
				}
				node = node.next;
			}
			return arrElem;
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list) {
		Node curNode = head;
		int curIndex = 0;
		for (int i = 0; i < list.size(); i++) {
			int value = (int) list.get(i);
			for (int j = curIndex; j < size(); j++) {
				if (value == (int) curNode.data) {
					remove(j);
					break;
				} else {
					curNode = curNode.next;
				}
			}
			curNode = head;
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		Set<Integer> set = new TreeSet<Integer>();
		Node node = head;
		for (int i = 0; i < size(); i++) {
			set.add((Integer) node.data);
			node = node.next;
		}
		clear();
		for (Integer i : set) {
			add(i);
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {
		Node node = head;
		int startIndex = -1;
		int endIndex = size();
		for (int i = 0; i < size(); i++) {
			if (node == null) {
				break;
			}
			int value = (int) node.data;
			if (value < min) {
				node = node.next;
				continue;
			}
			if (value > max) {
				endIndex = i - 1;
				break;
			}
			if (startIndex == -1) {
				startIndex = i;
			}
			node = node.next;
		}
		if (startIndex > -1) {
			if (startIndex == 0 && endIndex == size()) {
				clear();
				return;
			}
			remove(startIndex, endIndex - startIndex);
		}
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public LinkedList interSection(LinkedList list) {
		LinkedList newList = new LinkedList();
		Node aHead = head;
		Node bHead = list.head;
		for (int i = 0; i < list.size(); i++) {
			int bValue = (int) bHead.data;
			int aValue = 0;
			do {
				aValue = (int) aHead.data;
				if (aHead == null || aValue > bValue) {
					break;
				}
				if (aValue == bValue) {
					newList.add(bValue);
					aHead = aHead.next;
					break;
				} else {
					aHead = aHead.next;
				}
			} while (aHead != null && (int) aHead.data <= bValue);
			if (aHead == null) {
				break;
			}
			bHead = bHead.next;
		}
		return newList;
	}

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.reverse();
		for (int i = 0; i < list.size; i++) {
			System.out.println(list.get(i));
		}
		// System.out.println(list.get(2));
		// list.add(2, 100);
		// System.out.println(list.get(2));
		// list.addFirst(10);
		// System.out.println(list.get(2));
		// list.addLast(100);
		// System.out.println(list.remove(1));
		// System.out.println(list.removeFirst());
		// System.out.println(list.removeLast());

	}
}
