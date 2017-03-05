package com.coding.basic;

public class LinkedList implements List {
	
	private int size;
	private int modCount = 0;
	private Node beginMarker;
	private Node endMarker;
	
	private static class Node{
		public Node( Object d, Node p, Node n) {
			data = d;
			prev = p;
			next = n;
		}
		public Object data;
		public Node prev;
		public Node next;
	}
	
	public LinkedList () {
		doClear();
	}
	
	public void clear() {
		doClear();
	}
	
	private void doClear() {
		beginMarker = new Node(null, null, null);
		endMarker = new Node(null, beginMarker, null);
		beginMarker.next = endMarker;
		
		size = 0;
		modCount++;
	}
	
	public boolean add(Object o){
		add(size(), o);
		return true;
	}
	public boolean add(int index , Object o){
		/*if (index < 0 && index > size()) {
			throw new IndexOutOfBoundsException();
		}*/
		addBefore( getNode( index, 0, size()), o);
		return true;
	}
	
	public void addFirst(Object o){
		add(0, o);
	}
	
	public void addLast(Object o){
		add(size(), o);
	}
	
	public Object removeFirst(){
		return remove(0);
	}
	
	public Object removeLast(){
		return remove(size());
	}
	
	public Object get(int index){
		return getNode(index).data;
	}
	public Object remove(int index){
		return remove(getNode(index));
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	private void addBefore( Node p, Object o ) {
		Node newNode = new Node(o, p.prev, p );
		newNode.prev.next = newNode;
		p.prev = newNode;
//		p.prev = p.prev.next = new Node(o, p.prev, p);
		size++;
		modCount++;
	}
	
	private Node getNode(int idx) {
		return getNode(idx, 0, size() - 1);
	}
	
	private Node getNode(int idx, int lower, int upper) {
		Node p;
		if (idx < lower || idx > upper) {
			throw new IndexOutOfBoundsException();
		}
		
		if (idx < size() / 2) {
			p = beginMarker.next;
			for (int i = 0; i < idx; i++) {
				p = p.next;
			}
		} else {
			p = endMarker.prev;
			for (int i = size(); i > idx; i--) {
				p = p.prev;
			}
		}
		return p;
	}
	
	private Object remove( Node p ) {
		p.prev.next = p.next;
		p.next.prev = p.prev;
		modCount++;
		size--;
		
		return p.data;
	}
	
	
	public java.util.Iterator iterator() {
		return new LinkListIterator();
	}
	
	private class LinkListIterator implements java.util.Iterator {
		
		private Node current = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;

		@Override
		public boolean hasNext() {
			return current != endMarker;
		}

		@Override
		public Object next() {
			if ( modCount != expectedModCount ) {
				throw new java.util.ConcurrentModificationException();
			}
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			Object nextItem = current.next;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}
		
		public void remove() {
			if ( modCount != expectedModCount ) {
				throw new java.util.ConcurrentModificationException();
			}
			if ( !okToRemove ) {
				throw new IllegalStateException();
			}
			LinkedList.this.remove(current.prev);
			expectedModCount++;
			okToRemove = false;
			
		}
	}
}

class TestLinkLedList {
	
	public static void main(String[] args) {
		LinkedList lst = new LinkedList();
		for (int i = 0; i < 10; i++) {
			lst.add(i);
		}
		for (int i = 20; i < 30; i++) {
			lst.add(0, i);
		}
		
		lst.remove(0);
		lst.remove(lst.size()-1);
		
		System.out.println(lst);
		
		java.util.Iterator itr = lst.iterator();
		while (itr.hasNext()) {
			itr.next();
			itr.remove();
			System.out.println(lst);
		}
	}
}
