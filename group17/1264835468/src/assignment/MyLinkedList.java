package assignment;

public class MyLinkedList<E> implements List<E>, Iterable<E> {
	private Node<E> head;
	private int size;

	public MyLinkedList() {
		size = 0;
	}

	public void add(E o) {
		if (head == null)
			addFirst(o);
		else
			addLast(o);
	}

	public void addFirst(E o) {
		Node<E> oldFirst = head;
		head = new Node<>(o, oldFirst);
		size++;
	}

	public void addLast(E o) {
		if (head == null) {
			addFirst(o);
		}
		else {
			Node<E> oldLast = movePtrTo(size - 1);
			oldLast.next = new Node<>(o, null);
			size++;
		}

	}

	public void add(int index, E o) {
		if (index > size || index < 0) {
			throw new IllegalArgumentException("index:" + index);
		}
		if (index == 0) {
			addFirst(o);
			return;
		}
		Node<E> temp = movePtrTo(index - 1);
		Node<E> oldNext = temp.next;
		Node<E> newNext = new Node<>(o, oldNext);
		temp.next = newNext;
		size++;
	}

	public E remove(int index) {
		rangeCheck(index);
		E data;
		if (index == 0) {
			data = head.data;
			head = head.next;
		}
		else {
			Node<E> pre = movePtrTo(index - 1);
			Node<E> target = pre.next;
			pre.next = target.next;
			data = target.data;
		}
		size--;
		return data;
	}

	public E get(int index) {
		rangeCheck(index);
		return movePtrTo(index).data;
	}

	public int size() {
		return size;
	}

	private Node<E> movePtrTo(int index) {
		Node<E> resultNode = head;
		for (int i = 0; i < index; i++) {
			resultNode = resultNode.next;
		}
		return resultNode;
	}

	private void rangeCheck(int index) {
		if (index >= size) {
			throw new NoSuchElementException("index:" + index);
		}
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder('[');
		Node<E> temp = head;
		while (temp != null) {
			stringBuilder.append(String.valueOf(temp.toString()) + ",");
			temp = temp.next;
		}
		stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
		stringBuilder.append(']');
		return stringBuilder.toString();
	}

	private static class Node<T> {
		private T data;
		private Node<T> next;

		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}

		@Override
		public String toString() {
			return data.toString();
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<E> {
		Node<E> currentNode;

		public ListIterator() {
			currentNode = head;
		}

		@Override
		public boolean hasNext() {
			return currentNode.next != null;
		}

		@Override
		public E next() {
			Node<E> temp = currentNode;
			currentNode = currentNode.next;
			return temp.data;
		}

	}
}