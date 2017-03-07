package com.stackwei.DataStructure;

/**
 * 
 * @author stackwei -2017.2.25
 *
 */
public class LinkedList implements List {

	private Node head = null;
	private Node last = null;
	private int size = 0;

	private static class Node {
		Object item;
		Node prev;
		Node next;

		public Node(Node prev, Object item, Node next) {
			this.prev = prev;
			this.item = item;
			this.next = next;
		}
	}

	@Override
	public void add(Object element) {
		addLast(element);
	}

	@Override
	public void add(int index, Object element) {
		if (index < 0 || index > size) {
			System.out.println("操作无效,越界了");
			return;
		}
		if (index == 0) {
			addFirst(element);
			return;
		}
		if (index == size) {
			addLast(element);
			return;
		}
		Node indexNode = node(index);
		Node newNode = new Node(indexNode.prev, element, indexNode);
		indexNode.prev.next = newNode;
		indexNode.prev = newNode;
		size++;
	}

	@Override
	public Object get(int index) {
		if (index < 0 || index >= size) {
			System.out.println("查询无效,越界了");
			return null;
		}
		if (index == 0) {
			return head.item;
		}
		return node(index).item;
	}

	@Override
	public Object remove(int index) {
		if (index < 0 || index > size) {
			System.out.println("是空的，无法删除");
			return null;
		}
		if (index == 0) {
			return removeFirst();
		}
		if (index == size - 1) {
			return removeLast();
		}
		Node x = node(index);
		final Object element = x.item;
		final Node next = x.next;
		final Node prev = x.prev;

		if (prev == null) {
			head = next;
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

	@Override
	public int size() {
		return size;
	}

	private void addFirst(Object element) {
		final Node f = head;
		Node newNode = new Node(null, element, f);
		head = newNode;
		if (f == null)
			last = newNode;
		else
			f.prev = newNode;
		size++;
	}

	public void addLast(Object element) {
		if (head == null) {
			addFirst(element);
		} else {
			Node newNode = new Node(last, element, null);
			last.next = newNode;
			last = newNode;
			size++;
		}
	}

	public Object removeFirst() {
		if (head == null) {
			System.out.println("是空的，无法删除");
			return null;
		} else {
			Node x = head;
			Node next = head.next;
			Object element = x.item;
			x.item = null;
			x.next = null;
			head = next;
			if (next == null)
				last = null;
			else
				x.prev = null;
			size--;
			return element;
		}
	}

	public Object removeLast() {
		if (last == null) {
			System.out.println("是空的，无法删除");
			return null;
		} else {
			final Node l = last;
			final Object element = l.item;
			final Node p = l.prev;
			l.item = null;
			l.prev = null;
			last = p;
			if (p == null)
				head = null;
			else
				p.next = null;
			size--;
			return element;
		}
	}

	Node node(int index) {
		if (index < (size >> 1)) {
			Node x = head;
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

	/**
	 * 测试用例
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.add(0, "xxx");
		ll.add(1, 111);
		System.out.println(ll.size());
		System.out.println(ll.get(2));

	}
}