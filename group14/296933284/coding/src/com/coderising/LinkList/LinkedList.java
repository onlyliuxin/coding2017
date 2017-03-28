package com.coderising.LinkList;

import com.coding.basic.Iterator;
import com.coding.basic.List;

import java.util.Collection;


/**
 * LinkedList (单链表) 第14小组 296933284
 * 
 * @author Tonnyson
 *
 */
public class LinkedList<T extends Comparable> implements List<T> {

	private Node<T> head;
	private int size;

	public LinkedList() {
		super();
		this.head = new Node<T>(null);
	}

	public Node<T> getHead() {
		return head;
	}

	@Override
	public boolean add(T element) {
		addLast(element);
		return true;
	}

	@Override
	public void add(int index, T element) {

		if (index == size) {
			addLast(element);
		} else {
			Node<T> r = getPreNode(index);
			Node<T> node = new Node<>(element);
			node.next = r.next;
			r.next = node;
			size++;

		}
	}

	public void addFirst(T element) {
		Node<T> node = new Node<>(element);
		node.next = head.next;
		head.next = node;
		size++;
	}

	public void addLast(T element) {
		
		Node<T> node = new Node<>(element);

		Node<T> r = head;
		while (r.next != null) r = r.next;

		r.next = node;
		
		size++;

	}

	public void addAll(Collection<T> c) {

		Iterator<T> iter = (Iterator<T>) c.iterator();

		while (iter.hasNext()) {
			addLast(iter.next());
		}
	}

	@Override
	public T get(int index) {

		rangCheck(index);
		
		return (T) getPreNode(index).next.data;
	}

	@Override
	public T remove(int index) {
		
		rangCheck(index);
		
		Node<T> r = getPreNode(index);
		
		T result = (T) r.next.data;
		
		r.next = r.next.next;
		size--;

		return  result;
	}

	public T removeFirst() {
		return remove(0);
	}

	public T removeLast() {
		return remove(size - 1);
	}

	private Node<T> getPreNode(int index) {
		
		rangCheck(index);
		
		if (index == 0) {
			return head;
		} else {
			Node<T> r = head;
			
			for (int i = 0; i < index; i++)
				r = r.next;
			
			return r;			
		}
		
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iter<>();
	}

	private class Iter<T> implements Iterator<T> {
		int current = 0;

		@Override
		public boolean hasNext() {
			return current != size;
		}

		@Override
		public T next() {
			int i = current;
			
			rangCheck(i);
			
			current++;

			return (T) get(i);
		}

	}

	private void rangCheck(int index) {
		if ( index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
	}

	private static class Node<T> {
		T data;
		Node<T> next;

		Node(T data) {
			super();
			this.data = data;
			this.next = null;
		}
	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse() {
		Node<T> r = head.next;
		Node<T> p = null;
		head.next = null;

		while (r != null) {
			p = r;
			r = r.next;
			p.next = head.next;
			head.next = p;
		}
	}

	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 *
	 */
	public void removeFirstHalf() {
		int len = (int) Math.ceil(size / 2.0);

		remove(0, len);
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {

		rangCheck(i);

		if (i + length - 1 > size - i) {
			throw new IndexOutOfBoundsException();
		}

		Node<T> preFirst = getPreNode(i);
		Node<T> preLast = getPreNode(i + length - 1).next;

		preFirst.next = preLast.next;
		preLast = null;
		size -= length;

	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * @param list
	 */
	public int[] getElements(LinkedList<Integer> list) {
		int[] elements = new int[list.size()];

		for (int i = 0; i < list.size(); i++) {
			elements[i] = (Integer) get((int) list.get(i));
		}

		return elements;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素
	 *
	 * @param list
	 */
	public void subtract(LinkedList<T> list) {
		int len;
		for (int i = 0; i < list.size(); i++) {
			Node<T> p = head;
			Node<T> r = null;

			T value = list.get(i);

			while (p.next != null) {

				if (p.next.data.equals(value)) {
					r = p.next;
					p.next = r.next;
					r.next = null;
					size--;
				} else {
					p = p.next;
				}


			}
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		Node<T> p = head;
		Node<T> r = head.next;

		while (p.next != null && r.next != null) {
			if (p.next.data.compareTo(r.next.data) == 0) {
				p.next = r.next;
				r.next = p.next.next;
				size--;
			} else {
				p = p.next;
				r = r.next;
			}
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {
		Node<T> p = head;

		while (p.next!= null) {
			if (p.next.data.compareTo(min) > 0 && p.next.data.compareTo(max) < 0) {
				Node<T> r = p.next;
				p.next = r.next;
				r.next = null;
				size--;
			} else {
				p = p.next;
			}
		}
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public LinkedList<T> intersection(LinkedList<T> list){
		LinkedList<T> newList = new LinkedList<T>();

		Node<T> p1 = head;

		while (p1.next != null) {
			Node<T> p2 = list.getHead();
			while (p2.next != null && p1.next.data.compareTo(p2.next.data) != 0) {
				p2 = p2.next;
			}

			if (p2.next != null) {
				newList.add(p2.next.data);
			}
			p1 = p1.next;
		}

		return newList;
	}
}













