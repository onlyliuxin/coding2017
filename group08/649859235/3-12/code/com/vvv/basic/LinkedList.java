package com.vvv.basic;

public class LinkedList implements List {

	private Node<Object> head;
	private int size;

	public void add(Object o) {
		addLast(o);
	}

	public void add(int index, Object o) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			addFirst(o);
			return;
		}

		if (index == size) {
			addLast(o);
			return;
		}

		Node<Object> newNode = new Node<Object>(o);
		Node<Object> node = head;
		for (int i = 1; i < index; i++) {
			node = node.next;
		}
		newNode.next = node.next;
		node.next = newNode;
		size++;
	}

	public Object get(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		Node<Object> node = head;
		for (int i = 0; i < index; i++) {
			if (node.next != null) {
				node = node.next;
			}
		}
		Object data = node.data;
		return data;
	}

	public Object remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			return removeFirst();
		}

		Node<Object> node = head;
		Node<Object> pre = head;
		for (int i = 0; i < index; i++) {
			if (node.next != null) {
				pre = node;
				node = node.next;
			}
		}
		
		Object obj = node.data;
		if (head.next == null) {
			head = null;
			pre = null;
		} else if (node.next == null) {
			pre.next = null;
			node = null;
		} else {
			pre.next = node.next;
			node.next = null;
			node = null;
		}
		
		size--;
		return obj;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node<Object> node = new Node<Object>(o);
		node.next = head;
		head = node;
		size++;
	}

	public void addLast(Object o) {
		Node<Object> newNode = new Node<Object>(o);
		if (head == null) {
			head = newNode;
		} else {
			if (head.next == null) {
				head.next = newNode;
			} else {
				Node<Object> node = head;
				for (int i = 1; i < size; i++) {
					node = node.next;
				}
				node.next = newNode;
			}
		}
		
		size++;
	}

	public Object removeFirst() {
		if (size <= 0)
			throw new IndexOutOfBoundsException();

		Node<Object> node = head;
		head = node.next;
		node.next = null;
		
		size--;
		return node.data;
	}

	public Object removeLast() {
		if (size <= 0)
			throw new IndexOutOfBoundsException();

		Node<Object> node = head;
		for (int i = 1; i < size - 1; i++) {
			node = node.next;
		}
		Object data = node.next.data;
		node.next = null;
		
		size--;
		return data;
	}

	public Iterator iterator() {
		return new LinkedListIterator();
	}

	@SuppressWarnings("hiding")
	private static class Node<Object> {
		Object data;
		Node<Object> next;

		public Node(Object data) {
			this.data = data;
		}
	}

	private class LinkedListIterator implements Iterator {
		private Node<Object> node = head;
		
		@Override
		public boolean hasNext() {
			return node!=null;
		}

		@Override
		public Object next() {
			Object data = node.data;
			node = node.next;
			return data;
		}
		
		public void moveFirst(){
			node = head;
		}
	}
	

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {
		if (size <= 0) {
			throw new IndexOutOfBoundsException();
		}

		Node<Object> node = head;
		Node<Object> middleNode = node;
		int length = size;
		for (int i = 0; i < length; i++) {
			if (node.next != null) {
				node = node.next;
				addFirst(node.data);
			}
		}

		middleNode.next = null;
		node = null;
		size = length;
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf() {
		if (size <= 0) {
			throw new IndexOutOfBoundsException();
		}

		Node<Object> node = head;
		Node<Object> pre = head;
		int count = 0;
		for (int i = 0; i < size / 2; i++) {
			pre = node;
			node = node.next;
			count++;
		}
		head = node;
		pre.next = null;
		pre = null;
		size = size - count;		
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		if(i<0 || i>=size || length<0 || length>size || (i+length)>size){
			throw new IndexOutOfBoundsException();
		}

         Node<Object> node = head;
         Node<Object> pre = head;      
         Node<Object> posNode = head;
 		for (int j = 0; j < i+length; j++) {
			if (node.next != null) {
				pre = node;
				if(j==(i-1)){
					posNode = node;
				}				
				node = node.next;
			}
		} 		
 		
 		if(i==0){
 			head = node; 			
 		}else{
 		    posNode.next = node;
 		}
 		pre.next = null;
 		pre = null;
 		size = size - length;
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		if (list == null || list.size() == 0) {
			throw new IndexOutOfBoundsException();
		}

		int[] arr = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			int index = (Integer) list.get(i);
			arr[i] = (Integer) get(index);
		}
		return arr;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */
	public void subtract(LinkedList list) {
		if (list == null || list.size() == 0) {
			return;
		}

		for (int i = 0; i < list.size(); i++) {
			int o = (Integer) list.get(i);
			LinkedListIterator it = (LinkedListIterator) this.iterator();
			int index = 0;
			while (it.hasNext()) {
				int obj = (Integer) it.next();
				if (obj == o) {
					remove(index);
					it.moveFirst();
					break;
				}
				index++;
			}
		}

	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		if (head == null) {
			return;
		}
		Node<Object> node = head;
		Node<Object> pre = head;
		while (node.next != null) {
			node = node.next;
			int value = (Integer) pre.data;
			if ((Integer) node.data == value) {
				if (node.next == null) {
					pre.next = null;
					size--;
					break;
				}
				pre.next = node.next;
				node = node.next;
				if (node == null) {
					size--;
					break;
				}
				size--;
			}
			pre = pre.next;
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {
		Node<Object> node = head;
		Node<Object> minNode = null;
		int count = 0;

		while (node != null && (Integer) node.data <= min) {
			minNode = node;
			node = node.next;
		}

		while (node != null && (Integer) node.data < max) {
			node = node.next;
			count++;
		}

		if (minNode != null) {
			minNode.next = node;
		} else {
			head = node;
		}
		size = size - count;
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public LinkedList intersection(LinkedList list) {
		if (head == null) {
			return null;
		}

		LinkedList newList = new LinkedList();
		Node<Object> node = head;
		while (node.next != null) {
			Iterator it = list.iterator();
			while (it.hasNext()) {
				int value = (Integer)it.next();
				if ((Integer) node.data == value) {
					newList.add(value);
					break;
				}
			}
			node = node.next;
		}
		
		return newList;
	}
}
