package com.bjsxd.test;

public class LinkedList implements List{
	private static class Node{
		Object data;
		Node next;
	}
	private Node head;
	private Node last;
	public void add (Object o){
		if (head == null){
			head = new Node();
			head.data = o;
			head.next = null;
		}else{
			Node MyNode = new Node();
			MyNode = head;
			while (MyNode.next != null){
				MyNode = MyNode.next;
			}
			Node AddNode = new Node();
			MyNode.next = AddNode;
			AddNode.data = o;
		}
	}
	public void add(int index,Object o){
		if(index<0 || o ==null){
			throw new IllegalArgumentException("添加对象位置出错且不能为空");
		}else if (index == 0 && head == null){
			head = new Node();
			head.data = o;
			head.next = null;
		}else if (index > 0 && head == null){
			throw new IllegalArgumentException("添加对象位置出错");
		}else{
			Node SrcNode = new Node();
			Node AddNode = new Node();
			Node SrcNode2 = new Node();
			SrcNode = head;
			for(int i=0;i<=index;i++){
				SrcNode = SrcNode.next;
			}
			AddNode.next = SrcNode;
			AddNode.data = o;
			for (int i=0;i<index;i++){
				SrcNode2 = SrcNode2.next;
			}
			SrcNode2.next = AddNode;
		}
	}
	public Object get(int index){
		Node SrcNode = new Node();
		for (int i=0;i<index;i++){
			SrcNode = SrcNode.next;
		}
		return SrcNode;
	}
	public Object remove(int index){
		Node SrcNode = new Node();
		Node TempNode = new Node();
		Node MyNode = new Node();
		MyNode = head;
		int size = 0;
		while (MyNode != null){
			MyNode = MyNode.next;
			size++;
		}
		if (index < 0 || index > size){
		throw new IllegalArgumentException("删除对象位置出错");	
		}else{
		for (int i=0;i<index;i++){
			SrcNode = SrcNode.next;
		}
		TempNode =  SrcNode.next;
		SrcNode.next = SrcNode.next.next;
		return TempNode;
		}
	}
	public int size(){
		Node MyNode = new Node();
		MyNode = head;
		int size = 0;
		while (MyNode != null){
			MyNode = MyNode.next;
			size++;
		}
		return size;
	}
	public void addFirst(Object o){
		Node MyNode = new Node();
		MyNode.data = o;
		MyNode.next = head;
		head.next = MyNode;
	}
	public Object removeFirst(){
		Node MyNode = new Node();
		MyNode.data = head.data;
		head.next = head.next.next;
		return MyNode;
	}
	public Object removeLast(){
		Node TempNode = new Node();
		Node MyNode = new Node();
		MyNode = last;
		TempNode = head;
		int size = 0;
		while (TempNode != null){
			TempNode = TempNode.next;
			size++;
		}
		for (int i = 0;i < size-1; i++){
			TempNode = TempNode.next;
		}
		TempNode.next = null;
		return MyNode;
	}
}
