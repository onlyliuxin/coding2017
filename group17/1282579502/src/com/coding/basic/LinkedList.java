package com.coding.basic;

public class LinkedList implements List {
	
	private Node head = null;
	private Node tail = null;
	private int size = 0;
	
	public void add(Object o){
		if(head == null){
			head = new Node(o);
			tail = head;	
		}else{
			tail.next = new Node(o);
			tail = tail.next;
		}
		size ++;
	}
	/**
	 * Add to index from 0 to size
	 * Add to index at 0 is equivalent to addFirst
	 * Add to index at size is equivalent to add and addLast
	 * 
	 */
	public void add(int index , Object o){
		if(index < 0 || index >size+1) throw new IndexOutOfBoundsException(index + ": Invalid Index");
		Node newNode = new Node(o);
		//manage head node
		if(index == 0){
			if(head == null){
				this.add(o);
			}
			else{
				newNode.next = head;
				head = newNode;
				size++;
			}
		}
		else if (index == size){
			this.add(o);
		}
		else{
			Node prevNode = getNodeAt(index-1);
			newNode.next = prevNode.next;
			prevNode.next = newNode;
			size ++;
		}
	}
	public Object get(int index){
		Node c = getNodeAt(index);
		if(c == null ) return null;
		return c.data;
	}
	
	private Node getNodeAt(int index){
		if(index < 0 || index >size-1) throw new IndexOutOfBoundsException(index + ": Invalid Index");
		Node c = head;
		while(index-- > 0 ){
			if(c != null) c = c.next;
			else return null;
		}
		return c;
	}
	
	public Object remove(int index){
		if(index<0 || index >size-1) throw new IndexOutOfBoundsException(index + ": Invalid Index");
		Node ret = null;
		if(index == 0){
			ret = head;
			head = head.next;
			size --;
		}else if(index == size -1){
			Node nodeBeforeTail = getNodeAt(index -1);
			ret = tail;
			nodeBeforeTail.next = null;
			size --;
		}else{
			Node nodeBeforeTarget = getNodeAt(index -1);
			Node target = nodeBeforeTarget.next;
			ret = target;
			nodeBeforeTarget.next = target.next;
			size --;
		}

		return ret.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		this.add(0, o);
	}
	
	public void addLast(Object o){
		this.add(o);
	}
	
	public Object removeFirst(){
		return remove(0);
	}
	
	public Object removeLast(){
		return remove(size-1);
	}
	
	public Iterator iterator(){
		return new LinkedListIterator(this);
	}
	
	private  class Node{
		public Object data = null;
		public Node next = null;
		public Node(Object o){
			data = o;
		}
	}
	
	private static class LinkedListIterator implements Iterator{

		Object[] oArray = null;
		int cursor = 0;
		public LinkedListIterator(LinkedList ll){
			if(ll == null) throw new NullPointerException("Linkedlist Object is NULL");
			oArray = new Object[ll.size()];
			for(int i = 0; i< ll.size(); i++){
				oArray[i] = ll.get(i);
			}
		}
		
		@Override
		public boolean hasNext() {
			if(cursor < oArray.length){
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			Object o = oArray[cursor];
			cursor ++;
			return o;
		}
		
	}
}
