package basicstruct;

import java.util.NoSuchElementException;


public class LinkedList implements List {
	
	private Node head;
	
	private Node tail;
	
	private int size=0;
	
	public void add(Object o){
		addLast(o);
	}
	public void add(int index , Object o){
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index: "+index);
		}
		if (index == size) {	
			addLast(o);
		} else {
			Node temp = node(index);
			final Node pred = temp.previous;
			final Node newNode = new Node(o, temp, pred);
			temp.previous = newNode;
			if (pred == null){
				head = newNode;
			}
			else{
				pred.next = newNode;
			}
			size++;
		}
	}
	
	public Node node(int index) {
		//二分法查找
		if (index < (size >> 1)) {
			Node temp = head;
			for (int i = 0; i < index; i++){
				temp = temp.next;
			}
			return temp;
		} else {
			Node temp = tail;
			for (int i = size - 1; i > index; i--){
				temp = temp.previous;
			}
			return temp;
		}
	}
	
	public Object get(int index){
		if (index < 0 || index >=size) {
			throw new IndexOutOfBoundsException("index: "+index);
		}
		return node(index).data;
	}
	
	public Object remove(int index){
		if (index < 0 || index >=size) {
			throw new IndexOutOfBoundsException("index: "+index);
		}
		return deleteElement(node(index));
	}
	
	private Object deleteElement(Node node) {
		Object element = node.data;
		Node next = node.next;
	    Node prev = node.previous;
		if (prev == null) {
			head = next;
		}else{
			prev.next = next;
			node.previous = null;
		}
		if(next == null) {
			tail = prev;
		}else {
			next.previous = prev;
			node.next = null;
		}
		node.data = null;
		size--;
		return element;
	}
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node h = head;
		Node newNode = new Node(o, h, null);
		head = newNode;
		if (h == null){
			tail = newNode;
		}else{
			h.previous = newNode;
		}
		size++;
	}
	
	public void addLast(Object o){
		Node t = tail;
		Node node = new Node(o, null, t);
		tail = node;
		if (t == null) {
			head = node;
		} else {
			t.next = node;
		}
		size++;
	}
	
	public Object removeFirst(){
		final Node h = head;
		if (h == null) {
			throw new NoSuchElementException("No such element");
		}
		final Object element = h.data;
		final Node next = h.next;
		h.data = null;
		h.next = null;
		head = next;
		if (next == null) {
			tail = null;
		} else {
			next.previous = null;
		}
		size--;
		return element;
	}
	
	public Object removeLast(){
		Node t = tail;
		if (t == null){
			throw new NoSuchElementException("No such element");
		}
		final Object element = t.data;
		final Node prev = t.previous;
		t.data = null;
		t.previous = null;
		tail = prev;
		if (prev == null) {
			head = null;
		} else {
			prev.next = null;
		}
		size--;
		return element;
	}
	public boolean isEmpty(){
		return size==0;
	}
	public Iterator iterator(){
		return null;
	}
	
	private static  class Node{
		Object data;
		Node next;
		Node previous;
		public Node() {
			super();
		}
		public Node(Object data, Node next, Node previous) {
			super();
			this.data = data;
			this.next = next;
			this.previous = previous;
		}
	}
}