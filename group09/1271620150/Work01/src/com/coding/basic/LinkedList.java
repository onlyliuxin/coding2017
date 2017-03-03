package com.coding.basic;

import java.util.Collection;
import java.util.NoSuchElementException;

public class LinkedList implements List {

	private Node first;
	private Node last;
	private int size;

	public LinkedList() {
	}

	public LinkedList(Collection<Object> c) {
		this();
		addAll(size, c);
	}

	private void addAll(int index, Collection<Object> c) {
		checkPositionIndex(size);

		Object[] a = c.toArray();
		int numNew = a.length;
		if (numNew == 0)
			return;

		Node pred, succ;
		if (index == size) {
			succ = null;
			pred = last;
		} else {
			succ = node(index);
			pred = succ.prev;
		}

		for (Object o : a) {
			Node newNode = new Node(pred, o, null);
			if (pred == null)
				first = newNode;
			else
				pred.next = newNode;
			pred = newNode;
		}

		if (succ == null) {
			last = pred;
		} else {
			pred.next = succ;
			succ.prev = pred;
		}

		size += numNew;
	}

	public void add(Object o) {
		linkLast(o);
	}

	private void linkLast(Object o) {
		Node l = last;
		Node newNode = new Node(l, o, null);
		last = newNode;
		if (l == null) {
			first = newNode;
		} else {
			l.next = newNode;
		}
		size++;

	}

	public void add(int index, Object o) {
		checkPositionIndex(index);
		if (index == size) {
			linkLast(o);
		} else {
			Node l = node(index);
			linkBefore(o, l);
		}

	}

	public void linkBefore(Object o, Node succ) {
		final Node pred = succ.prev;
		final Node newNode = new Node(pred, o, succ);
		succ.prev = newNode;
		if (pred == null)
			first = newNode;
		else
			pred.next = newNode;
		size++;
	}

	public Object get(int index) {
		checkElementIndex(index);
		return node(index).data;
	}

	public Object remove(int index) {
		checkElementIndex(index);
		return unlink(node(index));
	}

	private Object unlink(Node node) {
		final Object element = node.data;
		final Node next = node.next;
		final Node prev = node.prev;

		if (prev == null) {
			first = next;
		} else {
			prev.next = next;
			node.prev = null;
		}

		if (next == null) {
			last = prev;
		} else {
			next.prev = prev;
			node.next = null;
		}

		node.data = null;
		size--;
		return element;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		linkFirst(o);
	}

	private void linkFirst(Object o) {
		final Node f = first;
		final Node newNode = new Node(null, o, f);
		first = newNode;
		if (f == null)
			last = newNode;
		else
			f.prev = newNode;
		size++;
	}

	public void addLast(Object o) {
		linkLast(o);
	}

	public Object removeFirst() {
		final Node f = first;
		if (f == null)
			throw new NoSuchElementException();
		return unlinkFirst(f);
	}

	private Object unlinkFirst(Node f) {
		final Object element = f.data;
		final Node next = f.next;
		f.data = null;
		f.next = null;
		first = next;
		if (next == null)
			last = null;
		else
			next.prev = null;
		size--;
		return element;
	}

	public Object removeLast() {
		final Node l = last;
		if (l == null)
			throw new NoSuchElementException();
		return unlinkLast(l);
	}

	private Object unlinkLast(Node l) {
		final Object element = l.data;
		final Node prev = l.prev;
		l.data = null;
		l.prev = null;
		last = prev;
		if (prev == null)
			first = null;
		else
			prev.next = null;
		size--;
		return element;
	}

	public Iterator iterator(int index) {
		return new LinkListIterator(index);
	}

	private static class Node {
		Object data;
		Node next;
		Node prev;

		Node(Node prev, Object obj, Node next) {
			this.data = obj;
			this.next = next;
			this.prev = prev;
		}

	}

	Node node(int index) {
		// assert isElementIndex(index);

		if (index < (size >> 1)) {
			Node x = first;
			for (int i = 0; i < index; i++)
				x = x.next;
			return x;
		} else {
			Node x = last;
			for (int i = size - 1; i > index; i--)
				x = x.prev;
			return x;
		}
	}

	private boolean isElementIndex(int index) {
		return index >= 0 && index < size;
	}

	private boolean isPositionIndex(int index) {
		return index >= 0 && index <= size;
	}

	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + size;
	}

	private void checkElementIndex(int index) {
		if (!isElementIndex(index))
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	private void checkPositionIndex(int index) {
		if (!isPositionIndex(index))
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	private class LinkListIterator implements Iterator {
		private Node lastReturned = null;
		private Node next;
		private int nextIndex;

		LinkListIterator(int index) {
			next = (index == size) ? null : node(index);
			nextIndex = index;
		}

		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		@Override
		public Object next() {
			if (!hasNext())
				throw new NoSuchElementException();

			lastReturned = next;
			next = next.next;
			nextIndex++;
			return lastReturned.data;
		}

	}

}