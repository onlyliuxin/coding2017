package task1.basic;

public class LinkedList implements List{
	
	private Node head = new Node();
	private int size;
	
	private static class Node{
		Object data;
		Node next;
	}
	

	public void add(Object o){
		addLast(o);
		
	}
	public void add(int index ,Object o){
		if(index<0 || index>size -1){
			throw new ArrayIndexOutOfBoundsException();
		}
		
		Node node=head;
		
		Node newNode=new Node();
		
		for(int i =0;i<index;i++){
			node=node.next;
		}
		newNode.data=o;
		
		if(index==0){
			addFirst(o);
			
		}else{
			newNode.next=node.next;
			node.next=newNode;
			
		}
		size++;
		
	}
	public Object get(int index){
		
		Node node=head;
		for(int i=0;i<index;i++){
			node=node.next;
		}
		return node.data;
		
	}
	public Object remove(int index){
		
		Node indexNode=head;
		Node node=head;
		
		for(int i=0;i<index;i++){
			indexNode=indexNode.next;
			
		}
		for(int i=0;i<index-1;i++){
			node=node.next;
		}
		
		node.next=indexNode.next;
		
		indexNode.next=null;
		
		size--;
		
		return indexNode.data;
		
				
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		
		Node newNode=new Node();
		newNode.data=o;
		newNode.next=head;
		head=newNode;
		size++;
	}
	public void addLast(Object o){
		
		Node newNode= new Node();
		
		Node node= head;
		
		for (int i=0;i<size ;i++){
			node=node.next;
		}
		
		newNode.data=o;
		node.next=newNode;
		size++;
		
		
	}
	public Object removeFirst(){
	
		head=head.next;
		
		size--;
		
		return head.data;
		
		
	}
	
	public Object removeLast(){
	
		Node node=head;
		for(int i=0;i<size-1 ;i++){
			node=node.next;
		}
		
		Node lastNode=node.next;
		node.next=null;
		
		size--;
		
		return lastNode.data;
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
