package com.coding.basic;

public class LinkList implements List{

	private Node head; //ͷ��㲻�������
	private static class Node{
		Object data;
		Node next;
	}
	
	@Override
	public void add(Object o) {
		//��һ��û���뵽
		if(null == head){
			head = new Node();
			head.next = null;
			head.data = o;
		}else{
			//β���뷨
			//Node t = new Node();
			Node t;
			Node ins = new Node();
			t = head;
			while(t.next != null){
				t = t.next;
			}
			t.next = ins;
			ins.next = null;
			ins.data = o;
		}
	}

	@Override
	public void add(int index, Object o) {
		if(index < 0 ){
			System.out.println("Error");
		}else if(index == 0 || index == 1){
			Node t = new Node();
			t.next = head.next;
			head.next = t;
			t.data = o;
		}else{
			Node t = new Node();//��ǰ�ڵ�
			Node p = new Node();//ǰһ���ڵ�
			t = head.next;
			for(int i = 1;i < index;i++){
				p = t;
				t = t.next;
			}
			Node ins = new Node();
			p.next = ins;
			ins.next = t;
			ins.data = o;
		}
		
	}

	@Override
	public Object get(int index) {
		if(index < 0 || head == null){
			System.out.println("Error");
			return null;
		}else{
			Node t ;
			t = head;
			for(int i = 1;i < index;i++){
				t = t.next;
			}
			return t.data;
		}
	}

	@Override
	public Object remove(int index) {
		
		return null;
	}

	public void display(){
		if(head == null){
			System.out.println("No Data");
		}else{
			Node t ;
			t = head;
			while(t != null){
				System.out.println("******"+t.data);
				t = t.next;
			}
		}
		
	}
	@Override
	public int size() {
		return 0;
	}
}
