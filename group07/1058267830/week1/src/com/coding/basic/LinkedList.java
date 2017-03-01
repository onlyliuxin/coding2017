package com.coding.basic;

public class LinkedList implements List, Iterator{
	private Node head;
	private Node tail;
	private Node currentNode;
	private int size;
	
	public LinkedList(){
		this.head = new Node(null); 
		this.tail = head;
		this.currentNode = head;
		this.size = 0;
	}

	@Override
	public void add(Object o) {
		Node node = new Node(o, null);
		tail.setNext(node);
		tail = node;
		size++;
	}

	@Override
	public void add(int index, Object o) {
		if(index < 0 || index > size+1){
			throw new RuntimeException("插入的位置错误...");
		}
		Node pre = head; // 得到待插入位置的前一个节点
		for(int i=0; i<index; i++){
			pre = pre.getNext();
		}
		Node behind = pre.getNext(); // 得到待插入位置的节点
		Node node = new Node(o, behind); // 构造当前节点并使当前节点的next指向behind
		pre.setNext(node);
		size++;
	}

	@Override
	public Object get(int index) {
		if(index < 0 || index >= size){
			throw new RuntimeException("index参数错误...");
		}
		Node node = head; // 得到待插入位置的前一个节点
		for(int i=0; i<=index; i++){
			node = node.getNext();
		}
		return node;
	}

	@Override
	public Object remove(int index) {
		if(index < 0 || index >= size){
			throw new RuntimeException("index参数错误...");
		}
		Node pre = head; // 得到待删除位置的前一个节点
		for(int i=0; i<index; i++){
			pre = pre.getNext();
		}
		Node node = pre.getNext(); // 得到待删除位置的节点并返回
		Node behind = node.getNext(); // 得到待删除位置的后一个节点
		pre.setNext(behind);
		size--;
		return node;
	}

	@Override
	public int size() {
		return size;
	}
	
	// 按顺序打印节点值
	public void printData(){
		Node node = head.getNext();
		while(node != null){
			System.out.println(node.getData());
			node = node.getNext();
		}
	}

	@Override
	public boolean hasNext() {
		if(currentNode == tail){
			return false;
		}else{
			currentNode = currentNode.getNext();
			return true;
		}
		
	}

	@Override
	public Object next() {
		return currentNode;
	}

}
