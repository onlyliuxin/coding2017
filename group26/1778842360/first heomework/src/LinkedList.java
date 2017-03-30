//package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	int size;
	public void add(Object o){
		if(head==null)
		{
			head=new Node(o);
			head.next=null;
			head.data=o;
		}
		else{
			Node p=head;
			{
				while(p.next!=null)
				{
					p=p.next;
				}
				Node n=new Node(o);
				p.next=n;
				n.data=o;
				n.next=null;
			}
		}
		size++;
	}
	public void add(int index , Object o){
		int i=1;
		Node p=head;
		while(i<index-1)
		{
			p=p.next;
			i++;
		}
		Node s=new Node(o);
		//Object ob=p.data;
		s.data=o;
	    s.next=p.next;
		p.next=s;
		//p.next=null;
		size++;
	}
	public Object get(int index){
		int i=1;
		Node p=head;
		while(p!=null&&i<index)
		{
			p=p.next;
			++i;
		}
		if(p==null&&i>index) {return null;}
		return p.data;
	}
	public Object remove(int index){
		int i=1;
		Node p=head;
		Object o=null;
		if(index==1)
		{
			 o=head.data;
			 if(head.next!=null)
			 {
				  p=head.next;
				  head.data=p.data;
				  p=head;
			 }
			 else{
			 head=null;
			 }
		}
		else{
			while(i<index-1)
			{
				p=p.next;
				i++;
			}
			 o=p.next.data;
			 p.next=p.next.next;
			
		}
		size--;
		return o;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		if(head==null)
		{
			head=new Node(o);
			head.next=null;
			head.data=o;
		}
		else{
			Node p=new Node(o);
			Object o1=head.data;
			head.data=o;
			if(head.next!=null)
			{
				p.next=head.next;
			}
			else{
				head.next=p;
			    p.next=null;
			}
			p.data=o1;
			
		}
		size++;
	}
	public void addLast(Object o){
		if(head==null)
		{
			head=new Node(o);
			head.next=null;
			head.data=o;
		}
		else{
			Node p=head;
			{
				while(p.next!=null)
				{
					p=p.next;
				}
				Node n=new Node(o);
				p.next=n;
				n.data=o;
				n.next=null;
			}
		}
		size++;
	}
	public Object removeFirst(){
		Object o=head.data;
		//Node p=head;
		if(head.next!=null)
		{
		  Node p=head.next;
		  head.data=p.data;
		  head=p;
		}
		else{
			head=null;
		}
		size--;
		return o;
	}
	public Object removeLast(){
		Node p=head;
		Object o=null;
		int i=1;
		while(p!=null&&i!=size)
		{
			p=p.next;
			i++;
		}
		
		//p=null;
		o=p.data;
		p=null;
        size--;
		return o;
	}
	public Iterator iterator(){
		return new LinkedListIterator(this);
	}
	private class LinkedListIterator implements Iterator
	{
		private LinkedList l=null;
		Node p=head;
		public  LinkedListIterator(LinkedList l)
		{
			this.l=l;
		}

		public boolean hasNext() {
			// TODO Auto-generated method stub
			boolean flag=false;
			if(p!=null)
			{
				flag=true;
			}
			return flag;
		}

		public Object next() {
			// TODO Auto-generated method stub
			Object o=p.data;
			p=p.next;
			return o;
		}
		
	}
	
	
	private static  class Node{
		public Node(Object data) {
			this.data=data;
		}
		Object data;
		Node next;
		
	}
}

