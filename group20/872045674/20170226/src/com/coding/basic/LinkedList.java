package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	
	public void add(Object o){
		addFirst(o);
	}
	public void add(int index , Object o){
		Node node = head;
		for(int i=0;i<index;i++){
			node = node.next;
		}

		Node newNode=new Node();
		newNode.data=o;
		newNode.next=node;
		add2Index(head,index,newNode);

	}
	private int count=0;
	private void add2Index(Node node,int index,Node nextNode){
		count++;
		if(index==count){
			node.next=nextNode;
			return;
		}
		add2Index(node.next,index,nextNode);
	}

	public Object get(int index){
		Node node = head;
		for(int i=0;i<index;i++){
			if(node.next==null){
				System.out.println("位置输入错误");
				break;
			}
			node = node.next;
		}
		return node.data;
	}
	public Object remove(int index){
		return null;
	}
	
	public int size(){
		int count=0;

		return count;
	}




	
	public void addFirst(Object o){
		Node newNode=new Node();
		newNode.data=o;
		newNode.next=head;
		head=newNode;
	}
	public void addLast(Object o){
		Node newNode=new Node();
		newNode.data=o;
		if(head==null){
			head=newNode;
		}else{
			lastElement(head,o);
		}

	}

	private void lastElement(Node node,Object o){
		if(node.next==null){
			Node newNode=new Node();
			newNode.data=o;
			node.next=newNode;
			return;
		}
		lastElement(node.next,o);
	}

	public Object removeFirst(){
		if(head==null){
			System.out.println("数据为空，不用删除");
			return false;
		}
		head = head.next;
		return true;
	}
	public Object removeLast(){
		removeLastNode(head);
		return true;
	}

	private void removeLastNode(Node node){
		if(node.next==null){
			node.data = null;
			return;
		}
		removeLastNode(node.next);
	}

	public Iterator iterator(){
		return new LinkedListIterator(this);
	}
	private class LinkedListIterator implements Iterator{
		private LinkedList list = null;
		private Node node=null;
		private LinkedListIterator(LinkedList l){
			this.list = l;
			this.node = head;
		}
		@Override
		public boolean hasNext() {
			if(node.next==null){
				return false;
			}
			node = node.next;
			return true;
		}

		@Override
		public Object next() {
			return node.data;
		}
	}
	
	
	private static  class Node{
		Object data;
		Node next;
	}

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.addFirst(1);
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(4);
		list.addLast(5);
		list.removeLast();
		System.out.println(list.size());
		//list.add(3,10);
		//System.out.println(list.get(4));
		Iterator i=list.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}
	}
}
