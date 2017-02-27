package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private Node last;

	private int size = 0;
	
	public void add(Object o){
		addLast(o);

	}
	public void add(int index , Object o){

		Node node = new Node();
		node.data = o;
		if(size == 0)
		{
			throw new NullPointerException(" linked list is null");
		}
		if(index == 0)
		{
			node.next=head;
			head = node;
		}
		Node nodeNow = head;
		for(int i=1;i<size;i++)
		{
			Node nextNode = nodeNow.next;
			if(i == index)
			{
				node.next = nextNode;
				nodeNow.next = node;
				break;
			}
			nodeNow = nodeNow.next;
		}
		size++;

		
	}
	public Object get(int index){
		if(size == 0)
		{
			throw new NullPointerException("linked list is null");
		}
		if(index >=size)
		{
			throw new IndexOutOfBoundsException(" this index too big by this list");
		}

		Node nodeNow = head;
		for(int i=0;i<size;i++)
		{
			if(i == index)
			{
				return nodeNow.data;
			}
			nodeNow = nodeNow.next;
		}
		return null;
	}
	public Object remove(int index){

		if(index >=size)
		{
			throw new IndexOutOfBoundsException(" this index too big by this list");
		}
		if(size == 0)
		{
			throw new NullPointerException("linked list is null");
		}
		if(index == 0)
		{
			if(size == 1)
			{
				size = 0;
				return head.data;
			}
			Object o = head.data;
			head.next = null;

			head = head.next;
			return o;

		}
		Node result = null;


		Node beforeNode = head;
		Node nextNode = head.next;
		for(int i=1;i<size;i++)
		{
			if(index == i)
			{
				beforeNode.next = nextNode.next;
				size--;
				return nextNode.data;
			}
			beforeNode = nextNode;
			nextNode = nextNode.next;
		}


		return result;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		if(head == null)
		{
			Node node = new Node();
			node.data = o;
			head = node;
			last = node;
		}else{
			Node node = new Node();
			node.data = o;
			node.next = last;
			head = node;
		}
		size++;
	}
	public void addLast(Object o){
		if(head == null)
		{
			Node node = new Node();
			node.data = o;
			head = node;
			last = node;
		}else{
			Node node = new Node();
			node.data = o;
			last.next = node;
			last = node;
		}
		size++;
	}
	public Object removeFirst(){
		if(size == 0)
		{
			throw new NullPointerException(" this linkedlist is null");
		}
		if(size == 1)
		{
			size = 0;
		}

		head = head.next;
		size--;
		return head.data;
	}
	public Object removeLast(){

		if(size==0)
		{
			throw new NullPointerException("this linkedlist is null ");
		}

		if(size == 1)
		{
			size = 0;
			return head;
		}



		Node nowNode = head;
		Node beforeNode = head;
		for(int i=0;i<size;i++)
		{
			if(nowNode.next == null)
			{
				beforeNode.next = null;
				last = beforeNode;
				size--;
				return last;


			}
			nowNode = nowNode.next;
			beforeNode = nowNode;
		}




		size--;

		return last.data;

	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
	}
	class LinkedListIterator implements Iterator{
		private LinkedList linkedList ;
		private int countNum = 0;

		public LinkedListIterator(LinkedList linkedList)
		{
			this.linkedList = linkedList;
			countNum = linkedList.size;
		}



		@Override
		public boolean hasNext() {
			if(countNum == 0)
			{
				return false;
			}
			return true;
		}

		@Override
		public Object next() {

			Object obj = linkedList.get(countNum--);
			linkedList.removeLast();

			return obj;
		}
	}

}
