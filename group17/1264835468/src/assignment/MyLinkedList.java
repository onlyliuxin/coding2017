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
		if (size == 0) {
			return "[]";
		}
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("[");
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

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		if (head == null || head.next == null) {
			return;
		}
		Node<E> node1 = head;
		Node<E> node2 = head.next;
		head.next = null;
		Node<E> node3 = node2.next;
		while (node3 != null) {
			node2.next = node1;
			node1 = node2;
			node2 = node3;
			node3 = node3.next;
		}
		head = node2;
		head.next = node1;
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 * 
	 */
	public void removeFirstHalf() {
		head = movePtrTo((size + 1) / 2);
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		rangeCheck(i + length - 1);
		if (i == 0) {
			head = movePtrTo(i + length - 1).next;
			return;
		}
		Node<E> node = movePtrTo(i - 1);
		Node<E> newNext = movePtrTo(i + length - 1).next;
		node.next = newNext;
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(MyLinkedList<Integer> list) {

		return null;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(MyLinkedList list) {

	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {

	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {

	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public MyLinkedList intersection(MyLinkedList list) {
		return null;
	}

	public static void main(String[] args) {
		MyLinkedList<Integer> linkedList = new MyLinkedList<>();
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		System.out.println(linkedList);
		linkedList.remove(0, 3);
		System.out.println(linkedList);
	}
}