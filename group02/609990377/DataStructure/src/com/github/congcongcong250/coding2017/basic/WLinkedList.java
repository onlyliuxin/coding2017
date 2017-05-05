package com.github.congcongcong250.coding2017.basic;

import java.util.NoSuchElementException;

public class WLinkedList implements WList {
	
	private Node head;
	private int size;
	
	public WLinkedList(){
		head = new Node();
		size = 0;
	}
	
	public void add(Object o){
		addLast(o);
	}
	
	public void add(int index , Object o){
		//Check bound
		checkIndex(index);
		
		Node nx = this.find(index);
		Node pr = nx.previous;
		Node in = new Node(o,pr,nx);
		nx.previous = in;
		pr.next = in;
		size++;
	}
	
	public Object get(int index){
		//Check bound
		checkIndex(index);
		
		return this.find(index).data;
	}
	
	public Object remove(int index){
		//Check bound
		checkIndex(index);
		Node rem = this.find(index);
		
		Node pr = rem.previous;
		Node nx = rem.next;
		pr.next = nx;
		nx.previous = pr;
		
		Object ret = rem.data;
		rem.previous = null;
		rem.next = null;
		rem.data = null;
		size--;
		return ret;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node nx = head.next;
		Node in = new Node(o,head, nx);
		head.next = in;
		nx.previous = in;
		size++;
	}
	
	public void addLast(Object o){
		Node last = head.previous;
		Node in = new Node(o,last,head);
		last.next = in;
		head.previous = in;
		
		size++;
	}
	public Object removeFirst(){
		return remove(0);
	}
	public Object removeLast(){
		return remove(size-1);
	}
	public WIterator wIterator(){
		return new ListItr();
	}
	public void clear(){
		for (Node x = head; x != null; ) {
			Node next = x.next;
			x.data = null;
			x.next = null;
            x.previous = null;
		    x = next;
		}
	}
	
	private void checkIndex(int index){
		if(index >= size || index < 0){
			throw new IndexOutOfBoundsException("Index:"+index+" Size:"+size);
		}
	}
	
	private Node find(int index){
		Node tra = head;
		
		//If index < size/2
		if( index < (size >> 1)){
			for(int i = 0; i <= index; i++){
				tra = tra.next;
			}
		}else{
			for(int i = size; i > index; i--){
				tra = tra.previous;
			}
		}
		return tra;
	}
	
	private static class Node{
		Object data;
		Node next;
		Node previous;
		
		public Node(){
			data = null;
			next = this;
			previous = this;
		}
		
		public Node(Object obj,Node pre, Node nx){
			data = obj;
			next = nx;
			previous = pre;
		}
		
		
	}
	
	private class ListItr implements WIterator{
		//Point to next node
		Node cursor;
		int nextIndex;

		public ListItr(){
			cursor = head.next;
			nextIndex = 0;
		}
		
		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		@Override
		public Object next() {
			checkBound();
			Node re = cursor;
			cursor = cursor.next;
			nextIndex++;
			return re.data;
		}
		
		public Object previous() {
			Node re = cursor.previous.previous;
			cursor = cursor.previous;
			nextIndex--;
			return re.data;
		}

		@Override
		public void remove() {
			//Check bound
			checkBound();
			WLinkedList.this.remove(--nextIndex);
			
		}
		
		private void checkBound(){
			if(nextIndex >= size){
				throw new NoSuchElementException("Iterates to the end");
			}
		}
	}
}
