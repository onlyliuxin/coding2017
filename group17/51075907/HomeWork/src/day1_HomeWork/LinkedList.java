package day1_HomeWork;


public class LinkedList implements List {
	
	private Node head;
	
	private int size = 0;
	
	private int modCount = 0;
	
	public void add(Object o){
		add( size(),o);
		return true;
	}
	public void add(int index , Object o){
		
	}
	public Object get(int index){
		Node p;
		if ( index <0||index> size())
			throw new IndexOutOfBoundsException();
		
		if (index < size()/2)
		{
			p=beginMarker.next;
			for (int i =0;i<index;i++)
				p=p.next;
		}
		else
		{
			p=endMarker;
			for (int i=size();i>index;i--)
				p=p.prev;
		}
		return p;
	}
	public Object remove(Node p){
		p.next.prev =p.prev;
		p.prev.next=p.next;
		int size;
		size--;
		int modCount;
		modCount++;
		return p.data;
	}
	
	public int size(){
		return -1;
	}
	
	public void addFirst(Node p,Object o){
		Node newNode= new Node (o,p.prev,p);
		newNode.prev.next= newNode;
		p.prev =newNode;
		size++;
		modCount++;
	}
	public void addLast(Object o){
		
	}
	public Object removeFirst(int index){
		return remove( get(index));
	}
	public Object removeLast(){
		return null;
	}
	public java.util.Iterator iterator(){
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements java.util.Iterator{
     private Node current=beginMarker.next;
     private int expectedModCount=modCount;
     private boolean okTORemove=false;
     
     public boolean haNext()
     {
    	 return current!= endMarker;
     }
     public Object next()
     {
    	 if (modCount !=expectedModCount)
    		 throw new java.util.ConcurrentModificationException();
    	 if (!=hasNext())
    		 throw new java.util.NoSuchElementException();
    	 
    	 
     }
	}
	
	
	private static  class Node{
		public Node(Object o, Node p, Node n)
		{
			data =o; next=n; prev=p;
		}
		public Object data;
		public Node next;
		public Node prev;
		
	}
}
