package list;

public class LinkedList implements List {

	public Node getFirst() {
		return first;
	}

	public Node getLast() {
		return last;
	}

	private int size;
	private Node first;
	private Node last;

	@Override
	public void add(int paramInt, Object paramE) {
		checkPositionIndex(paramInt);
		if (paramInt == size) {
			linkLast(paramE);
		} else {
			linkBefore(paramE, node(paramInt));
		}

	}

	// 寻找到需要插入的那个node
	Node node(int paramInt) {
		if (paramInt < size >> 1) {
			Node x = first;
			for (int i = 0; i < paramInt; i++) {
				x = x.next;
			}
			return x;
		} else {
			Node x = last;
			for (int i = size - 1; i > paramInt; i--) {
				x = x.prev;
			}
			return x;
		}
	}

	private void linkBefore(Object o, Node node) {
		Node pred = node.prev;
		Node newNode = new Node(pred, o, node);
		node.prev = newNode;
		if (pred == null) {
			first = newNode;
		} else {
			pred.next = newNode;
		}

	}

	private void checkPositionIndex(int paramInt) {
		if (paramInt < 0 || paramInt > size) {
			throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + size);
		}
	}

	@Override
	public boolean add(Object paramE) {
		linkLast(paramE);
		return true;
	}

	private void linkLast(Object paramE) {
		Node l = last;
		Node newNode = new Node(l, paramE, null);
		last = newNode;
		if (l == null) {
			first = newNode;
		} else {
			l.next = newNode;
		}
		size++;
	}

	@Override
	public void clear() {
		for (Node x = first; x != null;) {
			Node next = x.next;
			x.next = null;
			x.prev = null;
			x.item = null;
			x = next;
		}
		first = last = null;
		size = 0;
	}

	@Override
	public boolean contains(Object paramObject) {
		return false;
	}

	@Override
	public Object get(int paramInt) {
		checkPositionIndex(paramInt);
		return node(paramInt).item;
	}

	@Override
	public int indexOf(Object paramObject) {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}

	@Override
	public Iterator iterator() {
		return new LinkedIterator();
	}

	@Override
	public Object remove(int paramInt) {
		checkPositionIndex(paramInt);
		return unlink(node(paramInt));
	}

	private Object unlink(Node node) {
		Object item = node.item;
		Node prev = node.prev;
		Node next = node.next;
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
		node.item = null;
		size--;

		return item;

	}

	@Override
	public boolean remove(Object paramObject) {
		return false;
	}

	@Override
	public Object set(int paramInt, Object paramE) {
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	public static class Node {
		public Object item;
		public Node next;
		public Node prev;

		public Node(Node prev, Object item, Node next) {
			this.item = item;
			this.next = next;
			this.prev = prev;
		}

	}

	private class LinkedIterator implements Iterator {

		int cursor = 0;
		int lastRet = -1;

		@Override
		public boolean hasNext() {
			return cursor < size();
		}

		@Override
		public Object next() {
			int i = cursor;
			Object node = get(i);
			lastRet = i;
			cursor = i + 1;
			return node;
		}

		@Override
		public void remove() {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			LinkedList.this.remove(lastRet);
			if (lastRet < cursor) {
				cursor--;
			}
			lastRet = -1;// 防止对一个数据多次remove操作

		}

	}

}
