package org.coding.one;

public class LinkedList implements List {

	private Node first;
	
	private Node last;
	
	private int size = 0;
	
	@Override
	public void add(Object o) {
		Node newNode = new Node(o, null);
		if(this.last != null) {
			this.last.next = newNode;
			this.last = newNode;
		} else {
			this.first = newNode;
			this.last = newNode;
		}
		this.size++;
	}

	@Override
	public void add(int index, Object o) {
		checkIndex(index);
		if(index == 0) {	//first
			Node newNode = new Node(o, this.first);
			this.first = newNode;
			if(this.last == null)  { //通过该方法放入第一个元素时
				this.last = newNode;
			}
			this.size++;
		} else if (index == this.size) { //last
			add(o);
		} else {
			Node preNode = findNode(index - 1);
			Node nextNode = preNode.next;
			Node newNode = new Node(o, nextNode);
			preNode.next = newNode;
			this.size++;
		}
	}

	/**
	 * 通过角标获取对应的 Node 对象
	 * @param index (0 <= index < size)
	 * @return
	 */
	private Node findNode(int index) {
		Node node = this.first;
		for(int i = 1; i <= index ; i++) {
			node = node.next;
		}
		
		return node;
	}

	/**
	 * 0 <= index <= size
	 * @param index
	 */
	private void checkIndex(int index) {
		if(index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+ this.size);
		}
	}

	@Override
	public Object get(int index) {
		checkRangeIndex(index);
		return findNode(index).data;
	}
	
	/**
	 * 0 <= index < size
	 * @param index
	 */
	private void checkRangeIndex(int index) {
		if(index < 0 || index > this.size - 1) {
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+ this.size);
		}
	}

	@Override
	public Object remove(int index) {
		checkRangeIndex(index);
		Node oldNode = null;
		if(index == 0) {
			oldNode = this.first;
			this.first = oldNode.next;
		} else {
			Node preNode = findNode(index - 1);
			oldNode = preNode.next;
			preNode.next = oldNode.next;
		}
		oldNode.next = null;
		this.size--;
		return oldNode.data;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	public void addFirst(Object o){
		add(0, o);
	}
	
	public void addLast(Object o){
		add(this.size, o);
	}
	
	public Object removeFirst(){
		return remove(0);
	}
	
	public Object removeLast(){
		return remove(size - 1);
		
	}
	
	private static class Node {
		private Object data;
		private Node next;
		public Node(Object data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}
	}
}
