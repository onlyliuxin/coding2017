package assignment;

//
public class MyLinkedList {
	private Node head;
	private int size;

	public MyLinkedList() {
		size = 0;
	}

	public void add(Object o) {
		if (head == null)
			addFirst(o);
		else
			addLast(o);
	}

	public void addFirst(Object o) {
		Node oldFirst = head;
		head = new Node(o, oldFirst);
		size++;
	}

	public void addLast(Object o) {
		if (head == null) {
			addFirst(o);
		}
		else {
			Node oldLast = movePtrTo(size - 1);
			oldLast.next = new Node(o, null);
			size++;
		}

	}

	public void add(int index, Object o) {
		rangeCheck(index - 1);
		if (index == 0) {
			addFirst(o);
			return;
		}
		Node temp = movePtrTo(index - 1);
		Node oldNext = temp.next;
		Node newNext = new Node(o, oldNext);
		temp.next = newNext;
		size++;
	}

	public Object remove(int index) {
		rangeCheck(index);
		Object data;
		if (index == 0) {
			data = head.data;
			head = head.next;
		}
		else {
			Node pre = movePtrTo(index - 1);
			Node target = pre.next;
			pre.next = target.next;
			data = target.data;
		}
		size--;
		return data;
	}

	public Object get(int index) {
		return movePtrTo(index).data;
	}

	public int size() {
		return size;
	}

	private Node movePtrTo(int index) {
		Node resultNode = head;
		for (int i = 0; i < index; i++) {
			resultNode = resultNode.next;
		}
		return resultNode;
	}

	private void rangeCheck(int index) {
		if (index >= size) {
			throw new NoElementException("index:" + index);
		}
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder('[');
		Node temp = head;
		while (temp != null) {
			stringBuilder.append(String.valueOf(temp.toString()) + ",");
			temp = temp.next;
		}
		stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
		stringBuilder.append(']');
		return stringBuilder.toString();
	}

	private static class Node {
		private Object data;
		private Node next;

		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}

		@Override
		public String toString() {
			return data.toString();
		}
	}
}