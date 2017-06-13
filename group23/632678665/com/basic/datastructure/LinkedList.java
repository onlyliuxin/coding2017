package com.basic.datastructure;

public class LinkedList implements List{
	private int size=0;
	private Node head=null;
	private int index=-1;
	
	private static class Node{
		Object data;
		Node next;
		public Node(Object o,Node next){
			this.data=o;
			this.next=next;
		}
	}
	@Override
	public void add(Object o) {
		Node front=null;
		Node last=null;
		if(head==null){
			head=new Node(o,null);
			front=head;
			size++;
			index++;
			return ;
		}
		while(front.next!=null){
			front=front.next;
		}
		last=new Node(o,null);
		front.next=last;
		index++;
		size++;
	}

	@Override
	public void add(int index, Object o) {
		Node front=null;
		Node last=null;
		if(head==null){
			head=new Node(o,null);
			front=head;
			size++;
			index++;
			return ;
		}
		for(int i=0;i<index;i++){
			if(front.next==null){
				add(null);
				front=front.next;
			}
		}
		add(o);
	}

	@Override
	public Object get(int index) {
		Node front=null;
		if(index==0){
			if(head==null){
				throw new IndexOutOfBoundsException();
			}
			return head.data;
		}
		front=head;
		for(int i=0;i<index;i++){
			if(front.next==null){
				throw new IndexOutOfBoundsException();
			}
			front=front.next;
		}
		return front.next.data;
	}

	@Override
	public Object remove(int index) {
		if(index==0){
			head=head.next;
		}
		Node front=null;
		for(int i=0;i<index;i++){
			if(front.next==null){
				throw new IndexOutOfBoundsException();
			}
			front=front.next;
		}
		front.next=front.next.next;
		size--;
		return front.next.data;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
}
