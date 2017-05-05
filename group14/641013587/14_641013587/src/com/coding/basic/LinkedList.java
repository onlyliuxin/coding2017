package com.coding.basic;

public class LinkedList implements List {
	
	private Node head=new Node();
	
	public void add(Object o){
		if(head.data==null){
			head.data=o;
			return;
		}
		Node node=head;
		for(;node.next!=null;){
			node=node.next;
		}
		node.next=new Node();
		node.next.data=o;
		
	}
	public void add(int index , Object o){
		if(index==0){
			addFirst(o);
			return;
		}
		Node node=head;
		for(int i=0;i<index;i++){
				node=node.next;
				if(node==null){
					node=new Node();
				}
			
		}
		node.data=o;
		return;
	}
	public Object get(int index){
		Node node=head;
		for(int i=0;i<index;i++){
				node=node.next;
		}
		return node.data;
	}
	public Object remove(int index){
		if(index==0){
			Object data=head.data;
			removeFirst();
			return data;
		}
		Node node=head;
		for(int i=0;i<index-1;i++){
				node=node.next;
		}
		Object data=node.next.data;
		node.next=node.next.next;
		return data;
	}
	
	public int size(){
		int i =0;
		Node node=head;
		while (node!=null) {
			node=node.next;
			i++;
		}
		return i;
	}
	
	public void addFirst(Object o){
		Node node = new Node();
		node.data=o;
		node.next=head;
		head=node;
	}
	public void addLast(Object o){
		add(o);
	}
	public Object removeFirst(){
		Object data=head.data;
		head=head.next;
		if(head==null){
			head=new Node();
		}
		return data;
	}
	public Object removeLast(){
		Object data = null;
		Node node=head;
		for(;node.next!=null;){
			if(node.next.next==null){
				data=node.next.next;
				node.next=node.next.next;
				break;
			}
			node=node.next;
		}
		return data;
	}
	public Iterator iterator(){
		return new Iterator() {
			
			Node node=head;
			
			@Override
			public Object next() {
				Object data=node.data;
				node=node.next;
				return data;
			}
			
			@Override
			public boolean hasNext() {
				return node==null?false:true;
			}
		};
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
	}
}
