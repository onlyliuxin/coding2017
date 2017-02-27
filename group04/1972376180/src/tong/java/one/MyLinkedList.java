package tong.java.one;

/**
 * 自定义LinkedList
 * 
 * @author 仝闯
 *
 */
public class MyLinkedList {
	private Node head;
	private int size;

	// 默认在链表的结尾添加元素
	public void add(Object o) {
		Node newNode = new Node(o);
		if (size == 0) {
			head = newNode;
		} else if (size == 1) {
			Node oldHead = head;
			head = newNode;
			head.next = oldHead;
		} else {
			getNode(size - 1).next = newNode;
		}
		size++;
	}

	// 在指定索引出添加元素
	public void add(int index, Object o) {
		Node newNode = new Node(o);
		if (size == 0) {
			head = newNode;
		} else if (size == 1) {
			Node oldHead = head;
			head = newNode;
			head.next = oldHead;
		} else {
			if (index == 0) {
				Node oldHead = head;
				head = newNode;
				head.next = oldHead;
			} else if (index == size - 1) {
				getNode(size - 1).next = newNode;
			} else {
				for (int i = 1; i < index; i++) {
					getNode(index - 1).next = newNode;
					newNode.next = getNode(index);
				}
			}
		}
		size++;
	}

	// 添加元素到首位
	public void addFirst(Object o) {
		Node oldHead = head;
		head = new Node(o);
		head.next = oldHead;
		size++;
	}

	// 获取指定索引处的元素
	public Object get(int index) {
		return getNode(index).data;
	}

	private Node getNode(int index) {
		Node x = head;
		if (index == 0) {
			return head;
		} else {
			for (int i = 0; i < index; i++) {
				x = x.next;
			}
			return x;
		}

	}

	// 删除指定索引处的元素
	public Object remove(int index) {
		if (index < 0 || index > size - 1) {
			throw new RuntimeException();
		} else {
			if (0 < index && index < size - 1) {
				getNode(index - 1).next = getNode(index + 1);
				size--;
				return getNode(index);
			} else if (index == 0) {
				Node removeNode = head;
				removeNode.next = null;
				head = getNode(1);
				size--;
				return removeNode;
			} else {
				getNode(index - 1).next = null;
				size--;
				return getNode(index);
			}
		}

	}

	// 删除首位元素
	public Object removeFirst() {
		return remove(0);
	}

	// 删除末位元素
	public Object removeLast() {
		return remove(size - 1);
	}

	public int size() {
		return size;
	}

	class Node {
		Object data;
		Node next;

		public Node(Object data) {
			this.data = data;
		}
	}

}
