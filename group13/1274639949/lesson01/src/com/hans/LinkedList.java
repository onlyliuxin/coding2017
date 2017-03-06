package com.hans;

public class LinkedList implements List {
	
	private int size;
	
	private Node head;
	
	public LinkedList() {
		head = new Node();
//		head.data = head;
	}
	
	/**
	 * 添加一个元素
	 */
	public void add(Object o){
		Node tail = getTail();
		
		Node node = new Node();
		node.data = o;
		
		tail.next = node;
		size++;
		return;
	}
	
	/**
	 * 在指定位置加入一个元素
	 * @param index 指定的位置，应该满足 index > 0 && index <= size() 为 true。
	 */
	public void add(int index , Object o){
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index:" + index);
		
		Node pos = head;
		for(int i = 0; i < index; i++){
			//要在index位置添加一个元素，只要获取到 index - 1 位置的元素即可
			pos = pos.next;
		}
		Node node = new Node();
		node.data = o;
		node.next = pos.next;
		pos.next = node;
		size++;
		return;
	}
	
	/**
	 * 获取指定位置处的元素
	 * @param index 要获取的元素的位置，应该满足 index > 0 && index < size() 为 true。
	 */
	public Object get(int index){
		checkIndex(index);
		Node pos = head;
		for(int i = 0; i <= index; i++){
			//结束条件为 <= ,这是因为需要获取到index位置
			pos = pos.next;
		}
		return pos.data;
	}
	
	/**
	 * 移除并返回指定位置处的元素
	 * @param index 要移除的元素的位置，应该满足 index > 0 && index < size() 为 true。
	 */
	public Object remove(int index){
		checkIndex(index);
		Node pos = head;
		for(int i = 0; i < index; i++){
			pos = pos.next;
		}
		Node temp = pos.next;
		pos.next = temp.next;
		size--;
		return temp.data;
	}
	
	/**
	 * 获取存储的元素的个数
	 */
	public int size(){
		return this.size;
	}
	
	/**
	 * 在第一个元素的前面出添加一个元素
	 * @param o
	 */
	public void addFirst(Object o){
		add(0, o);
	}
	
	/**
	 * 在最后一个元素的后面添加一个元素
	 * @param o
	 */
	public void addLast(Object o){
		add(o);
	}
	
	/**
	 * 移除第一个元素
	 * @return 被移除的元素
	 */
	public Object removeFirst(){
		return remove(0);
	}
	
	/**
	 * 移除最后一个元素
	 * @return 被移除的元素
	 */
	public Object removeLast(){
		return remove(size - 1);
	}
	
	public Iterator iterator(){
		return null;
	}
	
	
	private static final class Node{
		Object data;
		Node next;
	}
	
	/**
	 * 获取最后一个节点
	 * @return
	 */
	private Node getTail(){
		Node tail = head;
		while(tail.next != null){
			tail = tail.next;
		}
		return tail;
	}
	/**
	 * 检查get和remove操作中所输入的元素是否有效
	 * @param index
	 */
	private void checkIndex(int index) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index:" + index);
	}
}

