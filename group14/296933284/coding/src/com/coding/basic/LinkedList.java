package com.coding.basic;

import java.util.Collection;


/**
 * LinkedList (单链表) 第14小组 296933284
 * 
 * @author Tonnyson
 *
 */
public class LinkedList<T> implements List<T> {

	private Node<T> head;
	private int size;

	public LinkedList() {
		super();
		this.head = new Node<T>(null);
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
}