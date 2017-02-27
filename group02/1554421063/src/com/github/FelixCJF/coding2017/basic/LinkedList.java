package com.github.FelixCJF.coding2017.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;//头指针
	private Node last;//尾指针
	private int size = 0;
	
	public void add(Object o){
		addLast(o);
	}
	
	public void add(int index , Object o){
		//检查是否越界
		checkIndex(index);

		Node indexNode = node(index);
		
		if (index == size) {
			addLast(o);
		} else {
			final Node pred = indexNode.prv;
			
			final Node newNode = new Node();
			newNode.data = o;
			newNode.next = indexNode;
			newNode.prv = pred;
			
			indexNode.prv = newNode;
			
			if (pred == null) {
				head = newNode;
			} else {
				pred.next = newNode;
			}       
		}
		size ++;
	}
	public Object get(int index){
		//检查是否越界
		checkIndex(index);
		
		Node indexNode = node(index);
		
		return indexNode.data;
	}
	public Object remove(int index){
		//检查是否越界
		checkIndex(index);
		
		Node indexNode = node(index);
		Object element = indexNode.data;
		Node pre = indexNode.prv;
		Node next = indexNode.next;
		
		if (pre == null) {
			head = next;
		} else {
			pre.next = next;
			indexNode.prv = null;
		}
		
		if (next == null) {
			last = pre;
		} else {
			next.prv = pre;
			indexNode.next = null;
		}
		
		indexNode.data = null;
		
		size --;
		
		return element;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		//节点变量存放原来的头指针
		final Node oldHead = head;
		//创建新的节点对象
		final Node newNode = new Node();
		newNode.data = o;
		newNode.next = head;
		newNode.prv = null;
		//判断oldhead是否为null
		if (oldHead == null) {
			last = newNode;
		}else {
			//头指针指向新创建的节点对象
			oldHead.prv = newNode;
		}
		//将newNode变为头指针
		head = newNode;
		size ++;
	}
	public void addLast(Object o){
		//节点新变量放原先的尾指针
		final Node oldLast = last;
		//创建新节点，加入要添加的对象
		final Node newNode = new Node();
		newNode.data = o;
		newNode.next = null;
		newNode.prv = oldLast;
		if (oldLast == null) {
			head = newNode;
		} else {
			//尾指针指向新创建的节点
			oldLast.next = newNode;
		}
		//newNode变为尾指针
		last = newNode;
		size++;
	}
	public Object removeFirst(){
		//通过头指针创建头节点
		final Node hNode = head;
		if (hNode == null) {
			throw new NoSuchElementException();
		}
		final Node next  = hNode.next;
		final Object element = hNode.data;
		
		//移除
		hNode.data = null;
		hNode.next = null;
		head = next;
		//判断是否为尾节点
		if (next == null) {
			last = null;
		}else {
			next.prv = null;
		}
		size --;
		return element;
	}
	public Object removeLast(){
		//通过尾指针创建节点
		final Node lastNode = last;
		if (lastNode == null) {
			throw new NoSuchElementException();
		}
		final Object element = lastNode.data;
		final Node prve = lastNode.prv;
		
		//移除
		lastNode.data = null;
		lastNode.prv = null;
		last = prve;
		
		if (prve == null) {
			head = null;
		} else {
			prve.next = null;
		}
		size --;
		return element;
	}
	public Iterator iterator(){
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator{
		
		private Node currentNode = head;//当前节点

		public boolean hasNext() {
			if (currentNode == null) {
				return false;
			}
			return true;
		}

		public Object next() {
			Object element = currentNode.data;
			currentNode = currentNode.next;
			return element;
		}
		
	}
	
	//查找index节点,并返回该节点
    Node node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prv;
            return x;
        }
    }
	//检查索引
	private void checkIndex(int index){
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
	}
	private static  class Node{
		Object data;
		Node next;
		Node prv;
	}
}