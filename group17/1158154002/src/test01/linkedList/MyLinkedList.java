package test01.linkedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
	private int theSize;	
	private int modCount = 0;
	private Node<T> beginMarker;
	private Node<T> endMarker;

	private static class Node<T> {
		public T data;
		public Node<T> prev;
		public Node<T> next;

		public Node(T d, Node<T> p, Node<T> n) {
			data = d;
			prev = p;
			next = n;
		}
	}

	public MyLinkedList() {
		clear();
	}

	public void clear() {
		beginMarker = new Node<T>(null, null, null);
		endMarker = new Node<T>(null, beginMarker, null);
		beginMarker.next = endMarker;
		theSize = 0;
		modCount++;
	}

	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public void add(T x) {
		add(size(), x);
	}

	public void add(int idx, T x) {
		addBefore(getNode(idx), x);
	}

	public T get(int idx) {
		return getNode(idx).data;
	}

	public T set(int idx, T newVal) {
		Node<T> p = getNode(idx);
		T oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}

	public T remove(int idx) {
		return remove(getNode(idx));
	}

	private void addBefore(Node<T> p, T x) {
		Node<T> newNode = new Node<T>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}

	private T remove(Node<T> p) {
		
		 final T element = p.data;
	        final Node<T> next = p.next;
	        final Node<T> prev = p.prev;

	        if (prev == null) {
	        	beginMarker = next;
	        } else {
	            prev.next = next;
	            p.prev = null;
	        }

	        if (next == null) {
	        	endMarker = prev;
	        } else {
	            next.prev = prev;
	            p.next = null;
	        }

	        p.data = null;
			theSize--;
			modCount++;
		return p.data;
	}

	private Node<T> getNode(int idx) {
//		Node<T> p;
//		if (idx < 0 || idx > size()) {
//			throw new IndexOutOfBoundsException();
//		}
//		if (idx < size() / 2) {
//			p = beginMarker.next;
//			for (int i = 0; i < idx; i++) {
//				p = p.next;
//			}
//		} else {
//			p = endMarker;
//			for (int i = size(); i < idx; i--) {
//				p = p.prev;
//			}
//		}
//		
        if (idx < (size() >> 1)) {
            Node<T> p = beginMarker;
            for (int i = 0; i < idx; i++)
                p = p.next;
            return p;
        } else {
            Node<T> p = endMarker;
            for (int i = size() - 1; i > idx; i--)
                p = p.prev;
            return p;
        }
	}

	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<T> {
		private Node<T> current = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;

		@Override
		public boolean hasNext() {
			return current != endMarker;
		}

		@Override
		public T next() {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		public void remove() {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
			if (!okToRemove) {
				throw new IllegalStateException();
			}
			MyLinkedList.this.remove(current.prev);
			okToRemove = false;
			expectedModCount++;
		}
	}
}
