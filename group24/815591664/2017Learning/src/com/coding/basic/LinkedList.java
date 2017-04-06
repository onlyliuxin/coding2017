package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	
	public void add(Object o){
		this.addLast(o);
		
	}
	public void add(int index , Object o){
		if(index<0 || index>size){
			throw new IndexOutOfBoundsException();
		}
		if(index==0){
			this.addFirst(o);
			size++;
			return;
		}else if(index==size){
			this.addLast(o);
			size++;
			return;
		}
		Node preNode = this.getNode(index-1);
		Node curNode = this.getNode(index);
		Node newNode = new Node(o, curNode);
		preNode.next = newNode;

		
		size++;
		
		
	}
	
	private Node getNode(int index){
		if(index <0 || index>=size){
			throw new IndexOutOfBoundsException();
		}
		if(index ==0){
			return head;
		}
		Node curNode = head;
		for(int i=1;i<=index;i++){
			curNode = head.next; 	
		}
		return curNode;
	}
	
	public Object get(int index){
		if(index<0 || index>=size){
			throw new IndexOutOfBoundsException();
		}
		
		Node temp = head;
		for(int i =1;i<=index;i++){
			temp = temp.next;
		}
			return temp.data;
	}
	public Object remove(int index){
		if(index<0 || index>=size){
			throw new IndexOutOfBoundsException();
		}
		Object o = null;
		if(size == 1){
			o = head.data;
			size--;
			return o;
		}
		if(index==0){
			o = head.data;
			Node afterHead = head.next;
			head = afterHead;
	
		}else if(index==size-1){
			Node preTail = getNode(index-1);
			Node tail = preTail.next;
			o = tail.data;
			preTail.next = null;
		}else{
			Node preCur = getNode(index-1);
			Node cur = preCur.next;
			Node nextCur = cur.next;
			o = cur.data;
			preCur.next = nextCur;
		
		}
		size--;
		return o;
			
			
			
		
		
		
		
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		Node node = new Node(o,null);
		
		if(head == null){
			head = node;
			size++;
			return;
		}
		head = new Node(o, head);
		size++;
		
	}
	public void addLast(Object o){
		//新节点的next指针指向tail
		Node add = new Node(o, null);
		if(head==null){			
			head = add;
			size++;
			return;
		}
		Node curNode = head;
		while(curNode.next != null){
			curNode = curNode.next;
		}
		
		curNode.next = add;
		size++;
	}
	
	
	public Object removeFirst() throws Exception{
		return this.remove(0);
	}
	public Object removeLast() throws Exception{
		return this.remove(size-1);
	}
	
	private class Itr implements Iterator{
		int cursor = 0;
		public boolean hasNext() {
			return cursor<size();
		}
		

		public Object next() throws Exception {
			cursor++;
			return get(cursor-1);
			
			
		}
		
	}
	public Iterator iterator(){
		return new Itr();
	}
	
	
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		
		Iterator itr = this.iterator();
		while(itr.hasNext()){
			try {
				sb.append(itr.next()+",");
			} catch (Exception e) {
				return "[]";
			}
			
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("]");
		
		return sb.toString();
//		
	}





	private static  class Node{
		private Object data;
		private Node next;
		public Node(Object data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}
		
		
		
		
	}
	
	public static void main(String[] args) throws Exception {
		LinkedList ll  = new LinkedList();
		ll.add(1);
		ll.add(2);
		System.out.println(ll);
		
		ll.add(1, 3);
		System.out.println(ll);
		
		ll.remove(1);
		
		System.out.println(ll);
		ll.add(4);
		System.out.println(ll);
		
		Iterator itr = ll.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
	}
}
