public class LinkedList implements List {
	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList();
		linkedList.add(0);

		System.out.println(linkedList.get(0));

	}

	private Node first;

	private Node last;

	private int size = 0;

	public void add(Object o) {
		Node oldLast = last;
		last = new Node();
		last.data = o;
		last.next = null;
		if (oldLast != null) {
			oldLast.next = last;
		}
		if (first == null) {
			first = last;
		}
		size++;
	}

	public void add(int index, Object o) {
		if (index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ size);
		}
		if (index == 0) {
			addFirst(o);
			return;
		}
		if (index == size) {
			addLast(o);
			return;
		}
		Node curserNode = cursor(index - 1);
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = curserNode.next;
		curserNode.next = newNode;
		size++;
	}

	public Object get(int index) {
		return cursor(index).data;
	}

	public Object remove(int index) {
		Node node = cursor(index - 1).next;
		cursor(index - 1).next = node.next;
		node.next = null;
		return node.data;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node newNode = new Node();
		newNode.data = o;
		if (first == null) {
			first = newNode;
		} else {
			newNode.next = first;
			first = newNode;
		}
		if (last == null) {
			last = first;
		}
		size++;
	}

	public void addLast(Object o) {
		Node newNode = new Node();
		newNode.data = o;
		if (last == null) {
			last = newNode;
		} else {
			last.next = newNode;
			last = newNode;
		}
		if (first == null) {
			first = last;
		}
		size++;
	}

	public Object removeFirst() {
		Node node = first;
		Node newFirst = first.next;
		first.next = null;
		first = newFirst;
		size--;
		return node.data;
	}

	public Object removeLast() {
		Node node = last;
		Node newLast = cursor(size - 1);
		newLast.next = null;
		last = newLast;
		size--;
		return node.data;
	}

	public Iterator iterator() {
		return null;
	}

	private Node cursor(int index) {
		Node curserNode = first;
		for (int i = 0; i < index; i++) {
			curserNode = curserNode.next;
		}
		return curserNode;
	}

	private static class Node {
		Object data;
		Node next;

	}
}
