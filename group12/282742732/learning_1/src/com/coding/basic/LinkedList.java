package com.coding.basic;


public class LinkedList implements List {
	
	private Node head;
	private Node last;
	private int size = 0;
	
	public void add(Object o){
		if (head==null) {
			head=new Node(o);
			last=head;
		}else{
			Node node = new Node(o);
			last.setNext(node);
			last = node;
		}
		size = size + 1;
	}
	public void add(int index , Object o){
		
		if (index >=0 && index<=this.size && size>0) {
			
			if (index == 0) {
				this.addFirst(o);
			}else if(index == size){
				this.addLast(o);
			}else{
				int num = 0;
				Node node = head;
				Node node1 = null;
				Node nodenew = new Node(o);
				Node node3 = null;
				while (num <= index){
					
					if(index - 1 == num){
						node1 = node;
					}else if(index == num){
						node3 = node;
					}
					node = node.next;
					num = num +1;
				};
				nodenew.setNext(node3);
				node1.setNext(nodenew);
				
				
				size = size + 1;
			}

		}else{
			System.out.println("指针越界");
		}
		
	}
	public Object get(int index){
		
		
		if (index >=0 && index<=this.size && size>0) {
			int num = 0;
			Node node = head;
			while (num < size){
				if(index == num){
					return node.getData();
				}else{
					node = node.next;
					num = num +1;
				}
			};
		}else{
			System.out.println("指针越界");
			return null;
		}
		return null;
	}
	public Object remove(int index){
		
		if (index >=0 && index<=this.size && size>0) {
			
			if (index == 0) {
				return this.removeFirst();
			}else if(index+1==size){
				return this.removeLast();
			}else{
				int num = 0;
				Node node = head;
				Node node1 = null;
				Node node2 = null;
				Node node3 = null;
				while (num <= index+1){
					if(index - 1 == num){
						node1 = node;
					}else if(index+1 == num){
						node3 = node;
					}else if(index == num){
						node2 = node;
					}
					node = node.next;
					num = num +1;
				};
				node1.setNext(node3);
				size = size - 1;
				return node2.data;
			}

		}else{
			System.out.println("指针越界");
			return null;
		}
		
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node(o);
		node.setNext(head);
		head = node;
		size = size + 1;
	}
	public void addLast(Object o){
		Node node = new Node(o);
		last.setNext(node);
		last = node;
		size = size + 1;
	}
	public Object removeFirst(){
		Object obj = null;
		if(size > 0){
			obj = head.data;
			head = head.next;
		}else{
			System.out.println("指针越界");
			return null;
		}
		
		size = size - 1;
		return obj;
	}
	public Object removeLast(){
		Object obj = null;
		if(this.size() > 0){
			if(this.size()==1){
				obj = head.data;
				this.head = null;
			}else if (this.size()==2) {
				obj = head.next.data;
				this.head.setNext(null);
			}else{
				int num = 0;
				Node node = head;
				Node node1 = null;
				Node node2 = null;
				Node node3 = null;
				while (num <= size-2){
					if(size - 2 == num){
						obj = node.next.data;
						node.setNext(null);
						last = node;
					}else{
						node = node.next;
					}
					
					num = num +1;
				};
			}
		}else{
			System.out.println("指针越界");
			return null;
		}
		
		

		size = size - 1;
		return obj;
	}
	public Iterator iterator(){
		return new IteratorLinkedList(this.head);
	}
	
	
	static  class Node{
		Object data;
		Node next;
		
		public Node(Object object) {
			data = object;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
	}
	
	
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
//		System.out.println(list.removeLast());
//		System.out.println(list.removeFirst());
//		list.add(3,"xxxxxx");
//		System.out.println("======="+list.removeFirst());
//		System.out.println(list.size());
//		
//		System.out.println("list.get0========"+list.get(0));
//		System.out.println("list.get1========"+list.get(1));
//		System.out.println("=============");
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		Iterator it = list.iterator();
		
		while(it.hasNext()){
			String str = (String) it.next();
			System.out.println("str====="+str);
		}
		
	}
}
