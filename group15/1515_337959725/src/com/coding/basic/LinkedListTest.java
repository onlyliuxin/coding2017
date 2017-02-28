package com.coding.basic;

public class LinkedListTest implements ListTest {
	private Node headNode;
	private Node tailNode;
	
	class Node{
		private Object obj;
//		private Node proNode;
		private Node nextNode;
		
		public Node(Object obj) {
			this.obj = obj;
		}
		
		
	}

	public void add(Object o) {
		if(headNode==null){
			headNode=new Node(o);
			tailNode=headNode;
		}else{
			tailNode.nextNode=new Node(o);
			tailNode=tailNode.nextNode;
		}
	}

	public void add(int index, Object o) {
		Node newNode=new Node(o);
		Node node=headNode;
		for(int i=0;i<index-1;i++){
			node=node.nextNode;
		}
		newNode.nextNode=node.nextNode;	
		node.nextNode=newNode;
		
	}

	public Object get(int index) {
		Node node=headNode;
		for(int i=0;i<index;i++){
			node=node.nextNode;
		}
		return node.obj;
	}

	public Object remove(int index) {
		Node node=headNode;
		if(index==0){
			return node.obj;
		}else{
			for(int i=0;i<index-1;i++){
				node=node.nextNode;
			}
			Object obj=node.nextNode.obj;
			node.nextNode=node.nextNode.nextNode;
			return obj;
		}
		
	}

	public int size() {
		int length=1;
		Node node=headNode;
		if(headNode==null){
			return 0;
		}else{
			while(node!=tailNode){
				node=node.nextNode;
				length++;
			}
			return length;
		}
	}
	
	public Object removeFirst(){
		Object obj = headNode.obj;
		headNode=headNode.nextNode;
		return obj;
	}
	public Object removeLast(){
		int length=size();
		Object obj = tailNode.obj;
		Node node=headNode;
		if(length==0){
			return null;
		}else if(length==1){
			return headNode;
		}else{
			for(int i=0;i<length-2;i++){
				node=node.nextNode;
			}
			tailNode=node;
			return obj;
		}
		
	}
}

