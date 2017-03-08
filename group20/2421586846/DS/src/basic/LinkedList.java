package basic;


public class LinkedList implements List {
	
	private Node head;
	
	private int size;
	//加到最后 
	public void add(Object o){
		Node newNode= new Node();
		newNode.data = o;
		newNode.next = null;
		
		if (head == null ){
			head= newNode;
			size++;
			return;
		}
		
		Node currentNode = head;
	
		while (currentNode.next != null ){
			currentNode = currentNode.next ; 
		} 
		/*
		for (int i=0;i< size;i++){
			currentNode = currentNode.next ; 
		}
	 */
		currentNode.next =newNode;	
		size++;
	}
	
	public void add(int index , Object o){
		if (index >= size ||index < 0) {
			return;
		}
		
		Node newNode= new Node();
		newNode.data = o;
		Node PrevNode =null; 
		
			if (index ==0 ){

				Node tempNode = head  ; 
				head = newNode;
				newNode.next = tempNode;
				
			}
			else {
				Node currentNode = head;
				for (int i=0; i <index; i++){
					PrevNode = currentNode;
					currentNode =currentNode.next;					
				}
				
				PrevNode.next = newNode;
				newNode.next = currentNode;
			}	
			size++;
	}
	
	public Object get(int index){
		
		if (index >= size ||index < 0) {
			return null;
		}
		Node currentNode = head;
		for (int i=0; i <index; i++){
			currentNode =currentNode.next;
		}
		return currentNode.data;
		
	}
	public Object remove(int index){
 
		if (index >= size ||index < 0) {
			return null;
		}

		Node currentNode = head;
		if (index ==0 ){
			head =head.next;
			size--;
			return currentNode.data;
		}
		Node PrevNode = null;
		for (int i=0; i <index; i++){
			PrevNode= currentNode;
			currentNode =currentNode.next;
		}
		PrevNode.next = currentNode.next;
		size--;
		return currentNode.data;
	}
	
	public int size(){
		
		return size;
	}
	
	public void addFirst(Object o){
		Node newNode = new Node();
		newNode.data =o;
		newNode.next = head;
		
		head = newNode;
		size++;
	}
	public void addLast(Object o){
		if (head == null){
			head = new Node();
			head.data= o;
			size ++;
			return;
		}
		Node currentNode = head;
		while (currentNode.next != null){
			currentNode=currentNode.next;
			
		}
		
		Node newNode = new Node();
		newNode.data= o;
		newNode.next =null;
		
		currentNode.next = newNode;
		size++;
	}
	public Object removeFirst(){

		size--;
		if (head ==null){
			return null;
		}
		else {
			Node firstNode = head;
			head = firstNode.next;	
			return firstNode.data ;
		} 
	}
	public Object removeLast(){
		
		if (head ==null ){
			return null;
		}
		if (size ==1){
			Node tempnode = head;
			head = null;
			size--;
			return tempnode.data;
		}
		Node currentNode = head.next;
		Node PrevNode= head;
		
		while (currentNode.next != null){
			PrevNode = currentNode;
			currentNode=currentNode.next;
		}
		PrevNode.next= null;
		 
		size--;
		return currentNode.data ;
		
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
	}
}
