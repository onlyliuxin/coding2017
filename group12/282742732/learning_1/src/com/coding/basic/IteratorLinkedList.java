package com.coding.basic;

import com.coding.basic.LinkedList.Node;

public class IteratorLinkedList implements Iterator{

	Node node ;
	public IteratorLinkedList(Node node){
		this.node = node;
	}
	
	@Override
	public boolean hasNext() {
		
		if (node == null) {
			return false;
		}
		return true;
		
	}

	@Override
	public Object next() {
		Object obj = this.node.data;
		node = node.next;
		return obj;
	}
	
	
}
