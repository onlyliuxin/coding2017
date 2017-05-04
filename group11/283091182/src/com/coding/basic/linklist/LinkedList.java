package com.coding.basic.linklist;

import com.coding.basic.Iterator;
import com.coding.basic.List;

public class LinkedList implements List {
	
	private Node head = null;
	private int size = 0;
	
	public void add(Object o){
		if(head==null){
			head = new Node(o);
		}else{
			Node temp = head;
			while(temp.hasNext()){
				temp = temp.next;
			}
			temp.next = new Node(o);
		}
		size++;
		
	}
	public void add(int index , Object o){
		validate(index);
		if(index==0){
			Node newNode = new Node(o,head);
			head = newNode;
		}else{
			Node temp = head;
			for(int i=0;i<index;i++){
				temp = temp.next;
			}
			Node newNode = new Node(o,temp.next);
			temp.next = newNode;
		}
		size++;
	}
	public Object get(int index){
		validate(index);
		Node temp = head;
		for(int i=0;i<=index;i++){
			temp = temp.next;
		}
		return temp.data;
	}
	public Object remove(int index){
		validate(index);
		Node temp = head;
		for(int i=0;i<index;i++){
			temp = temp.next;
		}
		Object result = temp.next.data;
		temp.next = temp.next.next;
		size --;
		return result;
		
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node newNode = new Node(o,head);
		head = newNode;
		size++;
	}
	public void addLast(Object o){
		Node temp = head;
		while(temp.hasNext()){
			temp = temp.next;
		}
		temp.next = new Node(o);
		size++;
	}
	public Object removeFirst(){
		Object result = head.data;
		head = head.next;
		size--;
		return result;
	}
	public Object removeLast(){
		Object result = null;
		if(size==1){
			result = head.data;
		}else{
			Node temp = head;
			while(temp.next.hasNext()){
				temp=temp.next;
			}
			result = temp.next.data;
			temp.next = null;
		}
		size--;
		return result;
	}
	public Iterator iterator(){
		return new LinkedListIterator();
	}
	private class LinkedListIterator implements Iterator{
		private Node current = head;
		private int pos = 0;
		@Override
		public boolean hasNext() {
			return current!=null;
		}

		@Override
		public Object next() {
			if(current == null){
				throw new IndexOutOfBoundsException("Invalid Index:"+pos);
			}
			Object result = current.data;
			current = current.next;
			pos++;
			return result;
		}
		
	}
	
	private void validate(int index){
		if(index<0||index>=size)throw new IndexOutOfBoundsException("Invalid Index:"+index);
	}
	
	private static  class Node{
		Object data;
		Node next;
		public boolean hasNext(){
			return (this.next!=null);
		}
		public Node(Object data,Node next){
			this.data = data;
			this.next = next;
		}
		public Node(Object data){
			this.data = data;
			this.next = null;
		}
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("[");
		Node temp = head;
		while(temp!=null){
			if(sb.length()>1)sb.append(",");
			sb.append(temp.data);
			temp = temp.next;
		}
		sb.append("]size=").append(this.size());
		return sb.toString();
	}
	public static void main(String[] args){
		LinkedList l = new LinkedList();
		for(int i=0;i<12;i++){
			l.add(i+"");
		}
		System.out.println(l);
		l.add("aaa");
		System.out.println("After adding aaa:"+l);
		l.add(2,"bbb");
		System.out.println("After adding bbb:"+l);
		System.out.println(l.get(2));
		System.out.println("After getting:"+l);
		System.out.println(l.remove(2));
		System.out.println("After removing:"+l);
		l.addFirst("first");
		System.out.println("After add First:"+l);
		l.addLast("last");
		System.out.println("After add Last:"+l);
		System.out.println(l.removeFirst());
		System.out.println("After remove First:"+l);
		System.out.println(l.removeLast());
		System.out.println("After remove Last:"+l);
		Iterator it = l.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		//it.next();
	}
}
