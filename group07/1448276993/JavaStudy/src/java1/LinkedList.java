package java1;

public class LinkedList implements List {
	
	private Node head=null;
	private Node last=null;
	private int size=0;
	
	public void add(Object o){
		Node newNode = new Node(o,null);
		if(head==null){
			head = newNode;
			last = newNode;
		}else{
			addLast(o);
        }
        size++;
	}
	public void add(int index , Object o){
		if(index<0&&index>size+1){
			System.out.println("Wrong Input!");
		}
		Node newNode = new Node(o,null);
		if(index==0){
			addFirst(o);
		}else if(index==size){
			addLast(o);
		}else{
			Node a=head;
			for(int i=0;i<index;i++){
				a=a.next;
				if(i==size-1){
					newNode.next=a.next;
					a.next=newNode;
				}
			}
		}
		size++;
	}
	public Object get(int index){
		if(head==null){
			return head;
		}
		Node a=head;
		for(int i=0;i<index;i++){
			a=a.next;
			if(i==index){
				break;
			}
		}
		return a;
	}
	public Object remove(int index){
		if(head==null){
			return head;
		}
		Node a=head;
		for(int i=0;i<index;i++){
			a=a.next;
			if(i==index-1){
				a.next=a.next.next;
				a.next.next=null;
			}
		}
		return a;
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		Node newNode = new Node(o,null);
		if(head==null){
			head=newNode;
			last=newNode;
		}else{
			newNode.next=head;
			head=newNode;
		}
		size++;
	}
	public void addLast(Object o){
		Node newNode = new Node(o,null);
		if(head==null){
			head=newNode;
		}else{
			last.next=newNode;
		}
		last=newNode;
		size++;
	}
	public Object removeFirst(){
		if(head==null){
			System.out.println("List Is Empty!");
		}else{
			head=head.next;
		}
		size--;
		return head;
	}
	public Object removeLast(){
		if(head==null){
			System.out.println("List Is Empty!");
		}else{
			Node a=head;
			for(int i=0;i<size;i++){
				a=a.next;
				if(i==size-1){
					last=a;
					a.next=null;
				}
			}
		}
		size--;
		return head;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
		public Node(Object e,Node node){
			this.data = e;
			this.next = node;
		}
	}
}
