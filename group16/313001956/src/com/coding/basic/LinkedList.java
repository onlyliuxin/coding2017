package com.coding.basic;

public class LinkedList implements List {

	private Node head;
	private int size;

	public LinkedList() {

	}

	public void add(Object o) {
		addLast(o);
	}

	public void add(int index, Object o) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("越界！");
		}
		if (head == null) {
			head = new Node();
			head.data = o;
		} else {

			Node n = getNodebyIndex(index);

			Node newnode = new Node();
			newnode.data = o;
			newnode.next = n.next;
			n.next = newnode;
		}
		size++;
	}

	public Object get(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("越界！");
		}
		if (head == null)
			return null;
		Node n = getNodebyIndex(index);
		return n.data;
	}

	public Object remove(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("越界！");
		}
		Node n = getNodebyIndex(index - 1);
		Object o = n.next.data;
		n.next = n.next.next;
		size--;
		return o;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		if (head == null) {
			head = new Node();
			head.data = o;
		} else {
			Node newnode = new Node();
			newnode.data = o;
			newnode.next = head;
			head = newnode;
		}
		size++;
	}

	public void addLast(Object o) {
		if (head == null) {
			head = new Node();
			head.data = o;
		} else {
			Node n = getNodebyIndex(size);
			Node newnode = new Node();
			newnode.data = o;
			n.next = newnode;
		}
		size++;
	}

	public Object removeFirst() {
		if (head == null)
			return null;

		head = head.next;
		size--;
		return head.data;
	}

	public Object removeLast() {
		if (head == null)
			return null;
		Node n = getNodebyIndex(size - 1);
		Object o = n.next.data;
		n.next = null;
		size--;
		return o;
	}

	public Iterator iterator() {
		return null;
	}

	private Node getNodebyIndex(int index) {
		int i = 0;
		Node n = head;
		while (i < index) {
			n = n.next;
			i++;
		}
		return n;
	}

	private static class Node {
		Object data;
		Node next;

		Object Getdata() {
			return data;
		}

		void Setdata(Object o) {
			data = o;
		}

		Node Getnext() {
			return next;
		}

		void Setnext(Node n) {
			next = n;
		}
	}
	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse(LinkedList list) {

		Node node = list.head.next;
		list.head.next = null;
		while (node != null) {
			Node temp = node.next;
			node.next = head.next;
			head.next = node;
			node = temp;
		}

	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 * 
	 */
	public void removeFirstHalf(LinkedList list) {
		int lsize = list.size();
		int semisize = lsize / 2;
		Node node = head.next;

		for (int i = 0; i < semisize; i++) {
			node.data = null;
			Node temp = node.next;
			node.next = null;
			node = temp;
		}
		head.next = node;
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 * @throws Exception
	 */
	public void remove(LinkedList list, int i, int length) {
		int lsize = list.size();
		if (i < 0 || i > lsize - 1 || (i + length > lsize - 1))
			throw new IndexOutOfBoundsException("越界！");
		Node nodebefore = list.head, node = list.head;
		for (int j = 0; j <= i + length; j++)
			if (j <= i) {
				node = node.next;
				if (j == i - 1)
					nodebefore = node;
			} else {
				Node temp = node.next;
				node.data = null;
				node.next = null;
				node = temp;
			}
		nodebefore.next = node;
	}

	/**
	 * 假定当前链表和listB均包含已升序排列的整数 从当前链表中取出那些listB所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList list, LinkedList listB) {
		ArrayList al = new ArrayList();
		int sizeB = listB.size();
		int index;
		Object o;
		for (int i = 0; i < sizeB; i++) {
			index = Integer.parseInt(listB.get(i).toString());
			o = list.get(index);
			al.add(o);
		}
		int[] array = new int[al.size()];
		for (int i = 0; i < al.size(); i++)
			array[i] = Integer.parseInt(al.get(i).toString());
		return array;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在listB中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list, LinkedList listB) {
		int sizeB = listB.size();
		int index;
		int end = 0;
		Node node = list.head;
		for (int i = 0; i < sizeB; i++) {
			index = Integer.parseInt(listB.get(i).toString());
			for (int j = end; j < index; j++) {
				if (j != index - 1)
					node = node.next;
				else {
					Node temp = node.next.next;
					node.next.data = null;
					node.next.next = null;
					node.next = temp;
					node = temp;
					end = j;
				}
			}
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素 （使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		Object o = head.data;
		Node prenode = null;
		for (Node node = head.next; node != null; node = node.next) {
			if (o.equals(node.data)) {
				Node temp = node.next;
				node.data = null;
				node.next = null;
				prenode.next = temp;
				node = prenode;
			} else {
				o = node.data;
				prenode = node;
			}
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法， 删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {
		Node prenode = null;
		for (Node node = head.next; node != null; node = node.next) {
			int value = Integer.parseInt(node.data.toString());
			if (value > min) {
				if (value >= max) {
					break;
				} else {
					Node temp = node.next;
					node.data = null;
					node.next = null;
					prenode.next = temp;
					node = prenode;
				}

			} else {
				prenode = node;
			}
		}
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public LinkedList intersection(LinkedList listA, LinkedList listB) {
		LinkedList listC = new LinkedList();
		Node nodeBaf = listB.head;
		for (Node nodeA = listA.head; nodeA != null; nodeA = nodeA.next) {
			for (Node nodeB = nodeBaf; nodeB != null; nodeB = nodeB.next) {
				if (nodeA.data.equals(nodeB.data)) {
					listC.add(nodeA.data);
					nodeBaf = nodeB.next;
					break;
				}
			}
		}

		return null;
	}

}

