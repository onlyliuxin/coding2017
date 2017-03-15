package com.hans;

public class LinkedList implements List {
	
	private int size;
	
	private Node head;
	
	public LinkedList() {
		head = new Node();
//		head.data = head;
	}
	
	/**
	 * ���һ��Ԫ��
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
	 * ��ָ��λ�ü���һ��Ԫ��
	 * @param index ָ����λ�ã�Ӧ������ index > 0 && index <= size() Ϊ true��
	 */
	public void add(int index , Object o){
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index:" + index);
		
		Node pos = head;
		for(int i = 0; i < index; i++){
			//Ҫ��indexλ�����һ��Ԫ�أ�ֻҪ��ȡ�� index - 1 λ�õ�Ԫ�ؼ���
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
	 * ��ȡָ��λ�ô���Ԫ��
	 * @param index Ҫ��ȡ��Ԫ�ص�λ�ã�Ӧ������ index > 0 && index < size() Ϊ true��
	 */
	public Object get(int index){
		checkIndex(index);
		Node pos = head;
		for(int i = 0; i <= index; i++){
			//��������Ϊ <= ,������Ϊ��Ҫ��ȡ��indexλ��
			pos = pos.next;
		}
		return pos.data;
	}
	
	/**
	 * �Ƴ�������ָ��λ�ô���Ԫ��
	 * @param index Ҫ�Ƴ���Ԫ�ص�λ�ã�Ӧ������ index > 0 && index < size() Ϊ true��
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
	 * ��ȡ�洢��Ԫ�صĸ���
	 */
	public int size(){
		return this.size;
	}
	
	/**
	 * �ڵ�һ��Ԫ�ص�ǰ������һ��Ԫ��
	 * @param o
	 */
	public void addFirst(Object o){
		add(0, o);
	}
	
	/**
	 * �����һ��Ԫ�صĺ������һ��Ԫ��
	 * @param o
	 */
	public void addLast(Object o){
		add(o);
	}
	
	/**
	 * �Ƴ���һ��Ԫ��
	 * @return ���Ƴ���Ԫ��
	 */
	public Object removeFirst(){
		return remove(0);
	}
	
	/**
	 * �Ƴ����һ��Ԫ��
	 * @return ���Ƴ���Ԫ��
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
	 * ��ȡ���һ���ڵ�
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
	 * ���get��remove�������������Ԫ���Ƿ���Ч
	 * @param index
	 */
	private void checkIndex(int index) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index:" + index);
	}
}

