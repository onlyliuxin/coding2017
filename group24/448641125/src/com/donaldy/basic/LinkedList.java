package com.donaldy.basic;

import java.util.*;

/**
 * Created by donal on 2017/3/9.
 * single LinkedList
 */
public class LinkedList implements List {

	private int size;
	private Node head;
	private Node last;

	public void add(Object o) {
		addLast(o);
	}

	public void addLast(Object o) {
		final Node l = last;
		final Node newNode = new Node(o, null);
		last = newNode;
		if (l == null)
			head = newNode;
		else
			l.next = newNode;
		size ++;
	}

	public void add(int index , Object o) {
		makeSure(index);
		if (index == 0){
			addFirst(o);
		}else {
			final Node preNode = getPreNode(index);
			final Node nextNode = preNode.next;
			final Node newNode = new Node(o, nextNode);
			preNode.next = newNode;
		}
	}

	public void addFirst(Object o){
		final Node h = head;
		final Node newNode = new Node(o, head);
		head = newNode;
		if (h == null)
			last = newNode;
		size++;
	}

	private Node getPreNode(int index) {
		Node preNode = head;
		for (int i = 0 ; i != index - 1 && preNode != null; ++i) {
			preNode = preNode.next;
		}
		return preNode;
	}

	private void makeSure(int index) {
		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException();
	}

	public Object get(int index) {
		Object targetData = null;
		makeSure(index);
		Iterator iterator = iterator();
		for (int i = 0; iterator.hasNext(); ++i, iterator.next()) {
			if (i == index) {
				targetData = iterator.next();
				break;
			}
		}
		return targetData;
	}

	public Object remove(int index) {
		if (size <= 0)
			throw new IndexOutOfBoundsException();
		makeSure(index);
		Object oldData;
		if (index == 0) {
			oldData = removeFirst();
		} else if (index == size - 1) {
			oldData = removeLast();
		} else {
			final Node preNode = getPreNode(index);
			final Node nextNode = preNode.next.next;
			oldData = preNode.next.data;
			preNode.next = nextNode;
			size --;
		}
		return oldData;
	}

	public Object removeFirst() {
		Object oldData;
		final Node h = head.next;
		oldData = head.data;
		head = h;
		size --;
		return oldData;
	}

	public Object removeLast() {
		Object oldData;
		final Node l = getPreNode(size);
		oldData = last.data;
		last = l;
		size --;
		return oldData;
	}
	
	public int size(){
		return size;
	}


	public Iterator iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator{
		private Node pointer = head;
		private int nextIndex = 0;

		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		@Override
		public Object next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Object nodeData = pointer.data;
			pointer = pointer.next;
			nextIndex ++;
			return nodeData;
		}
	}

	private static class Node {
		Object data;
		Node next;
		Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse() {
		Node node = last;
		Node preNode = head;
		last = head;
		head = node;
		node = preNode.next;
		Node nextNode;
		preNode.next = null;
		while (node != null) {
			nextNode = node.next;
			node.next = preNode;
			preNode = node;
			node = nextNode;
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf() {
		int half = size / 2;
		Node node = head;
		while (half != 0 ) {
			head = head.next;
			node.next = null;
			node = head;
			half --;
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length) {
		if (i + length != size)
			makeSure(i + length);
		Node node = head;
		Node preNode = head;

		if (i > 0) {
			for (int j = 0; j < i; ++j) {
				preNode = node;
				node = node.next;
			}
		}

		Node tempNode;
		for (int j = 0; j < length; ++j) {
			tempNode = node.next;
			node.next = null;
			node = tempNode;
			size --;
		}

		if (i == 0) {
			head = node;
		} else {
			preNode.next = node;
		}
	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		int array[] = new int[list.size()];
		int index = 0;
		int i = 0;
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			i = (int)iterator.next();
			makeSure(i);
			array[index ++] = (int)get(i);
		}
		return array;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 *
	 * (适用于list 是 List的真子集)
	 * @param list
	 */
	public void subtract(LinkedList list) {
		Iterator iterator = list.iterator();
		int index = 0;
		Object element;
		while (iterator.hasNext()) {
			element = iterator.next();
			for (int i = index; i < size; ++i) {
				if (element == get(i)) {
					index = i;
					break;
				}
			}
			remove(index);
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		Set set = new HashSet();
		Object element;
		int index = 0;
		Node node = head;
		while (node != null) {
			element = node.data;
			if (set.contains(element)) {
				System.out.println("remove !");
				remove(index);
			}else {
				set.add(element);
				index++;
			}
			node = node.next;
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max) {
		if (max < min || max < 0 || min < 0)
			throw new RuntimeException();
		Node startNode, endNode, node, preNode;
		startNode = head;
		endNode = last;
		node = head;
		preNode = node;
		int element;
		boolean flagS = true, flagT = true;
		while (node != null) {
			element = (int) node.data;
			if (element > min && flagS) {
				startNode = node;
				flagS = false;
			}
			if (element >= max && flagT) {
				endNode = node;
				flagT = false;
			}
			if (flagS)
				preNode = node;
			node = node.next;
		}
		if (head == startNode) {
			head = endNode;
		} else {
			if (last == endNode) {
				last = preNode;
				preNode.next = null;
				endNode = null;
			} else {
				preNode.next = endNode;
			}
		}
		Node tempNode;
		while (startNode != endNode) {
			tempNode = startNode;
			startNode = startNode.next;
			tempNode.next = null;
			size --;
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list) {
		LinkedList newLink = new LinkedList();
		Iterator it1 = iterator();
		Iterator it2 = list.iterator();
		int element1 = (int)it1.next();
		int element2 = (int)it2.next();
		while (it1.hasNext() && it2.hasNext()) {
			if (element1 < element2) {
				newLink.add(element1);
				element1 = (int) it1.next();
			} else {
				newLink.add(element2);
				element2 = (int) it2.next();
			}
		}

		while (it1.hasNext()) {
			if (element1 == 0)
				element1 = (int)it1.next();
			newLink.add(element1);
			element1 = 0;
		}
		while (it2.hasNext()) {
			if (element2 == 0)
				element2 = (int)it2.next();
			newLink.add(element2);
			element2 = 0;
		}
		if (element1 < element2)
			newLink.add(element2);
		else if (element2 < element1)
			newLink.add(element1);

		return newLink;
	}
}
