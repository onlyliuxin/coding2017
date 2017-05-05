package com.coding.basic;

import java.util.NoSuchElementException;

import com.coding.array.ArrayUtil;

public class LinkedList implements List {

	private Node head;

	public void add(Object o) {
		addLast(o);
	}

	public void add(int index, Object o) {
		Node node = new Node();
		node.data = o;
		if (index == 0) {
			addFirst(o);
			return;
		}
		Node before = getNode(index - 1);
		Node next = before.next;
		before.next = node;
		node.next = next;
	}

	private Node getLastNode() {
		Node temp = head;
		if (head != null) {
			while (true) {
				if (temp.next != null) {
					temp = temp.next;
				} else {
					break;
				}
			}
		} else {
			throw new NoSuchElementException();
		}
		return temp;
	}

	private Node getNode(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		int i = 0;
		Node temp = head;
		while (true) {
			if (temp == null) {
				throw new IndexOutOfBoundsException();
			}
			if (i == index) {
				break;
			} else {
				i++;
				temp = temp.next;
			}
		}
		return temp;
	}

	public Object get(int index) {
		Node node = getNode(index);
		return node.data;
	}

	public Object remove(int index) {
		if (index == 0) {
			removeFirst();
		}
		Node before = getNode(index - 1);
		Node temp = getNode(index);
		before.next = temp.next;
		return temp.data;
	}

	public int size() {
		int size = 0;
		Node temp = head;
		while (true) {
			if (temp == null) {
				break;
			} else {
				size++;
				temp = temp.next;
			}
		}
		return size;
	}

	public void addFirst(Object o) {
		Node node = new Node();
		node.data = o;
		node.next = head;
		head = node;
	}

	public void addLast(Object o) {
		Node node = new Node();
		node.data = o;
		if (head == null) {
			head = node;
			return;
		}
		Node last = getLastNode();
		last.next = node;
	}

	public Object removeFirst() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		Object obj = head.data;
		head = head.next;
		return obj;
	}

	public Object removeLast() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		if (head.next == null) {
			return removeFirst();
		}
		Node before = head;
		Node temp = head.next;
		while (true) {
			if (temp.next == null) {
				break;
			} else {
				before = temp;
				temp = temp.next;
			}
		}
		before.next = null;
		return temp.data;
	}

	public Iterator iterator() {
		return new LinkedIterator();
	}

	private static class Node {
		Object data;
		Node next;		
				
	}

	private class LinkedIterator implements Iterator {

		private Node node;

		public LinkedIterator() {
			node = head;
		}

		@Override
		public boolean hasNext() {
			if (node != null) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if (node == null) {
				throw new NoSuchElementException();
			} else {
				Object obj = node.data;
				node = node.next;
				return obj;
			}
		}

	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		if (head == null || head.next == null) {
			return;
		}
		Node temp = head.next;
		Node newHead = this.head;
		newHead.next = null;
		while (temp != null) {
			Node next = temp.next;
			temp.next = newHead;
			newHead = temp;
			temp = next;
		}
		this.head = newHead;
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf() {
		if (head == null || head.next == null) {
			return;
		}
		int len = size();
		for (int i = 0; i < len / 2; i++) {
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
		if (i < 0 || i >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if(head==null){
			return;
		}
		Node ni = head;
		Node temp = head;
		for (int j = 0; j < i + length; j++) {
			if (temp == null) {
				break;
			}
			if (j + 1 == i) {
				ni = temp;
			}
			temp = temp.next;
		}
		ni.next = temp;
		if (i == 0) {
			head = temp;
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
		if(list==null||head==null||list.head==null){
			return new int[0];
		}
		int[] arr = new int[0];
		Node tempA = head;
		Node tempB = list.head;
		int index=0;
		while(tempA!=null&&tempB!=null){
			int len = (int)tempB.data - index;
			int i = 0;
			for(i=0;i<len;i++){
				if(tempA==null){
					break;
				}
				tempA = tempA.next;
			}
			if(i==len&&tempA!=null){
				arr = new ArrayUtil().grow(arr, 1);
				arr[arr.length-1] = (int) tempA.data;
			}else{
				break;
			}
			index = (int)tempB.data;
			tempB = tempB.next;
		}
		return arr;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在listB中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list) {
		if(list==null||head==null||list.head==null){
			return ;
		}
		Node tempB = new Node();
		tempB.next = list.head;
		while(tempB.next!=null){
			if(tempB.next.data==null){
				tempB = tempB.next;
				continue;
			}
			int b = (int)tempB.next.data;
			Node tempA = new Node();
			tempA.next = head;
			while(tempA.next!=null){
				if(tempA.next.data==null){
					tempA = tempA.next;
					continue;
				}
				int a = (int)tempA.next.data;
				if(b<a){
					break;
				}else if(b==a){
					tempA.next = tempA.next.next;
				}else{
					tempA = tempA.next;
				}
			}
			tempB = tempB.next;
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		if(head==null||head.next==null){
			return ;
		}
		Node temp = head;
		while(temp.next!=null){
			if(temp.data==null||temp.next==null){
				temp  = temp.next;
				continue;
			}
			int a = (int) temp.data;
			int b = (int) temp.next.data;
			if(a==b){
				temp.next = temp.next.next;
			}else{
				temp = temp.next;
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
		if(head==null||min>=max){
			return ;
		}
		Node temp = new Node();
		temp.next = head;
		while(temp.next!=null){
			if(temp.next.data==null){
				temp = temp.next;
				continue;
			}
			int d = (int) temp.next.data;
			if(d>min&&d<max){
				temp.next = temp.next.next;
			}else if(d<=min){
				temp = temp.next;
			}else if(d>=max){
				break;
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
		LinkedList link = new LinkedList();
		if(list==null||head==null||list.head==null){
			return link;
		}
		Node tempA = new Node();
		tempA.next = head;
		Node tempB = new Node();
		tempB.next = list.head;
		while(tempA.next!=null&&tempB.next!=null){
			if(tempA.next.data==null){
				tempA = tempA.next;
				continue;
			}
			if(tempB.next.data==null){
				tempB = tempB.next;
				continue;
			}
			int a = (int)tempA.next.data;
			int b = (int)tempB.next.data;
			if(a<b){
				tempA = tempA.next;
			}else if(a>b){
				tempB = tempB.next;
			}else{
				link.add(tempA.next.data);
				tempA = tempA.next;
				tempB = tempB.next;
			}
		}
		return link;
	}
}
