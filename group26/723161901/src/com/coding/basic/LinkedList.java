package com.coding.basic;


public class LinkedList implements List {
	private Node head;
	private Node last;
	private int size;

	public LinkedList() {
	}

	public void add(Object obj) {
		addLast(obj);
	}

	public void add(int index, Object o) {
		if (index == size) {
			addLast(o);
		} else {
			linkBefor(o, node(index));
		}
	}

	public Object get(int index) {
		// 双重循环链表 size>>1 判断距离哪边近
		// 从第一个开始找
		return node(index).data;
	}

	public Object remove(int index) {
		return unlink(node(index));
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
        final Node f = head;
        final Node newNode = new Node(null, o, f);
        head = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;		
	}

	private void addLast(Object obj) {
		Node tempNode = last;
		Node newNode = new Node(tempNode, obj, null);
		last = newNode;
		if (tempNode == null) {
			head = newNode;
		} else {
			tempNode.next = newNode;
		}
		size++;
	}

	public Object removeFirst() {
		Node h = head;
        Object element = h.data;
        Node next = h.next;
        h.data = null;
        h.next = null; // help GC
        head = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;		
		return element;
	}

	public Object removeLast() {
		Node l = last;
        Object element = l.data;
        Node prev = l.prev;
        l.data = null;
        l.prev = null; 
        last = prev;
        if (prev == null)
            head = null;
        else
            prev.next = null;
        size--;
        return element;		
	}

	public Iterator iterator() {
		return null;
	}

	private void linkBefor(Object o, Node node) {
		Node pred = node.prev;
		Node newNode = new Node(pred, o, node);
		node.prev = newNode;
		if (pred == null) {
			head = newNode;
		} else {
			pred.next = newNode;
		}
		size++;
	}

	private Object unlink(Node node) {
		Node prev = node.prev;
		Object obj = node.data;
		Node next = node.next;
		if (prev == null) {
			head = next;
		} else {
			node.prev = null;
			prev.next = next;
		}

		if (next == null) {
			last = prev;
		} else {
			next.prev = prev;
			node.next = null;
		}
		node.data = null;
		size--;
		return obj;
	}

	Node node(int index) {
		if (index < (size >> 1)) {
			Node x = head;
			for (int i = 0; i < index; i++) {
				x = x.next;
			}
			return x;
		} else {
			Node x = last;
			for (int i = size - 1; i > index; i--) {
				x = x.prev;
			}
			return x;
		}
	}

	private static class Node {
		Object data;
		Node prev;
		Node next;

		Node(Node prev, Object data, Node next) {
			this.prev = prev;
			this.next = next;
			this.data = data;
		}
	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {

	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf() {

	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {

	}

	/**
	 * 假定当前链表和listB均包含已升序排列的整数 从当前链表中取出那些listB所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		return null;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在listB中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list) {

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
	public LinkedList intersection(LinkedList list) {
		return null;
	}
}
