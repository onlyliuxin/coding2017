package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private Node last;
	private int size =0;
	
	public void add(Object o){
			
		if(head == null){
			head = new Node();
			last = new Node();
			head.data=o;
			last = head;
		
		}else{
			Node node = new Node();
			node.data = o;
			last.next = node;			
			last = node;			
		}
		size++;		
	}
	public void add(int index , Object o){
		if(index == 0){
			 addFirst(o);
		}
		
		if(size >index){	
			Node newnode = new Node();
			newnode.data = o;
			Node nodefirst = new Node();
			Node nodelast = new Node();
			int amount =1;
			Node first = head;
			while(first.next !=null){
				if(amount+1 == index){
				nodefirst.next = newnode;
				nodelast = first.next;
				newnode.next = nodelast;	
				size++;
				}
				first = first.next;
				amount++;		
			}		
		}
		if(size==index){
		
			Node node = new Node();
			node.data = o;
			last.next =node;
			last = node;
			size++;
			
		}
	}
	public Object get(int index){
		int amount = 0;
		Node newnode = head;
		while(newnode.next !=null){
			if(amount==index){
				return newnode.data;	
			}
			amount++;
			newnode = newnode.next;			
		}
		return newnode.data;
	}
	public Object remove(int index){
		
		if(index ==0){
			removeFirst();	
		}
		int amount =0;
		Node first = head;
		Node remove = new Node();
		while(first.next!=null){
			if(amount+1==index){
			remove = first.next;	
			first.next = remove.next;
			size--;
			return remove.data;
			}
			first = first.next;
			amount++;		
		}	
		return null;
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		Node newhead =new Node();
		newhead.data=o;
		newhead.next = head;
		head = newhead;
		size++;
		
	}
	public void addLast(Object o){
		add(o);
		
	}
	public Object removeFirst(){
		Object removedata = new Object();
		removedata = head.data;
		head = head.next;
		size--;
		return removedata;
		
	}
	public Object removeLast(){
		Node last = head;
		Node cache =new Node();
	   while(last.next !=null){
		   cache = last;
		   last = last.next;
	   }
	      cache.next =null; 
		  size--;
		  return last.data;
	
	}
	public Iterator iterator(){
		return new Iteratorimp(this.head);
	}
	public  class Iteratorimp implements Iterator{
		Node newnode = new Node();
		public Iteratorimp(Node node){
			this.newnode = node;		
		}

		public boolean hasNext() {
		   if(newnode.next ==null){
			   return false;  
		   }
			return true;
		}

		public Object next() {
			newnode = newnode.next;		
			return newnode.data;
		}
		}
	
	
	private static  class Node{
		Object data;
		Node next;
		
	}
}
