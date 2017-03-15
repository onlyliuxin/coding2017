package com.tiaozaoj;

public class NewLinkedList {
	
	private Node head;
	private int size = 0;
	
	//���췽��
	public NewLinkedList(){
		//��ʼ����ʱ������һ��ͷ��㣬���洢����
		head = new Node("0x666");
		head.next = null;
	}
	
	public void add(Object o){
		Node newNode = new Node(o);
		head.next = newNode;
		newNode = head;
		this.size++;
	}
	
	private void verifyIndex(int index){
		try{
			if(index<0 || index>size)
				throw new Exception("Խ���쳣");
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
	}
	
	public void add(int index,Object o){
		this.verifyIndex(index);
		int j = -1;
		Node newNode = new Node(o);
		//�������
		for(Node p = head.next;p.next != null;p = p.next){
			if((index) == j+1){
				Node q = p.next;
				p.next = newNode;
				newNode.next = q;
				break;
			}
			j++;
		}
		this.size++;
	}
	
	public Object get(int index){
		this.verifyIndex(index);
		int j = 0;
		//�������
		Node p = head.next;
		for(;p.next != null;p = p.next){
			if((index) == j){
				break;
			}
			j++;
		}
		return p;
	}
	
	public Object remove(int index){
		this.verifyIndex(index);
		int j = -1;
		//�������
		Node p = head.next;
		for(;p.next != null;p = p.next){
			if((index) == j+1){
				break;
			}
			j++;
		}
		Node toRemoveNode = p.next;
		p.next = toRemoveNode.next;
		return toRemoveNode;
	}
	
	public int size(){
		return size;
	}
	
	private static class Node{
		public Object data;
		public Node next;
		
		public Node(Object o){
			this.data = o;
		}
	}
}
