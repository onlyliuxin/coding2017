package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private Node tail;
	private int size;
	
	
	public void add(Object o){
		this.addLast(o);
		
	}
	public void add(int index , Object o) throws Exception{
		if(index==0){
			this.addFirst(o);
			return;
		}else if(index==size-1){
			this.addLast(o);
			return;
		}else{
			
			
			Node curNode = this.getNode(index);
			Node pre = curNode.previous;
//			Node next = curNode.next;
			//加入链表
			Node newNode = new Node(o, pre, curNode);
			curNode.previous = newNode;
			pre.next = newNode;
		}
		size++;
		
		
	}
	private Node getNode(int index) throws Exception{
		if(index>=size){
			throw new Exception("下标超限");
		}
		if(index ==0){
			return head;
		}else if(index==size-1){
			return tail;
			
		}else{
			Node temp = head;
			for(int i =1;i<=index;i++){
				temp = temp.next;
			}
			return temp;
		}
	}
	public Object get(int index) throws Exception{
		if(index>=size){
			throw new Exception("下标超限");
		}
		if(index ==0){
			return head.data;
		}else if(index==size-1){
			return tail.data;
			
		}else{
			Node temp = head;
			for(int i =1;i<=index;i++){
				temp = temp.next;
			}
			return temp.data;
		}
	}
	public Object remove(int index) throws Exception{
		if(index>=size){
			throw new Exception("下标超限");
		}
		Object o = null;
		if(size == 1){
			o = head.data;
		}else{
			if(index==0){
				
				Node afterHead = head.next;
				afterHead.previous = null;
				head = afterHead;
				o = head.data;
		
			}else if(index == size-1){
				Node beforeTail = tail.previous;
				beforeTail.next = null;
				tail = beforeTail;
				o = tail.data;
			}else{
				Node curNode = this.getNode(index);
				Node pre = curNode.previous;
				Node next = curNode.next;
				//中间变量用于断开指针
				Node temp = new Node(next.data, pre, next.next);
				pre.next = temp;
				next = temp;
				o = curNode.data;
				
			}
			
			
			
		}
		
		size--;
		return o;
		
		
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		Node node = new Node(o, null, head);
		
		if(head == null){
			head = node;
			tail = node;
		}else{
			head.previous = node;
			head = node;
		}
		size++;
		
	}
	public void addLast(Object o){
		//新节点的previous指针指向tail
		Node curNode = new Node(o, tail, null);
		if(tail==null){			
			//当前链表为空时，将该节点加入链表，头尾均为该节点
			head = curNode;
			tail = curNode;
		}else{
			//如果该链表不为空时，将最后一个节点的next指针指向新加入的节点即可
			tail.next = curNode;
			//将新加入的这个节点置为tail
			tail = curNode;
			
		}
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
		private Node previous;
		private Node next;
		public Node(Object data, Node previous, Node next) {
			super();
			this.data = data;
			this.previous = previous;
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
