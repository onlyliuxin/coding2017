package com.skomefen.list;

public class LinkedListiterator implements Iterator {

	private LinkedList link = null;
	private int index = 0;
	public LinkedListiterator(LinkedList link) {
		this.link = link;
	}
	public boolean hasNext() {
		if(link==null){
			return false;
		}
		if(index<link.size()){
			return true;
		}
		return false;
	}

	public Object next() {
	
		return link.get(index++);
		
	}
}
