package com.coding.basic;

public class LinkedList implements List {

	private Node first;
	private Node last;
	
	private int size = 0;

	public void add(Object o) {
		
		if (first == null) {
			addFirst(o);
		}else{
			addLast(o);
		}
		
	}

	public void add(int index, Object o) {
		
		isIndexOutOfBounds(index);		
		
		Node n = null;
		if(first == null){
			addFirst(o);
		}else if(index == size-1){
			addLast(o);
		}else{
			n = getNode(index-1);
			Node n1 = n.next;
			n.next = new Node(o);
			n.next.next = n1;
			size++;
		}
	}

	public Object get(int index) {
		
		isIndexOutOfBounds(index);
		
		return getNode(index).data;
	}

	public Node getNode(int index) {
		
		if(index == 0) return first;
		if(index == size-1) return last;
		
		Node n = first;
		for (int i = 0; i <= index ; i++) {
			if (i == index) {
				break;
			}
			n = n.next;
		}
		return n;
	}
	
	public Object remove(int index) {
		
		isIndexOutOfBounds(index);
		
		
		Node n = getNode(index-1);
		Object o = null;
		if(index == 0){
			o = first.data;
			first = first.next;
		}else if(index == size-1){
			n.next = null;
			last = n;
		}else{
			n.next = n.next.next;
		}
		size--;
		
		return o;
	}

	private void isIndexOutOfBounds(int index){
		if(index >= size || index < 0) throw new IndexOutOfBoundsException();
	}
	
	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node n = first;
		first = new Node(o);
		if(n != null){
			first.next = n;
		}else{
			last = first;
		}
		size++;
	}

	public void addLast(Object o) {
		Node n = new Node(o);
		if(last != null){
			last.next = n;
		}else{
			first = n;
		}
		last = n;
		size++;
	}

	public Object removeFirst() {
		return null;
	}

	public Object removeLast() {
		return null;
	}

	public Iterator iterator() {
		return null;
	}

	private static class Node {
		Object data;
		Node next;
		
		public Node(Object data){
			this.data = data;
		}
		
		public Node(){}
		
	}
	
	public static void main(String[] args) {
		LinkedList l = new LinkedList();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(2, 5);
		l.remove(0);
		
		for(int i=0;i<l.size();i++){
			System.out.println(l.get(i));
		}
	}
	
}
