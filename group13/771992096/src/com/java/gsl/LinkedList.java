package com.java.gsl;


public class LinkedList implements List {
	
	private Node head;
	private int size = 0;
	
	public void add(Object o){
		
	}
	public void add(int index , Object o){
		if(index < 0 || index > size ){
			return;
		}
		Node node = (Node) (index==size ? head : get(index));
		Node prenode = node.pre;
//		Node l = last;
//	    Node newNode = new Node(l, o, null);
//	        last = newNode;
//	        if (l == null)
//	            head = newNode;
//	        else
//	            l.next = newNode;
//	        size++;
		
	}
	public Object get(int index){
		 if (index < 0 || index >= size){
			 System.out.println("错误");
			 return null;
		 }
	        // 获取index处的节点。    
	        // 若index < 双向链表长度的1/2,则从前先后查找;    
	        // 否则，从后向前查找。    
	        if (index < (size/2 )) {    
	            for (int i = 0; i <= index; i++)    
	            	head = head.next;    
	        } else {    
	            for (int i = size; i > index; i--)    
	                head = head.pre;    
	        }    
	        return head; 
		
	}
	public Object remove(int index){
		Node x = (Node) get(index);
        Node next = x.next;
        Node prev = x.pre;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.pre = null;
        }

        if (next == null) {
            x.last = prev;
        } else {
            next.pre = prev;
            x.next = null;
        }

        size--;
        return get(index);
	}
	
	public int size(){
		return -1;
	}
	
	public void addFirst(Object o){
		
	}
	public void addLast(Object o){
		
	}
	public Object removeFirst(){
		return null;
	}
	public Object removeLast(){
		return null;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		Node pre;
		Node last;
	}
}
