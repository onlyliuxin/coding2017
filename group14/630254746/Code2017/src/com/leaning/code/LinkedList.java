package com.leaning.code;






public class LinkedList implements List {

	private Node head;
	
	private Node last;
	
	private int size;
	
	
	void linkLast(Object o){
		Node lastNode = last;
        Node newNode = new Node(lastNode, o, null);
        last = newNode;
        if (lastNode == null)
            head = newNode;
        else
        	lastNode.next = newNode;
        size++;
	} 
	
	void linkHead(Object o){
		Node headNode = head;
        Node newNode = new Node(null, o, headNode);
        head = newNode;
        if (head == null)
            last = newNode;
        else
        	head.prev = newNode;
        size++;
	}
	
	@Override
	public void add(Object o) {
		linkLast(o);
	}

	@Override
	public void add(int index, Object o) {
		if (index < 0 || index >= size) {
			throw new RuntimeException("数组下标越界");
		}
		Node n = find(index);
		Node pred = n.prev;
	    Node newNode = new Node(pred, o, n);
        n.prev = newNode;
        if (pred == null)
            head = newNode;
        else
            pred.next = newNode;
        size++;
	}

	@Override
	public Object get(int index) {
		return find(index).item;
	}

	Node find(int index){
		if (index < (size >> 1)) {
            Node n = head;
            for (int i = 0; i < index; i++)
                n = n.next;
            return n;
        } else {
            Node n = last;
            for (int i = size - 1; i > index; i--)
                n = n.prev;
            return n;
        }
	} 
	
	@Override
	public Object remove(int index) {
		Node n = find(index);
		Object o = n.item;
		final Node prev = n.prev;
		final Node next = n.next;
		if (null != prev) {
			prev.next = next;
		}
		if (null != next) {
			next.prev = prev;
		}
		n.item = null;
		n.next = null;
		n.prev = null;
		size-- ;
		return o;
	}

	@Override
	public int size() {
		return size;
	}

	public void addFrist(Object o){
		linkHead(o);
	}
	
	public void addLast(Object o){
		linkLast(o);
	}
	
	public Object removeFirst(){
		Object o = head.item;
		Node n = head.next;
		head = n;
		if (n == null)
            last = null;
        else
            n.prev = null;
		size --;
		return o;
	}
	
	public Object removeLaset(){
		Object o = last.item;
		Node p = last.prev;
		last = p;
		if (p == null)
            head = null;
        else
            p.next = null;
		size --;
		return o;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	public static class Node{
		Object item;
	    Node next;
	    Node prev;

	    Node(Node prev, Object element, Node next) {
	        this.item = element;
	        this.next = next;
	        this.prev = prev;
	    }
	}
}
