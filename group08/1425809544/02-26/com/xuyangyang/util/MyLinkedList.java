package com;

import java.util.NoSuchElementException;

public class MyLinkedList implements MyList {

	int size = 0;
	Node first;
	Node last;

	private static class Node {
		Object item;
		Node next;// 指向下一个节点
		Node prev;// 指向上一个节点

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
	 * 把元素插入指定下标
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月21日
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
		final Node newNode = new Node(pred, o, succ);// 先把新建节点的前后指针赋值
		succ.prev = newNode;// 把插入的后一个节点的前指针指向新节点
		if (pred == null) {
			first = newNode;// 如果插入节点前指针为空,则为头指针
		} else {
			pred.next = newNode;// 把前一个节点的后指针指向新节点;
		}
		size++;
	}

	/**
	 * 根据指针返回节点信息
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月21日
	 * @param index
	 * @return
	 */
	Node node(int index) {

		if (index < (size >> 1)) {// 如果节点索引在前半部分,
			Node x = first;// 把头指针给x;
			for (int i = 0; i < index; i++)
				// 遍历前半部分节点,
				x = x.next;// 寻找索引index的前一个节点,它的next指针
			return x;
		} else {
			Node x = last;
			for (int i = size - 1; i > index; i--)
				x = x.prev;
			return x;
		}
	}

	/**
	 * 插入最后
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月21日
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
	 * 获得元素是检查
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月21日
	 * @param index
	 */
	private void checkElementIndex(int index) {
		if (index >= 0 && index < size) {

		} else {
			throw new IndexOutOfBoundsException();
		}

	}

	/**
	 * 添加元素时检查指针
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月21日
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
