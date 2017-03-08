package com.coding.basic;

public class MyLinkedList implements MyList {
	private int size = 0;
	private Node head;
	private Node tail;

	@Override
	public void add(Object o) {
		if (isEmpty()) {
			head = new Node(o);
			tail = head;
		} else {
			Node tmp = new Node(o);
			tail.next = tmp;
			tmp.prov = tail;
			tail = tmp;
		}
		size++;
	}

	@Override
	public void add(int index, Object o) {
		checkBoundsForAdd(index);
		if (index == 0) {
			addFirst(o);
		} else if (index == size) {
			addLast(o);
		} else {
			Node pos = findIndexPosition(index);
			addMid(pos, o);
		}
		size++;
	}

	/**
	 * add node to the head
	 */
	public void addFirst(Object o) {
		Node tmp = new Node(o);
		if(head == null){
			head = tmp;
		}else{
			tmp.next = head;
			head.prov = tmp;
			head = tmp;
		}
	}

	/**
	 * add node to the last
	 */
	public void addLast(Object o) {
		Node tmp = new Node(o);
		tail.next = tmp;
		tmp.prov = tail;
		tail = tmp;
	}

	/**
	 * add node to middle position
	 * 
	 * @param pos
	 * @param o
	 */
	public void addMid(Node pos, Object o) {
		Node tmp = new Node(o);
		tmp.prov = pos.prov;
		pos.prov.next = tmp;
		tmp.next = pos;
		pos.prov = tmp;
	}

	@Override
	public Node get(int index) {
		checkBounds(index);
		if (index == 0) {
			return head;
		} else {
			Node pos = findIndexPosition(index);
			return pos;
		}
	}

	@Override
	public Object remove(int index) {
		checkBounds(index);
		Node res;
		if (index == 0) {
			res = removeFirst();
		} else if (index == size-1) {
			res = removeLast();
		} else {
			Node pos = findIndexPosition(index);
			res = removeMid(pos);
		}
		size--;
		return res;
	}

	public Node removeFirst() {
		Node tmp = head;
		head = head.next;
		if(head != null){
			head.prov = null;
		}
		
		return tmp;
	}

	public Node removeLast() {
		Node tmp = tail;
		tail = tail.prov;
		tail.next = null;
		return tmp;

	}

	public Node removeMid(Node pos) {
		pos.prov.next = pos.next;
		pos.next.prov = pos.prov;
		return pos;
	}

	@Override
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * the index should be within 0~size-1
	 * 
	 * @param index
	 */
	public void checkBounds(int index) {
		if (index < 0 || index > size - 1) {
			// System.out.println("From MyLinkedList: Index out of bounds");
			throw new IndexOutOfBoundsException(OutOfBoundsMsg(index));
		}
	}

	/**
	 * the index should be within 0~size
	 * 
	 * @param index
	 */
	public void checkBoundsForAdd(int index) {
		if (index < 0 || index > size) {
			// System.out.println("From MyLinkedList: Index out of bounds");
			throw new IndexOutOfBoundsException(OutOfBoundsMsg(index));
		}
	}

	public String OutOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + size;
	}

	/**
	 * find the position of index
	 * 
	 * @param index
	 * @return
	 */
	public Node findIndexPosition(int index) {
		Node pos = head;
		for (int i = 0; i < index; i++) {
			pos = pos.next;
		}
		return pos;
	}

	@Override
	public String toString() {
		String s = "";
		Node tmp = head;
		while (tmp != null) {
			s += tmp.data + " ";
			tmp = tmp.next;
		}
		return s;
	}

	private static class Node {
		public Object data;
		public Node prov;
		public Node next;

		public Node() {
		}

		public Node(Object o) {
			this.data = o;
			this.prov = null;
			this.next = null;
		}
	}
	
	public MyIterator iterator() {
		return new LinkedListIterator(this);
	}
	
	private class LinkedListIterator implements MyIterator{
		
		private MyLinkedList eleIterator;
		private int pos;
		
		private LinkedListIterator(MyLinkedList mll){
			this.eleIterator = mll;
			this.pos = 0;
		}
		
		@Override
		public boolean hasNext() {
			return pos <= size;
		}

		@Override
		public Object next() {
			Node res = eleIterator.get(pos);
			pos++;
			return res;
		}
		
	}

}
