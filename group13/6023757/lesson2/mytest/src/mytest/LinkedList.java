package mytest;

public class LinkedList {
	private int size = 0;
	private Node firstNode = null;
	private Node lastNode = null;
	
	public void add(Object o){
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = null;
		if(lastNode == null){
			firstNode = newNode;
			lastNode = newNode;
		}
		else{
			lastNode.next = newNode;
			lastNode = newNode;
		}
		this.size++;
	}
	public void add(int index , Object o){
		if(index < 0 || index >= this.size){
			throw new IndexOutOfBoundsException("Index: "+index+" is out of band");			
		}
		if(index == 0){
			addFirst(o);
		}
		else if(index == this.size-1){
			addLast(o);
		}
		else{
			Node pointer = this.firstNode;
			for(int i = 0;i < index - 1;i++){
				pointer = pointer.next;  
			}
			Node newNode = new Node();
			newNode.data = o;
			newNode.next = pointer.next;
			pointer.next = newNode;
			this.size++;			
		}
		
	}
	public Object get(int index){
		if(index < 0 || index >= this.size){
			throw new IndexOutOfBoundsException("Index: "+index+" is out of band");
		}
		Node pointer = this.firstNode;
		for(int i = 0;i < index;i++){
			pointer = pointer.next;  
		}
		return pointer.data;
	}
	public void remove(int index){
		if(index < 0 || index >= this.size){
			throw new IndexOutOfBoundsException("Index: "+index+" is out of band");			
		}
		if(index == 0){
			removeFirst();
		}
		else if(index == this.size-1){
			removeLast();
		}
		else{
			Node pointer = this.firstNode;
			for(int i = 0;i < index - 1;i++){
				pointer = pointer.next;  
			}
			Node toBeDelete = pointer.next;
			pointer.next = toBeDelete.next;
			toBeDelete.next = null;
			this.size--;			
		}
		
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = firstNode;
		firstNode = newNode;
		this.size++;			
	}
	public void addLast(Object o){
		add(o);
	}
	public void removeFirst(){
		Node NextNode = firstNode.next;
		firstNode.next = null;
		firstNode = NextNode;
		this.size--;
	}
	public void removeLast(){
		Node pointer = this.firstNode;
		for(int i = 0;i < this.size - 2;i++){
			pointer = pointer.next;  
		}
		Node toBeDelete = pointer.next;
		pointer.next = null;
		lastNode = pointer;
		toBeDelete.next = null;
		this.size--;			
	}
	
	private static class Node{
		Object data;
		Node next;
		
	}

}
