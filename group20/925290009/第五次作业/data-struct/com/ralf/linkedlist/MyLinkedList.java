package com.ralf.linkedlist;

import java.util.Arrays;
import java.util.Objects;
import java.util.TreeSet;

import BasicData.MyArrayList;
import BasicData.MyIterator;
import BasicData.MyList;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {

	private Node<T> head;// 指向头部，始终设置为空
	// private Node<T> tail;// 指向尾部的节点
	private int size;

	public MyLinkedList() {
		this.head = new Node<T>(null);
		this.size = 0;
	}

	private static class Node<T> {
		Node<T> next = null;
		T item = null;

		public Node(T t) {
			item = t;
		}

	}

	@Override
	public boolean add(T t) {
		// TODO Auto-generated method stub
		return addLast(t);
	}

	@Override
	public void add(int index, T t) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> newNode = new Node<T>(t);
		if (index == 0) {
			Node<T> oldNode = head.next;
			head.next = newNode;
			newNode.next = oldNode;
			size++;
		}

		else {
			Node<T> current = getNode(index - 1);
			newNode.next = current.next;
			current.next = newNode;
			size++;
		}

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			return removeFirst();
		} else if (index == size - 1) {
			return removeLast();
		} else {
			Node<T> current = getNode(index - 1);
			T data = current.next.item;
			current.next.item = null;
			current.next = current.next.next;
			size--;
			return data;
		}
	}

	@Override
	public T set(int index, T t) {
		// TODO Auto-generated method stub
		Node<T> current = getNode(index);
		T data = current.item;
		current.item = t;
		return data;
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		T data = getNode(index).item;
		return data;
	}

	public int indexOf(T t) {
		Node<T> current = this.head;
		int index = 0;
		while (current.next != null) {
			current = current.next;
			if (Objects.equals(current.item, t)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	private Node<T> getNode(int index) {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> current = this.head;
		int m_size = 0;
		while (current.next != null && m_size <= index) {
			current = current.next;
			m_size++;
		}
		return current;
	}

	public boolean addFirst(T t) {

		if (head.next == null) {
			Node<T> current = new Node<T>(t);
			head.next = current;
			current = null;
			size++;
			return true;
		} else {
			Node<T> current = new Node<T>(t);
			current.next = head.next;
			head.next = current;
			size++;
			return true;
		}
	}

	public boolean addLast(T t) {

		if (head.next == null) {
			Node<T> current = new Node<T>(t);
			head.next = current;
			current.next = null;
			size++;
			return true;
		} else {
			Node<T> current = new Node<T>(t);
			Node<T> oldNode = getNode(size - 1);
			oldNode.next = current;
			current.next = null;
			size++;
			return true;
		}

	}

	public T removeFirst() {
		if (head.next == null) {
			return null;
		} else if (head.next.next == null) {
			T data = head.next.item;
			head.next.item = null;
			head = null;
			size--;
			return data;
		} else {
			T data = head.next.item;
			Node<T> current = head.next.next;
			head.next.item = null;
			head.next = current;
			size--;
			return data;
		}
	}

	public T removeLast() {
		if (head.next == null) {
			return null;
		} else if (head.next.next == null) {
			T data = head.next.item;
			head.next.item = null;
			size--;
			return data;
		} else {
			Node<T> current = getNode(size - 2);
			T data = current.next.item;
			current.next.item = null;
			current.next = null;
			size--;
			return data;
		}
	}
	
	public boolean isContain(T t){
		
		if (head.next == null) {
			return false;
		}
		Node<T> current = head;
		while(current.next != null){
			current = current.next;
			if (Objects.equals(t, current.item)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		this.head.next = reverseList(head.next);
	}

	private Node<T> reverseList(Node<T> mhead) {

		if (mhead == null || mhead.next == null) {
			return mhead;
		}
		Node<T> reHead = reverseList(mhead.next);
		mhead.next.next = mhead;
		mhead.next = null;
		return reHead;
	}

	/**
	 * 删除一个单链表的后半部分 例如：list = 2->5->7->8 , 删除以后的值为 2->5 如果list = 2->5->7->8->10
	 * ,删除以后的值为2,5,7
	 */
	public void removeLastHalf() {

		if (size < 2) {
			return;
		}
		int index = (size - 1) / 2 + 1;
		Node<T> current = getNode(index - 1);
		Node<T> temp = current;
		while (index < size) {
			temp = temp.next;
			temp.item = null;
			index++;
		}
		size = (size - 1) / 2 + 1;
		current.next = null;
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf() {

		if (size < 2) {
			return;
		}
		int maxIndex = size/ 2;
		int index = 0;
		Node<T> current = head;
		while (index < maxIndex) {
			current = current.next;
			current.item = null;
			index++;
			size--;
		}
		//size = (size - 1) / 2 + 1;
		head.next = current.next;
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {

		if (i < 0 || i >= size || (i + length - 1) > size) {
			throw new IndexOutOfBoundsException();
		}
		int index = 0;
		Node<T> current;
		if (i == 0) {
			current = head;
		} else {
			current = getNode(i - 1);
		}
		Node<T> temp = current;
		while (index < length) {
			current = current.next;
			current.item = null;
			index++;
		}
		if (current.next == null) {
			if (i == 0) {
				head.next = null;
			} else {
				temp.next = null;
			}
		} else {
			if (i == 0) {
				head.next = current.next;
			} else {
				temp.next = current.next;
			}
		}
		size = size - length;

	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public int[] getElements(MyLinkedList<T> list) {
		int[] elements = new int[list.size];
		int i = 0;
		MyIterator<Integer> iterator = (MyIterator<Integer>) list.iterator();
		while (iterator.hasNext()) {
			int index = iterator.Next();
			if (index < this.size) {
				Node<T> current = getNode(index);
				elements[i++] = (Integer) current.item;
			} else {
				elements[i++] = 0;// 没有该元素时值为零，由于是int类型，引用类型时为null
			}
		}
		return Arrays.copyOf(elements, i);
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(MyLinkedList<T> list) {

		if (list.size == 0) {
			return;
		}
		MyIterator<T> iterator = list.iterator();

		while (iterator.hasNext()) {
			T element = iterator.Next();
			int index = indexOf(element);// 当前链表的索引
			if (index != -1) {
				remove(index);
			}
		}
	}

	/**
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeRepeatValues() {
		if (head.next == null || head.next.next == null) {
			return;
		}
		// 利用自己的MyArrayList
		MyArrayList<T> list = new MyArrayList<>();
		// 遍历链表
		Node<T> current = head;
		T obj = null;
		while (current.next != null) {
			current = current.next;
			obj = current.item;
			if (list.isContain(obj)) {
				// int index = indexOf(obj);
				remove(indexOf(obj)); // 可以增加remove(T t)方法，供调用

			} else {
				list.add(obj);
			}
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {

		if (head.next == null || head.next.next == null) {
			return;
		}
		Node<T> current = head;
		T obj = null;
		while (current.next != null) {
			current = current.next;
			obj = current.item;
			if (current.next != null && Objects.equals(obj, current.next.item)) {
				// int index = indexOf(obj);
				remove(indexOf(obj)); // 可以增加remove(T t)方法，供调用
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

		if (head.next == null) {
			return;
		}
		Node<T> current = head;
		Integer integer;// 目前只比较数据类型的，若是应用类型，需要实现Comparable接口
		while (current.next != null) {
			current = current.next;
			integer = (Integer) current.item;
			if (integer.intValue() > min && integer.intValue() < max) {
				remove(indexOf(current.item));
			}
		}
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public MyLinkedList<T> intersection(MyLinkedList<T> list) {

		if (list.size == 0 || head.next == null) {
			return null;
		}
		MyLinkedList<T> newLinked = new MyLinkedList<T>();
		MyIterator<T> iterator = list.iterator();
		//MyArrayList<T> arrayList = new MyArrayList<>();//没增加排序功能
		//ArrayList<T>  arrayList = new ArrayList<>();
		TreeSet<T> treeSet = new TreeSet<>();
		
		while (iterator.hasNext()) {
			T element = iterator.Next();
			if (isContain(element)) {
				treeSet.add(element);
			}
		}

		for(T t : treeSet){
			newLinked.add(t);
		}
		return newLinked;
		
	}
	/*
	@SuppressWarnings("unchecked")
	public MyLinkedList<T> union(MyLinkedList<T> list) {

		if (head.next == null) {

			if (list.size == 0) {
				return null;
			} else {
				return list;
			}
		} else {
			if (list.size == 0) {
				return this;
			} else {

				MyLinkedList<T> newList = new MyLinkedList<T>();
				TreeSet<T> treeSet = new TreeSet<>();// 可用MyArrayList装所有不同元素，并排序添加到linkedlist中

				Node<T> current = head;
				while (current.next != null) {
					current = current.next;
					treeSet.add(current.item);
				}
				MyIterator<T> iterator = (MyIterator<T>) list.iterator();
				while (iterator.hasNext()) {
					treeSet.add(iterator.Next());
				}
				for (T t : treeSet) {
					newList.add(t);
				}
				return newList;
			}
		}

	}
	
	*/
	public MyIterator<T> iterator() {

		return new LinkedListIterator();
	}

	private class LinkedListIterator implements MyIterator<T> {

		private int current = 0;
		T data = null;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (current < size);
		}

		@Override
		public T Next() {
			// TODO Auto-generated method stub
			if (hasNext()) {
				data = getNode(current).item;
				current++;
				return data;
			}
			return null;
		}
	}

}
