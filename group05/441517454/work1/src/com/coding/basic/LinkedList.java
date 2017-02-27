package com.coding.basic;



public class LinkedList implements List {
	
	private Node head;
	private Node body;
	//private Node body1;
	private int size = 0;
	
	
	public void add(Object o){
		if(null == head){
			head = new Node();
			head.data = o;
			head.next = null;
			size++;
		}
//		else if(head.next == null){ 
//			
//			head.next =new Node();
//			head.next.data =  o;
//			head.next.next = null;
//			body=head.next;
//			size++;
//		}
		else {
			body=head;
			while(!(body.next ==null))
			{body =body.next;}
			body.next =new Node();
			body.next.data =o;
			body.next.next =null;
			size++;		
			}
		
	
	}
	public void add(int index , Object o){
		
	}
	public Object get(int index){
		 if (index<size)
			{body=head;
			for (int i=0;i<index;i++)
				{
					body =body.next;
				}
				return body.data;
			}else return null;
			
	}
	public Object remove(int index){
		 if (index<size&&index>1)
			{body=head;
			for (int i=0;i<index-1;i++)
				{
					body =body.next;
				}
			body.next=body.next.next;
			size--;	
				return body.next.data;
			}else return null;
		
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		if(null == head){
			head = new Node();
			head.data = o;
			head.next = null;
			size++;
		}else { 			
			body =new Node();
			body.data =  o;
			body.next = head;
			head=body;
			size++;
			}
	}
	public void addLast(Object o){
		if(!(head==null))			
			 {
				body=head;
				while(!(body.next ==null))
				{body =body.next;}
				body.next =new Node();
				body.next.data =o;
				body.next.next =body.next;
				size++;		
				}else
					System.out.println("请先建立头结点");
		
	}
	public Object removeFirst(){
		if(!(head==null))			
		 {
			body=head;
			head =head.next;
			size--;
			return body.data;
			}else

		return null;
	}
	public Object removeLast(){
		if (!(head==null))
		{body=head;
		for (int i=0;i<size-2;i++)
			{
				body =body.next;
			}
		body.next=body.next;
		size--;	
			return body.next.data;
		}else return null;
	
	}
	public Iterator iterator(){
		return new LinkedListIterator(this);
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
	}
	private class LinkedListIterator implements Iterator{
		private	LinkedList linkedList;
			private int pos = 0;
	public LinkedListIterator(LinkedList linkedList) {
		this.linkedList =linkedList;
		
	}

			@Override
			public boolean hasNext() {
				pos++;
				if(pos>linkedList.size){				
					return false;
				}else
				return true;
			}

			@Override
			public Object next() {
				
				return  linkedList.get(pos-1);
			}}
}
