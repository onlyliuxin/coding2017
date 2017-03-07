package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	public void add(Object o){
		//判断有没有头结点
		if(head == null)
			head = new Node(o,null);
		else {
			Node newNode = head;
			while(newNode.next != null){
				newNode = newNode.next;
			}
			newNode.next = new Node(o,null);
			
			}
		
	}
	public void add(int index , Object o){
		//检查下标是否越界
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("index:" + index + "越界；");
		} 
		Node node = head;
		//插入的是第一个头结点
		if(index == 0){
			Node newNode = new Node(o,head);
			head = newNode;
			size ++;
		}
		else{
			for(int i = 1; i < index; i++){
				node = node.next;
			}
			//在index处插入o，并且将o的next节点设为node.next
			Node newNode = new Node(o, node.next);
			node.next = newNode;
			size++;
		}				
}	
				
	public Object get(int index){
		//检查下标是否越界
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("index:" + index + "越界；");
		} 
		Node node = head;
		for (int i = 1; i <= index; i++) {
			node = node.next;
		}
		return node.data;
	}
	
	public Object remove(int index){
		//检查下标是否越界
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("index:" + index + "越界；");
		} 
		Node node = head;
		Node removeNode;
		if (index == 0) {
			//第一个节点直接将头节点指向下一个节点
			removeNode = head;
			head = head.next;
		} 
		else {
			//找到索引值的前一个节点
			for (int i = 1; i < index; i++) {
				node = node.next;
			}
			removeNode = node.next;
			//前一个节点指针，指向被删除节点所指向的节点
			node.next = removeNode.next;
		}
		size--;
		return removeNode.data;
	}
		
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node newNode = new Node(o, head.next);
		head.next = newNode;
		size++;
	}
	
	public void addLast(Object o){
		add(o);
	}
	
	public Object removeFirst(){
		if(size <= 0){
			throw new IndexOutOfBoundsException("没有元素；");	
		}
		Node node = head;
		head = head.next;
		size--;
		return node.data;	
	}
	
	public Object removeLast(){
		if(size <= 0){
			throw new IndexOutOfBoundsException("没有元素；");	
		}
		Node node = head;
		while(node.next != null){
			node = node.next;
		}
		Object val = node.data;
		node = null;
		size--;
		return val;		
	}
	private static  class Node{
		Object data;
		Node next;

		Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		
		}
	}

	
	
	
	public Iterator iterator(){
		return new Itr(this);
	}
	
    private class Itr implements Iterator{
        private int l = -1;
        private LinkedList list;
		private Itr(LinkedList linkedList) {
			// TODO Auto-generated constructor stub
			this.list = list;
			
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return l < list.size - 1;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			l++;
			if (l >= list.size) {
				l--;
				throw new IndexOutOfBoundsException();
			}

			return list.get(l);
		}

		@Override
		public Object remove() {
			// TODO Auto-generated method stub
			if (l < 0) {
				throw new NoSuchElementException();
			}
			Object val = list.removeLast();
			l--;
			return val;
		}
		
	}
	
    }
