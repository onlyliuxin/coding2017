package com.coding.basic;

public class LinkedList implements List {
	//head 和 tail 有可能指向同一个对象
	private Node head;
	private Node tail;
	
	private int size = 0;
	
	
	public void add(Object o){
		if(o == null)
			return;
		
		Node nodeToAdd = new LinkedList.Node();
		nodeToAdd.data = o;
		
		if(this.size == 0)
		{
			Init(nodeToAdd);
			return;
		}
		
		AddTailNode(nodeToAdd);
	}
	
	public void add(int index , Object o){
		//check user input
		if(o == null || index < 0)
			return;
		
		Node nodeToAdd = new LinkedList.Node();
		nodeToAdd.data = o;
		
		if(this.size == 0)
		{
			Init(nodeToAdd);
			return;
		}
		
		//be the tail
		if(index >= this.size)
		{
			AddTailNode(nodeToAdd);
			return;
		}
		
		//be the head
		if(index == 0)
		{
			AddHeadNode(nodeToAdd);
			return;
		}
		
		Insert(index, nodeToAdd);
	}
	
	
	public Object get(int index){
		//check user input
		if(index < 0 || index >= this.size)
			return null;
		
		Node rtn = getNode(index);
		return rtn.data;
	}
	
	/*
	 * User needs to validate input
	 */
	private Node getNode(int index)
	{
		Node rtn = this.head;
		for(int i = 0; i < index; ++i)
		{
			rtn = rtn.next;
		}
		return rtn;
	}
	
	public Object remove(int index){
		//check user input
		if(index < 0 || index >= this.size)
			return null;
		
		Node nodeToRemove = getNode(index);
		
		//如果是循环链表，则要通过判等的方式
		if(nodeToRemove.prev == null)//is the head
		{
			this.head = nodeToRemove.next;//could be null
		}
		
		if(nodeToRemove.next == null)//is the tail
		{
			this.tail = this.tail.prev;//could be null
		}
		
		--size;
		
		return nodeToRemove.data;
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		this.add(0, o);;
	}
	
	public void addLast(Object o){
		this.add(this.size, o);
	}
	
	public Object removeFirst(){
		return this.remove(0);
	}
	
	public Object removeLast(){
		return this.remove(this.size - 1);
	}
	
	public Iterator iterator(){
		return new LinkedListIterator(this);
	}
	
	/*
	 * User need to validate input
	 */
	private void Init(Node node)
	{
		head = node;
		tail = node;
		++size;
	}
	
	/*
	 * User need to validate input
	 */
	private void AddHeadNode(Node node)
	{
		this.head.prev = node;
		node.next = this.head;
		this.head = node;
		++size;
	}
	
	
	/*
	 * User need to validate input
	 */
	private void AddTailNode(Node node) {
		this.tail.next = node;
		node.prev = this.tail;
		this.tail = node;
		++size;
	}
	
	/*
	 * Head and Tail will not be changed
	 * So 0 < index < this.size && this.size > 1
	 * User validate input
	 */
	private void Insert(int index, Node node)
	{
		Node prevNode = getNode(index - 1);
		Node temp = prevNode.next;//could not possibly be null
		prevNode.next = node;
		node.prev = prevNode;
		node.next = temp;
	}
	
	//static class is not allowed to access it's 
	//outerclass's instance members without an outer class instance
	private static class Node{
		Object data;
		Node next;
		Node prev;
	}
	
	private class LinkedListIterator implements Iterator
	{
		private LinkedList data;
		
		private int index;
		
		public LinkedListIterator(LinkedList linkedList)
		{
			if(linkedList != null)
			{
				this.data = linkedList;
				this.index = -1;
			}
		}
		
		@Override
		public boolean hasNext() {
			if(this.data != null)
			{
				if(this.data.size() > index + 1)
				{
					return true;
				}
			}
			return false;
		}

		@Override
		public Object next() {
			if(this.hasNext())
			{
				return this.data.get(++index);
			}
			return null;
		}
		
	}
	
	
}
