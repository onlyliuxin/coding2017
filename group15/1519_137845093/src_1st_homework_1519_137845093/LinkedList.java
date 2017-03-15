package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	public void add(Object o){
		//�ж���û��ͷ���
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
		//����±��Ƿ�Խ��
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("index:" + index + "Խ�磻");
		} 
		Node node = head;
		//������ǵ�һ��ͷ���
		if(index == 0){
			Node newNode = new Node(o,head);
			head = newNode;
			size ++;
		}
		else{
			for(int i = 1; i < index; i++){
				node = node.next;
			}
			//��index������o�����ҽ�o��next�ڵ���Ϊnode.next
			Node newNode = new Node(o, node.next);
			node.next = newNode;
			size++;
		}				
}	
				
	public Object get(int index){
		//����±��Ƿ�Խ��
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("index:" + index + "Խ�磻");
		} 
		Node node = head;
		for (int i = 1; i <= index; i++) {
			node = node.next;
		}
		return node.data;
	}
	
	public Object remove(int index){
		//����±��Ƿ�Խ��
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("index:" + index + "Խ�磻");
		} 
		Node node = head;
		Node removeNode;
		if (index == 0) {
			//��һ���ڵ�ֱ�ӽ�ͷ�ڵ�ָ����һ���ڵ�
			removeNode = head;
			head = head.next;
		} 
		else {
			//�ҵ�����ֵ��ǰһ���ڵ�
			for (int i = 1; i < index; i++) {
				node = node.next;
			}
			removeNode = node.next;
			//ǰһ���ڵ�ָ�룬ָ��ɾ���ڵ���ָ��Ľڵ�
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
			throw new IndexOutOfBoundsException("û��Ԫ�أ�");	
		}
		Node node = head;
		head = head.next;
		size--;
		return node.data;	
	}
	
	public Object removeLast(){
		if(size <= 0){
			throw new IndexOutOfBoundsException("û��Ԫ�أ�");	
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
