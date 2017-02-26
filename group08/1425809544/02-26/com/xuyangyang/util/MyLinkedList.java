package com;

import java.util.NoSuchElementException;

public class MyLinkedList implements MyList {

	int size = 0;
	Node first;
	Node last;

	private static class Node {
		Object item;
		Node next;// ָ����һ���ڵ�
		Node prev;// ָ����һ���ڵ�

		Node(Node prev, Object element, Node next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}

	public boolean add(Object o) {
		linkLast(o);
		return true;

	}

	/**
	 * ��Ԫ�ز���ָ���±�
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017��2��21��
	 * @param index
	 * @param o
	 */
	public void add(int index, Object o) {
		checkPositionIndex(index);
		if (index == size) {
			linkLast(0);
		} else {
			LinkBefore(o, node(index));
		}

	}

	public void addFirst(Object o) {
		linkFirst(o);
	}

	public void addLast(Object o) {
		linkLast(o);
	}

	private void linkFirst(Object o) {
		final Node f = first;
		final Node newNode = new Node(null, o, f);
		first = newNode;
		if (first == null) {
			first = newNode;
		} else {
			f.prev = newNode;
		}
		size++;

	}

	void LinkBefore(Object o, Node succ) {
		final Node pred = succ.prev;
		final Node newNode = new Node(pred, o, succ);// �Ȱ��½��ڵ��ǰ��ָ�븳ֵ
		succ.prev = newNode;// �Ѳ���ĺ�һ���ڵ��ǰָ��ָ���½ڵ�
		if (pred == null) {
			first = newNode;// �������ڵ�ǰָ��Ϊ��,��Ϊͷָ��
		} else {
			pred.next = newNode;// ��ǰһ���ڵ�ĺ�ָ��ָ���½ڵ�;
		}
		size++;
	}

	/**
	 * ����ָ�뷵�ؽڵ���Ϣ
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017��2��21��
	 * @param index
	 * @return
	 */
	Node node(int index) {

		if (index < (size >> 1)) {// ����ڵ�������ǰ�벿��,
			Node x = first;// ��ͷָ���x;
			for (int i = 0; i < index; i++)
				// ����ǰ�벿�ֽڵ�,
				x = x.next;// Ѱ������index��ǰһ���ڵ�,����nextָ��
			return x;
		} else {
			Node x = last;
			for (int i = size - 1; i > index; i--)
				x = x.prev;
			return x;
		}
	}

	/**
	 * �������
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017��2��21��
	 * @param o
	 */
	private void linkLast(Object o) {
		final Node l = last;
		final Node newNode = new Node(l, o, null);
		last = newNode;
		if (last == null) {
			first = newNode;
		} else {
			l.next = newNode;
		}
		size++;

	}
 
	public Object get(int index) {
		checkElementIndex(index);
		return node(index).item;

	}

	/**
	 * ���Ԫ���Ǽ��
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017��2��21��
	 * @param index
	 */
	private void checkElementIndex(int index) {
		if (index >= 0 && index < size) {

		} else {
			throw new IndexOutOfBoundsException();
		}

	}

	/**
	 * ���Ԫ��ʱ���ָ��
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017��2��21��
	 * @param index
	 */
	private void checkPositionIndex(int index) {
		if (index >= 0 && index <= size)
			throw new IndexOutOfBoundsException();
	}

	public boolean reomve(Object o) {

		if (o == null) {
			for (Node x = first; x != null; x = x.next) {
				if (x.item == null) {
					unlink(x);
					return true;
				}
			}

		} else {
			for (Node x = first; x != null; x = x.next) {
				if (o.equals(x.item)) {
					unlink(x);
					return true;
				}
			}

		}

		return false;

	}

	@Override
	public Object remove(int index) {
		checkElementIndex(index);
		return unlink(node(index));

	}

	private Object unlink(Node x) {

		final Object element = x.item;
		final Node next = x.next;
		final Node prev = x.prev;

		if (prev == null) {
			first = next;
		} else {
			prev.next = next;
			x.prev = null;
		}

		if (next == null) {
			last = prev;
		} else {
			next.prev = prev;
			x.next = null;
		}

		x.item = null;
		size--;
		return element;

	}

	public Object removeFirst() {

		final Node f = first;
		if (f == null) {
			throw new NoSuchElementException();
		}

		return unlinkFirst(f);

	}

	private Object unlinkFirst(Node f) {

		final Object element = f.item;
		final Node next = f.next;
		f.item = null;
		f.next = null;
		first = next;
		if (next == null) {
			last = null;
		} else {
			next.prev = null;
		}
		size--;

		return element;
	}

	public Object removeLast() {
		Node l = last;
		if (l == null) {
			throw new NoSuchElementException();
		}
		return unLinkLast(l);
	}

	private Object unLinkLast(Node l) {
		final Object element = l.item;
		final Node prev = l.prev;
		last = prev;
		l.item = null;
		l.prev = null;
		if (prev == null) {
			first = null;
		} else {
			prev.next = null;
		}
		size--;

		return element;
	}

	public int size() {

		return size;
	}

}
