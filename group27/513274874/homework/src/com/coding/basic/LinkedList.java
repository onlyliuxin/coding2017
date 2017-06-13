
package com.coding.basic;

import java.util.NoSuchElementException;

/**
 * @author zhougd 20170306
 * 单向链表实现LinkedList
 */

public class LinkedList implements List {
	java.util.LinkedList a;
	/**
	 * 第一个元素
	 */
	private Node head;

	/**
	 * 最后一个元素
	 */
	private Node tail;

	/**
	 * 元素容量
	 */
	private int size = 0;
	
	public void add(Object o){
		Node node = new Node(o);
		//判断是否链表为空
		if(this.size() == 0){
			this.addFirst(node);
		}else{
			this.addLast(node);
		}

	}
	public void add(int index , Object o){
		checkIndex(index);

		Node oldNode= this.getNode(index);
		Object oldObject = oldNode.getData();
		Node next = oldNode.getNext();

		//将原位置修改为新元素
		oldNode.setData(o);
		//设置下一个元素
		oldNode.setNext(new Node(oldObject));
		//设置下一个元素的下一个元素
		oldNode.getNext().setNext(next);

		size ++;
	}

	public Object get(int index){
		checkIndex(index);
		return this.getNode(index).getData();
	}

	public Object remove(int index){
		checkIndex(index);
		//获取到当前元素和下一个元素
		//把当前元素的值设置成下一个元素的值，删除掉下一个元素，这样的话，不必管上一个元素是什么，是不是第一个元素
		Node node = this.getNode(index);
		Object data = node.getData();
		Node nextNode = this.getNode(index + 1);
		node.setData(nextNode.getData());
		node.setNext(nextNode.getNext());

		return data;
	}
	
	public int size(){
		return this.size();
	}
	
	public void addFirst(Object o){
		Node node = new Node(o);
		//原头变为第二
		Node temp = this.head;
		this.head = node;
		node.next = temp;
		size++;
	}
	public void addLast(Object o){
		Node node = new Node(o);
		Node t = this.tail;
		if(t == null){
			this.head = node;
		}else{
			this.tail.next = node;
			this.tail = node;
		}
		size++;
	}
	public Object removeFirst(){
		Node head = this.head;
		if(head == null){
			throw new NoSuchElementException("No such element !");
		}
		this.head = this.head.getNext();
		size--;
		return head ;
	}

	public Object removeLast(){
		Node node ;
		if(this.tail == null){
			throw new NoSuchElementException("No such element !");
		}
		node = this.tail;
		if(this.head ==this.tail){
			node = this.head;
			this.head = null;
			this.size = 0;
		}else{
			//获取尾元素的上一个元素
			this.tail = this.getNode(this.size-2);
			this.tail.setNext(null);
			this.size--;
		}

		return node;
	}

	public Iterator iterator(){
		return new LinkedListIterator();
	}

	private void checkIndex(int index){
		if(index < 0 || index >size()){
			throw new IndexOutOfBoundsException("Index out of bound !");
		}
	}

	private Node getNode(int index ){

		Node node = this.head;
		for(int i = 0 ;i<size();i++){
			node = node.next;
		}
		return node;
	}

	private class LinkedListIterator implements Iterator{

		private int currentIndex = 0;
		private int count = size();

		@Override
		public boolean hasNext() {
			return currentIndex < count-1;
		}

		@Override
		public Object next() {
			currentIndex++;
			return get(currentIndex);
		}
	}
	
	private static class Node{
		Object data;
		Node next;

		public Node(Object data) {
			this.data = data;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}


}