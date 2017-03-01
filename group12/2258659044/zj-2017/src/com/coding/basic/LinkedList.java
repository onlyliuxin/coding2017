package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	
	private int size = 0;
	
	public void add(Object o){
		
		Node addNode = new Node();
		addNode.data = o;
	    if(size==0){	    	
	    	head = addNode;
	    }else{
	    	//获取最后一个节点
	    	Node lastNode = getPointNode(size-1);
	    	lastNode.next = addNode;	    		    	
	    }
	    size++;
	}
	public void add(int index , Object o){
		
		Node addNode = new Node();
		addNode.data = o;
		if(index == 0){			
			addFirst(o);
			return;
		}		
        if(index == size){
			Node lastNode = getPointNode(size-1);
			lastNode.next = addNode;
		}else{
			Node pointNode = getPointNode(index);
			Node prePointNode = getPointNode(index-1);			
			prePointNode.next = addNode;
			addNode.next = pointNode;			
		}	
		size ++;
	}
	public Object get(int index){

		Node node = getPointNode(index);
		return node.data;
	}
	
	public Object remove(int index){
		
		Node pointNode = getPointNode(index);
		Node nextPointNode = getPointNode(index+1);
		if(index ==0){
			head = nextPointNode;			 
		}else{
			Node prePointNode = getPointNode(index-1);			
			prePointNode.next = nextPointNode;
		}						
		size --;
		return pointNode.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){	
		
		Node secondNode = head;	
		head = new Node();
		head.data = o;
		if(size>0){		
			head.next = secondNode;
		}				
		size ++;
	}
	
	public void addLast(Object o){
		add(o);
	}
	
	public Object removeFirst(){

		return remove(0);
	}
	
	public Object removeLast(){
		
		return remove(size-1);
	}
	public Iterator iterator(){
		return new Itr();
	}
	
	private class Itr implements Iterator{

		int cursor;
		@Override
		public boolean hasNext() {
			return cursor != LinkedList.this.size;
		}

		@Override
		public Object next() {
			
			int i = this.cursor;
			if (i >= LinkedList.this.size){
				throw new NoSuchElementException();
			}			
			this.cursor = (i + 1);
			return LinkedList.this.get(i);
		}
		
	}
	
	/**
	 * 获取指定的节点
	 * @return
	 */
	private Node getPointNode(int index){

		if(index>size){
			throw new IndexOutOfBoundsException("Index: "+index+",Size:"+size+"");	
		}
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}
	
	private static  class Node{
		Object data;
		Node next;
		
	}	
}