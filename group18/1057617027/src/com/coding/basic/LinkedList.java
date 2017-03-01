package com.coding.basic;

public class LinkedList implements List {
	
	private Node head = new Node(null,null);
	private Node last;
	private int size;
	private static class Node{
		Object data;
		Node next;
		Node(Object data,Node next){
			this.data = data;
			this.next = next;
		}
	}
	public void add(Object o){
		if(size==0){
		Node node = new Node(o,null);
		head = node;
		last = node;
		size++;
		}else{
			Node node = new Node(o,null);
			last.next = node;
			last = node;
			size++;
		}

	}
	public void add(int index , Object o){
		if(index>size){
			System.out.println("插入的"+index+"大于当前的"+size);
		}
		Node n = head;
		Node n1 = head;
		for(int i=0;i<index-1;i++){
			n1 = n1.next;
		}
		for(int i=0;i<index;i++){
			n = n.next;
		}
		Node newnode = new Node(o,n);
		n1.next = newnode;
		newnode.next = n;
//		if(index+1<=size){
//			last = n;
//		}
		size++;
	}
	public Object get(int index){
		Node n =head;
		for(int i=0;i<index;i++){
			n =n.next;
		}
		return n.data;
	}
	public Object remove(int index){
		System.out.println(index);
		Node n =head;
		for(int i =0 ;i<index;i++){
			n=n.next;
		}
		System.out.println(n.data);
		n.next=n.next.next;
		size--;
		return n.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node(o,null);
		node.next = head;
		head = node;
		size++;
	}
	public void addLast(Object o){
		Node node = new Node(o,null);
		last.next = node;
		last =node;
		
	}
	public Object removeFirst(){
	    head=head.next;
	    size--;
		return null;
	}
	public Object removeLast(){
		Node nn = head;
		for(int i = 0;i<size-2;i++){
			nn = nn.next;
		}
		nn.next = null;
		last = nn;
		size--;
		return null;
		
	}
	public Iterator iterator(){
		return new Iter();
	}
	private int nextnum; 
	public class Iter implements Iterator{
		
		@Override
		public boolean hasNext() {
			
			return nextnum!=size;
		}

		@Override
		public Object next() {
			return get(nextnum++);
		}
		
	}
	
	

	public static void main(String args[]){
		LinkedList l = new LinkedList();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(2, "wo");
		l.removeFirst();
		l.addFirst(1);
		l.removeLast();
		l.addLast(4);
		while(l.iterator().hasNext()){
			System.out.println(l.iterator().next());
		}
		
//		System.out.println(l.size());
//		System.out.println(l.get(0));
//		System.out.println(l.get(1));
//		System.out.println(l.get(2));
//		System.out.println(l.get(3));
		//System.out.println(l.get(4));

	}
}
