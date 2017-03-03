public class LinkedList implements List {
	
	private Node head;
	private int size = 0;
	
	public void add(Object o){
		if (head == null) {
			head = new Node(o);
		} else {
			Node pointer = head.next;
			while (pointer.next != null) {
				pointer = pointer.next;
			}
			pointer.next = new Node(o);
		}
		size++;
	}
	public void add(int index , Object o){
		Node pointer = head;
		for (int i = 0; i < index - 1; i++) {
			pointer = pointer.next;
		}
		Node nNode = new Node(o, pointer.next);
		pointer.next = nNode;
		size++;
	}
	public Object get(int index){
		Node pointer = head;
		for (int i= 0; i < index; i++) {
			pointer = pointer.next;
		}
		return pointer;
	}
	public Object remove(int index){
		Node pointer = head;
		for (int i = 0; i < index - 1; i++) {
			pointer = pointer.next;
		}
		Node temp = pointer.next;
		pointer.next = temp.next;
		size--;
		return temp;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
        head = new Node(o, head);
	}
	public void addLast(Object o){
		Node pointer = head;
		while (pointer.next != null) {
			pointer = pointer.next;
		}
		pointer.next = new Node(o);
	}
	public Object removeFirst(){
		Node temp = head;
		head = head.next;
		return temp;
	}
	public Object removeLast(){
		Node pointer = head;
		while (pointer.next.next != null) {
			pointer = pointer.next;
		}
		Node temp = pointer.next;
		pointer.next = null;
		return temp;
	}
	public myIterator iterator(){
		return new myIterator(this);
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		Node (Object o, Node n) {
			data = o;
			next = n;
		}
		Node (Object o) {
			data = o;
			next = null;
		}
	}
}
