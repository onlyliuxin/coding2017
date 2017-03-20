package week01.basic;

public class MyLinkedList implements List {

	private Node head;
	private int size;

	public void add(Object o) {
		// 空链表
		if (head == null) {
			head = new Node();
			head.data = o;
			head.next = null;
		} else {
			Node p = head;
			while (p.next != null) {
				p = p.next;
			}

			Node target = new Node();
			target.data = o;
			target.next = null;
			p.next = target;
		}
		size++;
	}

	public void add(int index, Object o) {
		// index 是否合法
		checkPositionIndex(index);
		if (head == null) {
			head = new Node();
			head.data = o;
			head.next = null;
		} else {
			if (index == 0) {
				addFirst(o);
			} else if (index == size) {
				addLast(o);
			} else {
				Node p = new Node();
				Node p1 = head;
				for (int i = 0; i < index - 1; i++) {
					p1 = p1.next;
				}
				p.data = o;
				p.next = p1.next;
				p1.next = p;

				size++;
			}
		}
	}

	public Object get(int index) {
		checkElementIndex(index);
		Node p = head;
		for (int i = 0; i < index; i++) {
			p = p.next;
		}
		return p.data;
	}

	public Object remove(int index) {
		checkElementRemove();
		checkElementIndex(index);

		Object removeObject = null;
		if (index == 0) {
			removeObject = removeFirst();
		} else if (index == (size - 1)) {
			removeObject = removeLast();
		} else {
			Node p = head;
			for (int i = 1; i < index; i++) {
				p = p.next;
			}
			removeObject = p.next.data;
			p.next = p.next.next;
			size--;
		}
		return removeObject;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		if (head == null) {
			head = new Node();
			head.data = o;
			head.next = null;
		} else {
			Node p = new Node();
			p.data = o;
			p.next = head;
			head = p;
		}
		size++;
	}

	public void addLast(Object o) {
		add(o);
	}

	public Object removeFirst() {
		checkElementRemove();
		Object removeObject = head.data;
		head = head.next;
		size--;
		return removeObject;
	}

	public Object removeLast() {
		checkElementRemove();

		Object removeObject = null;

		if (size == 1) {
			removeObject = head.data;
			head = head.next;
		} else {
			Node p = head;
			for (int i = 0; i < size; i++) {
				if (p.next.next == null) {
					removeObject = p.next;
					p.next = null;
					break;
				} else {
					p = p.next;
				}
			}
		}
		size--;
		return removeObject;
	}

	private boolean isEmpty() {
		return size == 0;
	}

	private void checkElementRemove() {
		if (isEmpty()) {
			throw new NullPointerException("The list is empty.");
		}
	}

	private void checkElementIndex(int index) {
		if (!isElementIndex(index)) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ size);
		}
	}

	private boolean isElementIndex(int index) {
		return index >= 0 && index < size;
	}

	private void checkPositionIndex(int index) {
		if (!isPositionIndex(index)) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ size);
		}
	}

	private boolean isPositionIndex(int index) {
		return index >= 0 && index <= size;
	}

	public Iterator iterator() {
		return new MyLinkedListIterator(this);
	}

	private class MyLinkedListIterator implements Iterator {
		private MyLinkedList list = null;
		private int position = 0;

		private MyLinkedListIterator(MyLinkedList list) {
			this.list = list;
		}

		@Override
		public boolean hasNext() {
			if ((position + 1) > size()) {
				return false;
			}
			return true;
		}

		@Override
		public Object next() {
			return list.get(position++);
		}
	}

	private static class Node {
		Object data;
		Node next;
	}

	@Override
	public String toString() {
		String elementStr = "";
		Node p = head;
		while (p != null) {
			elementStr += p.data + ",";
			p = p.next;
		}

		return "MyLinkedList: { size=" + size + ", elementData=" + "["
				+ elementStr.substring(0, elementStr.length() - 1) + "]" + " }";
	}

}
