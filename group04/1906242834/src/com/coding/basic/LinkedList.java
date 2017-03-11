package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private Node tail;
	private int size;
	//将最后一个节点的指针指向要插入的元素
	public void add(Object o){
		Node newnode = new Node();
		newnode.setData(o);
		//linkedList头元素为空的话，把此插入的新元素作为头元素
		if (head==null) {
			head = newnode;
			size ++;
		}else {
		//若头元素不为空，则把插入的节点newnode设置为（LinkedList）的末节点
			tail.setNext(newnode);
			tail = newnode;
			size ++;
		}
	}
	//将index上一个索引的指针指向o，将o的指针指向原index元素
	public void add(int index , Object o){
		if(head==null){
			System.out.println("LinkedList长度为0");
		}
		if(index>size()){
			System.out.println("IndexOutOfBound");
		}
		Node temp = head;
		Node newnode = new Node();
		newnode.setData(o);
		for (int i = 0; i < index-1; i++) {
			temp = temp.getNext();
		}
		if(temp.getNext()==null){
			temp.setNext(newnode);
			size ++;
		}else{
			temp.setNext(newnode);
			newnode.setNext(temp.getNext());
			size ++;
		}
		
	}
	public Object get(int index){
		Node temp = head;
		int a =0;
		while (a<=index) {
			temp = temp.getNext();
			a += 1;
			return temp.getData();
		}
		return null;
		
	}
	public Object remove(int index){
		if(head==null){
			return null;
		}
		Node temp = head;
		Object i = get(index);
		while(temp.getNext()!=i){
			temp = temp.getNext();
		}
		
		temp.setNext(temp.getNext().getNext());
		size--;
		
		return i;
	}
	
	public int size(){
		size = 0;
		if (head==null) {
			System.out.println("LikedList长度为0");
			return size;
		}
		Node temp = head;
		while(temp.getNext()!=null){
			size += 1;
			temp = temp.getNext();
		}
		return size;
	}
	
	public void addFirst(Object o){
		Node newnode = new Node();
		newnode.setData(o);
		newnode.setNext(head);
		size++;
	}
	
	public void addLast(Object o){
		Node newnode = new Node();
		newnode.setData(o);
		tail.setNext(newnode);
		newnode.setNext(null);
		size++;
	}
	public Object removeFirst(){
		if(head==null){
			return null;
		}
		head.setNext(null);
		return get(0);
	}
	public Object removeLast(){
		Object i = get(size-1);
		Node newnode = new Node();
		newnode.setData(i);
		newnode.setNext(null);
		return i;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		//获取当前节点数据
		public Object getData(){
			return data;
		}
		//设置当前节点数据
		public void setData(Object data){
			this.data = data;
		}
		//返回下一个节点
		public Node getNext(){
			return next;
		}
		//设置下一个节点
		public void setNext(Node next){
			this.next = next;
		}
	}
}