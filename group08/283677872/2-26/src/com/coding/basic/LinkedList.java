package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	
	public void add(Object o){
		if(head==null)
		{
			head = new Node();
			head.data = o;
		}		
		else
		{
			Node node = new Node();
			getLastNode(head).next = node;
			node.data = o;
		}
		
	}
	public Node getLastNode(Node node){
		if(node.next!=null)
		{
			return getLastNode(node.next);
		}
		else
		{
			return node;
		}
	}
	
	public void add(int index , Object o){
		if(index==0)
		{
			addFirst(o);
		}
		else
		{
			Node node1 = getNode(index-1,head);
			Node node2 = getNode(1,node1);
			Node node = new Node();
			node.data = o;
			node.next = node2;
			node1.next = node;
				
		}
		
	}
	public Node getNode(int index,Node node){
		if(index==0)
		{
			return node;
		}
		else
		{
			return getNode(--index,node.next);
		}
		
	}
	public Object get(int index){
		return getNode(index,head).data;
	}
	public Object remove(int index){
		Node node1 = getNode(index-1,head);
		Node node2 = getNode(1,node1);
		Node node3 = getNode(1,node2);
		node1.next = node3;
		return node2.data;
	}
	
	public int size(){
		int i = 0;
		Node node = head;
		while(node.next!=null)
		{
			node = node.next;
			i++;
		}
		return i;
	}
	
	public void addFirst(Object o){
		Node node = new Node();
		node.data =o;
		node.next = head;
		head = node;
	}
	public void addLast(Object o){
		Node node = new Node();
		node.data =o;
		getLastNode(head).next = node;
		
	}
	public Object removeFirst(){
		Node node = head;
		Node node1 = getNode(1,head);
		head = node1;
		return node.data;
	}
	public Object removeLast(){
		int size = size();
		return remove(size -1);
	}
	public Iterator iterator(){
		return null;
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		Node node = head;
		while(node.next!=null)
		{
			sb.append(node.data).append(",");
			node = node.next;
			
		}
		sb.append(node.data);
		return sb.toString();
		
	}
	private static  class Node{
		Object data;
		Node next;
		
		
		
	}
}
