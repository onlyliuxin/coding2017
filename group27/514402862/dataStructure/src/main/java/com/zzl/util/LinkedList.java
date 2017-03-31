package com.zzl.util;

import java.util.NoSuchElementException;

public class LinkedList implements List{
	
	private int size = 0;
	private Node head;
	private Node last;
	
	void linkFirst(Object o){
		final Node h = head;
		final Node newNode = new Node(o, h);
		head = newNode;
		if(h == null)
			last = newNode;
		
		size++;
	}
	
	void linkLast(Object o){
		final Node l = last;
		final Node newNode = new Node(o, null);
		last = newNode;
		if(l == null)
			head = newNode;
		else
			l.next = newNode;
		
		size++;
	}
	
	void linkBefore(Object o, Node succ){
		final Node next = succ.next;
		final Node newNode = new Node(o, next);
		succ.next = newNode;
		size++;
	}
	
	@Override
	public void add(Object o) {
		linkLast(o);
	}
	
	@Override
	public void add(int index, Object o) {
		rangeCheck(index);
		
		if(index == 0)
			linkFirst(o);
		else if(index == size)
			linkLast(o);
		else
			linkBefore(o, node(index - 1));
	}

	@Override
	public Object get(int index) {
		rangeCheck(index);
		
		return node(index).data;
	}

	@Override
	public Object remove(int index) {
		rangeCheck(index);
		
		return changeNode(node(index), index);
	}

	@Override
	public int size() {
		return size;
	}
	
	public void addFirst(Object o){
		linkFirst(o);
	}
	
	public Object removeFirst(){
		return remove(0);
	}
	
	public Object removeLast(){
		return remove(size - 1);
	}
	
	public Iterator iterator(){
		return new LinkedListIterator();
	}
	
	private Object changeNode(Node oldNode, int index){
		Object oldNodeValue = oldNode.data;
		if(index == 0){
			head = oldNode.next;
		}else {
			Node oldNodePrev = node(index - 1);
			oldNodePrev.next = oldNode.next;
			if(null == oldNode.next){
				last = oldNodePrev;
			}
		}
		oldNode.data = null;
		oldNode.next = null;
		
		size--;
		return oldNodeValue;
	}
	
	private void rangeCheck(int index){
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException("Index:" + index + "size:" + size);
	}
	
	Node node(int index){
		Node x = head;
		for(int i = 0; i < index; i++)
			x = x.next;
		return x;
	}
	
	private static class Node{
		Object data;
		Node next;
		
		private Node(Object o, Node l){
			this.next = l;
			this.data = o;
		}
	}
	
	private class LinkedListIterator implements Iterator{
        private Node lastReturned;
        private Node next;
		private int nextIndex;
		
		LinkedListIterator(){
			next = LinkedList.this.head;
			nextIndex = 0;
		}
		
		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		@Override
		public Object next() {
            if (!hasNext())
                throw new NoSuchElementException();
			
            lastReturned = next;
            next = next.next;
            nextIndex++;
			return lastReturned.data;
		}
		
	}
}
