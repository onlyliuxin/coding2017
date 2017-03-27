package com.github.qq809203042.coding2017.basic.structures;

/*
 * 根据API实现自己的LinkedList数据结构
 */
public class MyLinkedList implements MyList {
	// 头节点对象，不存储元素，用于指示链表的起始位置
	private Node head = new Node(null,null);
	// 尾节点对象
	private Node last = new Node(null,null);
	// 链表长度
	int size = 0;

	@Override
	public Object get(int index) {
		Node node = node(index);
		return node.data;
	}

	@Override
	public boolean add(Object obj) {
		addLast(obj);
		return true;
	}

	@Override
	public boolean add(Object obj, int index) {
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("下标越界");
		}
		if(index == size){//等同于添加到末尾
			addLast(obj);
			return true;
		}
		Node nodeIndex = node(index);//获取index处的节点
		Node newNode = new Node(obj,nodeIndex);//新增元素的next指向原来index处
		if(index == 0){
			head.next = newNode;
		}else{
			Node nodeBeforeIndex = node(index-1);
			nodeBeforeIndex.next = newNode;
		}
		size++;
		return true;
	}
	
	@Override
	public Object remove(int index) {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("下标越界");
		}
		if(index == 0){
			return removeFirst();
		}else{
			Node nodeIndex = node(index);
			Object removeData = nodeIndex.data;
			node(index-1).next = nodeIndex.next;
			size--;
			return removeData;
		}
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	public void addFirst(Object obj) {
		// 创建一个临时节点存储第一个节点
		Node tempFirst = head.next;
		// 创建所需添加元素的节点对象
		Node newNode = new Node(obj, tempFirst);
		head.next = newNode;
		if (tempFirst == null) {// 如果链表中没有元素，则将添加的节点同时赋值为尾节点
			last = newNode;
		}
		size++;
	}

	public void addLast(Object obj) {
		// 创建一个临时节点存储尾节点
		Node tempLast = last;
		// 创建所需添加元素的节点对象
		Node newNode = new Node(obj, null);
		last = newNode;
		if (size == 0) {// 如果链表中没有元素，则将添加的节点同时赋值为头节点
			head.next = newNode;
		} else {// 如果链表中原先存在元素，则将原来尾节点的next指向现在的节点
			tempLast.next = newNode;
		}
		size++;

	}

	public Object removeFirst() {
		if(size == 0){
			throw new NullPointerException("链表为空");
		}
		Node removeNode =head.next;
		Object removeData = removeNode.data;
		head.next = removeNode.next;
		size--;
		return removeData;
	}

	public Object removeLast() {
		if(size <= 1){
			return removeFirst();
		}else{
			Object removeData = last.data;
			last = node(size-2);
			last.next = null;
			size--;
			return removeData;
		}
	}

	
	
	
	@Override
	public String toString() {
		if(size == 0){
			return "[]";
		}
		StringBuffer listToString = new StringBuffer();
		listToString.append("[");
		Node node = head;
		for(int i = 0; i < size;i++){
			node = node.next;
			listToString.append(node.data);
			if(i<size-1){
				listToString.append(",");
			}
		}
		listToString.append("]");
		return listToString.toString();
	}


	// 节点内部类
	private static class Node {
		Object data;
		Node next;

		// 构造函数
		Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
//	找到下标为index的节点并返回
	private Node node(int index) {
		if(size == 0){
			throw new IndexOutOfBoundsException("链表为空");
		}else if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("下标越界");
		}
		Node node = head.next;
		for(int i = 1; i <= index; i++){
			node = node.next;
		}
		return node;
	}
}
