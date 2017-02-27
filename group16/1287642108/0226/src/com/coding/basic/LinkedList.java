package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private Node tail;
	private int size = 0 ;
	
	public void add(Object o){
		addLast(o);
	}

	public void add(int index, Object o) {
		if (index == 0) {
			addFirst(o);
		} else if (index >= size) {
			addLast(o);
		} else {
			Node node = new Node();
			node.data = o;
			node.next = getNode(index);
			getNode(index - 1).next = node;
			size++;
		}
	}
	
	public Object get(int index) {
		Node node = getNode(index);
		return node.data;
	}
	
	public Object remove(int index){
		Node currentNode = getNode(index);
		Node prevNode = getNode(index - 1);
		Node lastNode = getNode(index + 1);
		prevNode.next = lastNode;
		size--;
		return currentNode.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		  Node node=new Node();  
		  node.data = o;
		  node.next = head;  
          head = node;  
          size++;  
	}
	public void addLast(Object o){
		 Node node=new Node();  
		 node.data = o;
		 node.next = null; 
         Node lastNode = getNode(size-1);
         lastNode.next = node;
         size++;  
	}
	public Object removeFirst(){
		Object obj = getNode(0).data;
		Node node = getNode(1);
		node.next = head;
		size--;
		return obj;
	}
	public Object removeLast(){
		Object obj = getNode(size - 1).data;
		Node node = getNode(size - 2);
		node.next = null;
		size--;
		return obj;
	}
	
	//获取节点
    public Node getNode(int index){
    	checkIndex(index);
    	if(index == 0 ){
    		return head;
    	}else if(index == size -1 ){
    		return tail;
    	}else{
    		 Node node = head;
    	        for(int i=0;i<index;i++){
    	        	node = node.next;
    	        }
    	        return node;
    	}
    }
    
 // 下标越界判断
 	private void checkIndex(int index) {
 		if (index >= size || index < 0)
 			throw new IndexOutOfBoundsException("数组下标越界");
 	}
	
	private static class Node {
		Object data;
		Node next;
	}
}
