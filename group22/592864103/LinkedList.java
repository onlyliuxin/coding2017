public class LinkedList implements List {

	private Node head;

	public void add(Object o) {
		if (o == null) {
			System.out.println("传入对象不能为空！");
		} else if (head == null) {
			head.data = o;
			head.next = null;
		} else {
			Node cursor = head;
			for (int i = 0; i < size(); i++) {
				cursor = cursor.next;
			}
			cursor.data = o;
			cursor.next = null;
		}
	}

	public void add(int index, Object o) {
		if (o == null) {
			System.out.println("传入对象不能为空！");
			return;
		}
		if ((index > size())) {
			System.out.println("超出数组长度！");
			return;
		} else if (index == 0) {
			Node temp = new Node();
			temp.data = head.data;
			temp.next = head.next;
			head.data = o;
			head.next = temp;
		} else {
			Node cursor = head;
			Node temp = new Node();
			for (int i = 0; i < index - 1; i++) {
				cursor = cursor.next;
			}
			temp.data = o;
			temp.next = cursor.next;
			cursor.next = temp;
		}
	}

	public Object get(int index) {
		if ((index > size())) {
			System.out.println("超出数组长度！");
			return null;
		} else {
			Node cursor = head;
			for (int i = 0; i < index; i++) {
				cursor = cursor.next;
			}
			return cursor;
		}
	}

	public Object remove(int index) {
		if ((index > size())) {
			System.out.println("超出数组长度！");
			return null;
		} else if (index == 0) {
			Node temp = new Node();
			temp.data = head.data;
			temp.next = head.next;
			head = head.next;
			return temp;
		} else {
			Node cursor = head;
			for (int i = 0; i < index - 1; i++) {
				cursor = cursor.next;
			}
			Node temp = new Node();
			Node target = cursor.next;
			temp.data = target.data;
			temp.next = target.next;
			cursor.next = target.next;
			return temp;
		}
	}


	public int size() {
		Node cursor = head;
		int size = 0;
		while (cursor != null) {
			cursor = cursor.next;
			size++;
		}
		return size;
	}

	public void addFirst(Object o) {
		Node temp = new Node();
		temp.data = head.data;
		temp.next = head.next;
		head.data = o;
		head.next = temp;
	}

	public void addLast(Object o) {
		if (o == null) {
			System.out.println("传入对象不能为空！");
		} else if (head == null) {
			head.data = o;
			head.next = null;
		} else {
			Node cursor = head;
			for (int i = 0; i < size(); i++) {
				cursor = cursor.next;
			}
			cursor.data = o;
			cursor.next = null;
		}
	}

	public Object removeFirst() {
		Node temp = new Node();
		temp.data = head.data;
		temp.next = head.next;
		head = head.next;
		return temp;
	}

	public Object removeLast() {
		Node temp = new Node();
		Node cursor = head;
		for (int i = 0; i < size() - 2; i++) {
			cursor = cursor.next;
		}
		Node last = cursor.next;
		temp.data = last.data;
		temp.next = null;
		cursor.next = null;
		return temp;
	}

	public Iterator iterator() {
		return new LinkedListIterator(this);
	}
	public class LinkedListIterator implements Iterator{
		int pos = 0;
		LinkedList list = new LinkedList();
		LinkedListIterator(LinkedList list){
			this.list = list;
		}
		@Override
		public boolean hasNext() {
			if (pos<list.size()){
				return true;
			}else
				return false;
		}

		@Override
		public Object next() {
			pos++;
			return list.get(pos);
		}
	}

	private static class Node {
		Object data;
		Node next;

	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse() {

	}

	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
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
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 *
	 * @param list
	 */
	public static int[] getElements(LinkedList list) {
		return null;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素
	 *
	 * @param list
	 */

	public void subtract(LinkedList list) {

	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {

	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
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
