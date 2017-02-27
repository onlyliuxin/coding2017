package basic;


public class LinkedList implements List {
	
	private Node head;
	
	public void add(Object o){
		Node newNode= new Node();
		newNode.data = o;
		newNode.next = null;
		
		if (head == null ){
			head= newNode;
		}
		
		Node currentNode = head;
		while (currentNode.next != null ){
			currentNode = currentNode.next ; 
		} 
		

		currentNode.next =newNode;	
			
	}
	public void add(int index , Object o){
		int oldSize = size();
		if (index >= oldSize ||index < 0) {
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
	}
	
	public Object get(int index){
		int oldSize = size();
		if (index >= oldSize ||index < 0) {
			return null;
		}
		Node currentNode = head;
		for (int i=0; i <index; i++){
			currentNode =currentNode.next;
		}
		return currentNode.data;
		
	}
	public Object remove(int index){
		int size = size();
		if (index >= size ||index < 0) {
			return null;
		}

		Node currentNode = head;
		if (index ==0 ){
			head =head.next;
			return currentNode.data;
		}
		Node PrevNode = null;
		for (int i=0; i <index; i++){
			PrevNode= currentNode;
			currentNode =currentNode.next;
		}
		PrevNode.next = currentNode.next;
		return currentNode.data;
	}
	
	public int size(){
		if (head == null){
			return 0;
		}
		
		Node currentNode = head; 
		int i =0;
		while (currentNode.next != null){
			currentNode =currentNode.next;
			i++;
		}
		return i;
	}
	
	public void addFirst(Object o){
		Node newNode = new Node();
		newNode.data =o;
		newNode.next = head;
		
		head = newNode;
		
	}
	public void addLast(Object o){
		Node currentNode = head;
		while (currentNode.next != null){
			currentNode=currentNode.next;
			
		}
		
		Node newNode = new Node();
		newNode.data= o;
		newNode.next =null;
		
		currentNode.next = newNode;
		
	}
	public Object removeFirst(){
		if (head ==null){
			return null;
		}
		else {
			Node firstNode = head;
			head = firstNode.next;	
			return firstNode;
		}
	}
	public Object removeLast(){
		int size = size();
		if (head ==null ){
			return null;
		}
		if (size ==1){
			Node tempnode = head;
			head = null;
			return tempnode.data;
		}
		Node currentNode = head.next;
		Node PrevNode= head;
		
		while (currentNode.next != null){
			PrevNode = currentNode;
			currentNode=currentNode.next;
		}
		PrevNode.next= null;
		 
		
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
