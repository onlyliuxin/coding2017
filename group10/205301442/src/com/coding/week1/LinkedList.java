package com.coding.week1;

public class LinkedList implements List {
	private int size;
	private Node first;
	private Node last;
	public static class Node{
		Object data;
		Node next;
		Node prev;
		public Node(Node prev,Object data,Node next){
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
	
	@Override
	public void add(Object o) {
		final Node l = last;
		Node newNode = new Node(last,o,null);
		last = newNode;
		if(first==null){
			first = newNode;
		}else{
			l.next = newNode;
		}
		size++;

	}

	@Override
	public void add(int index, Object o) {
		
		if(index>size){
			System.out.println("Exception in thread \""+Thread.currentThread()+"\" java.lang.IndexOutOfBoundsException:Index:"+index+",Size:"+size);
			return;
		}
		Node newNode = new Node(null,o,null);
		Node nodePre = node(index-1);
		Node oldNode = node(index);
		if(nodePre!=null){
			nodePre.next =newNode;
			newNode.prev = nodePre;
		}else{
			first = newNode;
		}
		if(oldNode!=null){
			oldNode.prev = newNode;
			newNode.next = oldNode;
		}else{
			last = newNode;
		}
		size++;
	}

	@Override
	public Object get(int index) {
		if(!rangeCheck(index)){
			return null;
		}
		
		return node(index).data;
	}

	@Override
	public Object remove(int index) {
		if(!rangeCheck(index)){
			return null;
		}
		Node prevNode = node(index-1);
		Node nextNode = node(index+1);
		Node rmNode = node(index);
		if(prevNode!=null){
			prevNode.next = nextNode;
		}else{
			first=nextNode;
		}
		if(nextNode!=null){
			nextNode.prev = prevNode;
		}else{
			last = prevNode;
		}
		size--;
		return rmNode.data;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	public Object head(){
		return first.data;
	}
	public Object last(){
		return last.data;
	}
	public void addFirst(Object o){
		add(0,o);
	}
	public void addLast(Object o){
		add(size,o);
	}
	public Object removeFirst(){
		Node f = first;
		remove(0);
		return f.data;
	}
	public Object removeLast(){
		Node l = last;
		remove(size-1);
		return l.data;
	}
	public Node node(int index){
		if(index<0){
			return null;
		}
		Node x =null;
		if(index<(size<<1)){
			 x = first;
			for(int i=0;i<index;i++ ){
				x = x.next;
			}
		}else{
			x = last;
			for(int i=size-1;i>index;i--){
				x = x.prev;
			}
		}
		return x;
	}
	public boolean rangeCheck(int index){
		
		if(index>size-1||index<0){
			System.err.println("Exception in thread \""+Thread.currentThread()+"\" java.lang.IndexOutOfBoundsException:Index:"+index+",Size:"+size);
			return false;
		}
		return true;
	}
	public Ito iterator(){
		return new Ito();
	}
	public class Ito implements Iterator{
		int cursor;
		@Override
		public boolean hasNext() {
		    if(cursor!=size){
		    	return true;
		    }
			return false;
		}

		@Override
		public Object next() {
			Object o=node(cursor).data;
			cursor++;
			return o;
		}
		
	}
}
