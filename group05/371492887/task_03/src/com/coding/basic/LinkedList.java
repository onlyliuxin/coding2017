package com.coding.basic;

public class LinkedList implements List {

	private Node first;

	private Node last;

	int size = 0;

	public LinkedList() {

	}

	/**
	 * 遍历链表
	 * 
	 * @param index
	 * @return
	 */
	public Node node(int index) {
		if (index < (size >> 1)) { // 这个处理很机智啊
			Node x = first;
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

	public Object get(int index) {
		checkElementIndex(index);
		return node(index).data;
	}

	public Object remove(int index) {
		checkElementIndex(index);
		return (unlink(node(index)));
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		final Node f = first;
		final Node newNode = new Node(null, o, f);
		first = newNode;

		if (f == null) {
			last = newNode;
		} else {
			f.prev = newNode;
		}
		size++;// 别忘了
	}

	public void addLast(Object o) {
		final Node l = last;
		final Node newNode = new Node(l, o, null);
		last = newNode;
		if (l == null) {
			first = newNode;
		} else {
			l.next = newNode;
		}
		size++;
	}

	public void addBefore(Object o, Node succ) {
		final Node pred = succ.prev;
		final Node newNode = new Node(pred, o, succ);
		succ.prev = newNode;
		if (pred == null) {
			first = newNode;
		} else {
			pred.next = newNode;
		}
		size++;
	}

	public void addAfter(Object o, Node succ) {
		final Node nextd = succ.next;
		final Node newNode = new Node(succ, o, nextd);
		succ.next = newNode;
		if (nextd == null) {
			last = newNode;
		} else {
			nextd.prev = newNode;
		}
		size++;
	}

	private Object unlink(Node n) {

		Object data = n.data;
		final Node prev = n.prev;
		final Node next = n.next;

		/* 自己写的就是这么bug */
		// if(n.prev==null){
		// first=n.next;
		// }else if(n.next==null){
		// last=n.prev;
		// }else {
		// n.prev.next=n.next;
		// n.next.prev=n.prev;
		// }
		//
		// n=null;

		if (prev == null) {
			first = next;
			// first.prev = null;// 源码没有这行代码 why？ 答：当 data, prev,
			// 报NullPointException
			// next全为null时，改node为null？
		} else {
			prev.next = next;
			n.prev = null;
		}

		if (next == null) {
			last = prev;
			// last.next = null;// 源码没有这行代码 why？ 报NullPointException
		} else {
			next.prev = prev;
			n.next = null;
		}

		n.data = null; // 为什么不直接 node=null，而是依次将 data, prev, next 赋为null
		size--; // 注意
		return data;
	}

	public Object removeFirst() {// 为啥源码中还需要传参 node？

		final Object data = first.data;
		final Node next = first.next;
		first = next;

		if (next == null)
			first = null;
		else
			next.prev = null;

		size--;
		return data;
	}

	public Object removeLast() {// 为啥源码中还需要传参 node？

		final Object data = last.data;
		final Node prev = last.prev;
		last = prev;

		if (prev == null)
			last = null;
		else
			prev.next = null;

		size--;
		return data;
	}

	private static class Node {
		Object data;
		Node next;
		Node prev;

		Node(Node prev, Object data, Node next) {
			this.prev = prev;
			this.data = data;
			this.next = next;
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public boolean add(Object o) {
		addLast(o);
		return true;
	}

	@Override
	public boolean add(int index, Object o) {
		checkPositionIndex(index);
		if (index == size)
			addLast(o);
		else
			addBefore(o, node(index));
		return true;
	}

	@Override
	public boolean addAll(Object[] o) {
		// add(size,o);
		// return true;

		return add(size, o);// 优雅
	}

	@Override
	public boolean addAll(int index, Object[] o) {// 挺有难度
		checkPositionIndex(index);

		int numNew = o.length;
		if (numNew == 0)
			return false;

		Node pred, succ; // unlink节点的前一节点及unlink节点
		if (index == size) {
			succ = null;
			pred = last;
		} else {
			succ = node(index);
			pred = succ.prev;
		}

		for (Object data : o) {
			Node newNode = new Node(pred, data, null);// 将新节点link到前一节点
			if (pred == null)
				first = newNode;
			else
				pred.next = newNode;
			pred = newNode;
			// size++;
		}

		if (succ == null) {// link上succ
			last = pred;
		} else {
			// last = succ; // 源码未处理 why？ succ又不是最后一个， 当然不处理了。
			// succ.next = null; // 源码未处理 why？
			pred.next = succ;
			succ.prev = pred;
			// size++;
		}

		size += numNew;

		return true;
	}

	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + size;
	}

	private void checkElementIndex(int index) {
		if (!isElementIndex(index))
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	private void checkPositionIndex(int index) {
		if (!isElementIndex(index))
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	private boolean isElementIndex(int index) {
		return index >= 0 && index < size;
	}

	private boolean isPositionIndex(int index) {
		return index >= 0 && index <= size; // postion可以添加到最后
	}

	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);
		if (index == -1)
			throw new IndexOutOfBoundsException("对象不存在" + o);
		Node node = node(index);
		return unlink(node) != null;
	}

	public boolean remove(Node node) {

		return unlink(node) != null;

	}

	@Override
	public boolean removeAll(List list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object set(int index, Object o) {
		checkElementIndex(index);
		Node node = node(index);
		Object oldValue = node.data;
		node.data = o;
		return oldValue;
	}

	@Override
	public int indexOf(Object o) {
		int index = 0;
		if (o == null) {
			for (Node x = first; x != null; x = x.next) { // 新的循环方式
				if (x.data == null)
					return index;
				index++;
			}
		} else {
			for (Node x = first; x != null; x = x.next) { // 新的循环方式
				if (o.equals(x.data))
					return index;
				index++;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		int index = size - 1;
		if (o == null) {
			for (Node x = last; x != null; x = x.prev) { // 新的循环方式
				if (x.data == null)
					return index;
				index--;
			}
		} else {
			for (Node x = last; x != null; x = x.prev) { // 新的循环方式
				if (o.equals(x.data))
					return index;
				index--;
			}
		}
		return -1;
	}

	@Override
	public Object[] toArray() {
		Object[] elementData = new Object[size];
		int i = 0;
		for (Node x = first; x != null; x = x.next) {
			elementData[i++] = x.data;
		}
		return elementData;
	}

	@Override
	public void clear() {
		// bug炸了
		// for(Node x=first;x!=null;x=x.next){
		// x=null;
		// }

		for (Node x = first; x != null;) {
			Node next = x.next;
			x.prev = null;
			x.data = null;
			x.next = null;
			x = next;
		}
		size = 0;
		first = last = null;
	}

	public Iterator iterator() {
		return new Itr();
	}

	private class Itr implements Iterator {
		private Node lastRetured;
		private Node next;
		int cursor;

		@Override
		public boolean hasNext() {
			return cursor < size;
		}

		@Override
		public Object next() {
			checkElementIndex(cursor);
			next = node(cursor);
			lastRetured = next;
			next = next.next;
			cursor++;
			return lastRetured.data;
		}

	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 * 
	 * @throws Exception
	 */
	public void reverse() throws Exception {
		if (size == 0) {
			throw new Exception("该链表没有数据");
		}
		Node current;
		Node prev = null;
		Node next = null;
		for (current = first; current != null; current = next) {
			next = current.next;
			current.next = prev;
			current.prev = next;
			prev = current;
			if (next == null) { // 已到队列尾部，设置first
				last = first;
				first = current;
			}
		}
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 * 
	 * @throws Exception
	 */
	public void removeFirstHalf() throws Exception {
		if (size == 0) {
			throw new Exception("该链表没有数据");
		}
		int index = size >> 1;
		Node halfNode = node(index);
		Node next = first.next;
		for (Node clearNode = first; clearNode != halfNode; clearNode = next) {
			next = clearNode.next;
			clearNode.data = null;
			clearNode.prev = null;
			clearNode.next = null;
			size--;
		}
		first = halfNode;
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		checkElementIndex(i);
		int time = Math.min(length, size);
		if (i == 0) {
			for (int j = 0; j < time; j++) {
				unlink(first);
			}
		} else {
			Node startNode = node(i - 1);// 获取第 i-1个节点
			Node next = startNode.next;
			for (int j = 0; j < time; j++) {
				unlink(startNode.next);
			}
		}
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList list) {

		// 校验给定的索引值是否大于size
		for (Iterator it = list.iterator(); it.hasNext();) {
			int index;
			if ((index = (Integer) it.next()) >= size)
				throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}

		int[] result = new int[list.size()];
		int count = 0;// 记录Node在linkedList里的索引
		int i = 0;// 数组的索引
		Node node = list.first;
		for (Iterator it = this.iterator(); it.hasNext();) {
			Integer next = (Integer) it.next();
			if (node != null && (Integer) node.data == count) {
				result[i++] = (Integer) next;
				node = node.next;
			}
			count++;
		}
		return result;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list) {
		Node node = first;
		Node next = list.first;
		while (next != null && node!=null) {
			Node temp = node.next;// 暂存一下, 否则删了报空指针
			if (node.data == next.data || node.data.equals(next.data)) {
				unlink(node);
				next = next.next;
			}
			node = temp;
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		Node node = first;
		Node next = first.next;
		while (node != null && next != null) {
			while (node.data == next.data || node.data.equals(next.data)) {
				Node temp = next.next;
				unlink(next);
				next = temp;
				if (next == null)
					return;
			}
			node = node.next;
			next = next.next;
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {
		int startIndex = -1;
		int stopIndex = -1;
		int count = -1;
		for (Node node = first; node != null; node = node.next) {
			count++;
			if ((Integer) node.data >= max) {
				remove(startIndex, stopIndex - startIndex + 1);
				return;
			} else {
				stopIndex = count;
			}
			if (startIndex == -1 && (Integer) node.data > min)
				startIndex = count;
		}
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public LinkedList intersection(LinkedList list) {
		LinkedList newList = new LinkedList();
		Node thisNode = first;
		Node listNode = list.first;

		while (thisNode != null && listNode != null) {
			if(indexOf(listNode.data)!=-1){
				newList.add(listNode.data);
				thisNode = thisNode.next;
			}
			listNode = listNode.next;// listNode每次都要下移一位
		}
		return newList;
	}
}
