package com.coding.mybasic;

public class LinkedList implements List {
	
	private Node head;
	private Node last;
	private int size;
	
	public LinkedList() {
	}
	
	@Override
	public void add(Object element) {
		if(head == null){
			addHead(element);
		}else{
			addLast(element);
		}
	}
	
	@Override
	public void add(int index, Object element) {
		if(index == size){
			add(element);
			return;
		}
		
		if(index == 0){
			addFirst(element);
			return;
		}
		checkIndex(index);
		insertElement(index - 1,element);
	}
	
	
	@Override
	public Object get(int index) {
		checkIndex(index);
		Node node = getNodeByIndex(index);
		return node != null ? node.data : null;
	}

	@Override
	public Object remove(int index) {
		
		checkIndex(index);
		Object element = null;
		if(index == 0){
			element = removeFirst();
		}
		else if(index == (size - 1)){
			element = removeLast();
		}
		else {
			element = removeMiddle(index);
		}
		return element;
	}
	
	
	@Override
	public int size() {
		return size;
	}
	

	@Override
	public Iterator iterator() {
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator{
		private Node node = head;
		int i = 0;
		@Override
		public boolean hasNext() {
			return i < size;
		}

		@Override
		public Object next() {
			checkIndex(i);
			Object element = node.data;
			node = node.next;
			i++;
			return element;
		}
		
	}

	public void addFirst(Object o){
		Node node = new Node();
		node.data = o;
		node.next = head.next;
		head = node;
		size++;
	}
	public void addLast(Object o){
		Node node = new Node();
		node.data = o;
		node.next = null;
		last.next = node;
		last = node;
		size++;
	}
	public Object removeFirst(){
		return removeFirstNode();
	}
	public Object removeLast(){
		return removeLastNode();
	}
	private Object removeMiddle(int index) {
		Node temp = getNodeByIndex(index - 1);
		Node removeNode = temp.next;
		Object element = removeNode.data;
		temp.next = removeNode.next;
		removeNode = null;
		size--;
		return element;
	}

	/**
	 * 检查index index >=0 且  < size
	 * @param index 
	 * @throws Exception
	 */
	private void checkIndex(int index) {
		if(index < 0){
			throw new RuntimeException("index 必须大于0");
		}
		// 越界
		if(index >= size){
			throw new RuntimeException("index 必须小于size:" + size);
		}
	}
	
	/**
	 * 添加head
	 * @param element
	 */
	private void addHead(Object element) {
		head = new Node();
		head.data = element;
		head.next = null;
		last = head;
		size++;
	}
	/**
	 * 插入序号在0-size之间的元素,不包含0和size位置 
	 * @param index
	 * @param element
	 */
	private void insertElement(int index, Object element) {
		
		Node temp = getNodeByIndex(index);
		if(temp != null){
			Node node = new Node();
			node.data = element;
			node.next = temp.next;
			temp.next = node;
		}
		size++;
	}
	/**
	 * 获取下标为index的元素
	 * @param index
	 * @return
	 */
	private Node getNodeByIndex(int index) {
		Node temp = head;
		int i = 0;
		
		while(i < size){
			if(i == index){
				return temp;
			}
			temp = temp.next;
			i++;
		}
		
		return null;
	}
	/**
	 * 移除最后一个元素
	 * @return
	 */
	private Object removeLastNode() {
		Node node = getNodeByIndex(size - 2);
		Node lastNode = node.next;
		Object element = lastNode.data;
		lastNode = null;
		last = node;
		size--;
		return element;
	}
	/**
	 * 移除第一个元素
	 * @return
	 */
	private Object removeFirstNode() {
		Node node = head.next;
		Object element = head.data;
		head = null;
		head = node;
		size--;
		return element;
	}
	
	
	
	private static class Node{
		Object data;
		Node next;
		public Node() {
		}
		@SuppressWarnings("unused")
		public Node(Object data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}
		
		
	}
}
