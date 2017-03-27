package com.github.xiaozi123.coding2017.basic;

import java.util.NoSuchElementException;

import jdk.nashorn.internal.ir.IndexNode;

public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	private Node node(Object o) {
		Node now=new Node();
		now.data=o;
		now.next=null;
		size++;
		return now;
	}
	
	public void add(Object o){
		if (head==null) {
			head=node(o);
		}
		else {
			addLast(o);
		}
		
	}
	public void add(int index , Object o){
		if (index<0||index>size) {
			throw new IndexOutOfBoundsException("OutOfBound");
		}else if (index==0) {
			addFirst(o);
		}else if (index==size+1) {
			addLast(o);
		}else{
			Node beforeNode=head;
			for (int i = 0; i < index-1; i++) {
				beforeNode=beforeNode.next;
			}
			Node addNode=node(o);
			addNode.next=beforeNode.next;
			beforeNode.next=addNode;
		} 
	}
	
	
	public Object get(int index){
		if (index<0||index>size) {
			throw new IndexOutOfBoundsException("OutOfBound");
		} else {
			Node indexNode=head;
			for (int i = 0; i < index; i++) {
				indexNode=indexNode.next;
			}
			return indexNode.data;
		}
		
	}
	public Object remove(int index){
		if (index<0||index>size) {
			throw new IndexOutOfBoundsException("OutOfBound");
		}else if(index==0){
			return removeFirst();
		}else if(index==size){
			return removeLast();
			
		}else{
			Node beforeNode=head;
			for (int i = 0; i < index-1; i++) {
				beforeNode=beforeNode.next;
			}
			
			Node indexNode=head;
			for (int i = 0; i < index; i++) {
				indexNode=indexNode.next;
			}
			beforeNode.next=indexNode.next;
			indexNode.next=null;
			size--;
			return indexNode.data;
		} 
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node headNode=node(o);
		headNode.data=o;
		headNode.next=head.next;
		head=headNode;
		
	}
	
	public void addLast(Object o){
		Node tailNode=head;
		while(tailNode.next!=null){
			tailNode=tailNode.next;
		}
		Node lastNode=node(o);
		tailNode.next=lastNode;
	}
	public Object removeFirst(){
		if (head==null) {
			throw new NoSuchElementException();
		}
		Object temp= head.data;
		head=head.next;
		size--;
		return temp;
	}
	public Object removeLast(){
		if (head==null) {
			throw new NoSuchElementException();
		}
		Node newNode=head;
		while(newNode.next.next!=null){
			newNode=newNode.next;
		}
		Node lastNode=newNode.next;
		newNode.next=null;
		size--;
		return lastNode.data;
		
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
	}
	
	public static void main(String[] args) {
		LinkedList linkedList=new LinkedList();
		
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		
		System.out.println("数字个数为3："+linkedList.size());
		
		System.out.println("获取的数字为1："+linkedList.get(0)); 
		System.out.println("获取的数字为2："+linkedList.get(1)); 
		System.out.println("获取的数字为3："+linkedList.get(2)); 
		// add get remove size
		
		System.out.println("*************");
		
		System.out.println(linkedList.remove(0));//1
		System.out.println("获取的数字为2："+linkedList.get(0)); 
		System.out.println("获取的数字为3："+linkedList.get(1)); 
		System.out.println("数字个数为2："+linkedList.size());
		
	}
	
}

