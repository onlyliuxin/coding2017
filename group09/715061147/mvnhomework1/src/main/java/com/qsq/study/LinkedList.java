package com.qsq.study;

public class LinkedList<E> implements List<E>{

	private Node<E> first;
	private Node<E> last;
	private int size;
	
	private static class Node<T> {
		T item;
		Node<T> prev;
		Node<T> next;
		
		public Node(Node<T> prev, T item, Node<T> next) {
			this.prev = prev;
			this.item = item;
			this.next = next;
		}
	}
	
	/*
	 * 无参数构造函数
	 */
	public LinkedList() {
		first = null;
		last = first;
		size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	private void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
		}
	}

	@Override
	public boolean add(E e) {
		if (first == null) {
			// 链表为空
			first = new Node<E>(null, e, null);
		} else if (last == null) {
			// 尾结点为空
			last = new Node<E>(first, e, null);
			first.next = last;
		} else {
			Node<E> node = new Node<E>(last, e, null);
			last.next = node;
			last = node;
		}
		++size;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);
		if (index < 0 || index > size) {
			return false;
		}
		
		remove(index);
		return true;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		Node<E> current = first;
		while (index > 0) {
			--index;
			if (current == null) {
				return null;
			}
			current = current.next;
		}
		
		E oldItem = current.item;

		if (current.prev == null) {
			// 删除的是头结点
			first = current.next;
			if (current.next != null) {
				current.next.prev = null;
			}
		} else {
			current.prev.next = current.next;
			if (current.next != null) {
				current.next.prev = current.prev;
			}
		}
		--size;

		return oldItem;
	}

	@Override
	public E get(int index) {
		rangeCheck(index);
		
		int i = 0;
		Node<E> current = first;
		while (i < index) {
			if (current == null) {
				return null;
			}
			++i;
			current = current.next;
		}
		return current.item;
	}

	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		
		Node<E> current = first;
		while (index > 0) {
			--index;
			current = current.next;
		}
		E oldElement = current.item;
		current.item = element;
		
		return oldElement;
	}

	@Override
	public int indexOf(Object o) {
		if (first == null) {
			return -1;
		}

		Node<E> current = first;
		int index = 0;
		if (o == null) {
			while (current != null) {
				if (current.item == null) {
					return index;
				} else {
					current = current.next;
					++index;
				}
			}
		} else {
			while (current != null) {
				if (o.equals(current.item)) {
					return index;
				} else {
					current = current.next;
					++index;
				}
			}
		}

		return -1;
	}

}
