package com.maple.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	private int size = 0;//自己加的，觉得需要
	/**
	 * 与addList()是一样的
	 */
	public void add(Object o){
		addLast(o);
	}
	public void add(int index , Object o){
		if(index<0||index>size){
			throw new IndexOutOfBoundsException("Joy Index "+index+", Size: "+size);
		}
		Node prevNode=head;
		Node curNode=head.next;
		int count=0;
		while(count<=index){
			if(count==index){
				Node newNode=new Node();
				newNode.data=o;
				
				newNode.next=curNode;
				prevNode.next=newNode;
				size++;
				break;
			}
			curNode=curNode.next;
			prevNode=prevNode.next;
			count++;
		}
		
		
	}
	public Object get(int index){
		if(index<0||index>=size)
			throw new IndexOutOfBoundsException("Joy Index "+index+", Size: "+size);
		
		Node curNode=head.next;
		int count=0;
		while(count<=index){
			if(count==index){
				return curNode.data;
			}
			curNode=curNode.next;
			count++;
		}
		return null;
	}
	public Object remove(int index){
		if(index<0||index>=size)
			throw new IndexOutOfBoundsException("Joy Index "+index+", Size: "+size);
		Node prevNode=head;
		Node curNode=head.next;
		int count=0;
		while(count<=index){
			if(count==index){
				prevNode.next=curNode.next;
				size--;
				return curNode.data;
			}
			curNode=curNode.next;
			prevNode=prevNode.next;
			count++;
		}
		return null;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node objNode=new Node();
		objNode.data=o;
		if(head==null) head=new Node();
		objNode.next=head.next;
		size++;
		head.next=objNode;
	}
	public void addLast(Object o){
		Node objNode=new Node();
		objNode.data=o;
		if(head==null) head=new Node();
		
		//也可以用iterator迭代，先不用吧
		Node curNode=head;
		while(curNode.next!=null){
			curNode=curNode.next;
		}
		objNode.next=curNode.next;
		curNode.next=objNode;
		size++;
		
	}
	public Object removeFirst(){
		if(head==null||head.next==null)
			throw new NoSuchElementException();
		Node delNode=head.next;
		head.next=delNode.next;
		size--;
		return delNode.data;
	}
	public Object removeLast(){
		if(head==null||head.next==null)
			throw new NoSuchElementException();
		Node prevNode=head;
		Node curNode=head.next;
		while(curNode!=null){
			if(curNode.next==null){//说明是尾节点
				prevNode.next=curNode.next;
				size--;
				return curNode.data;
			}
			curNode=curNode.next;
			prevNode=prevNode.next;
		}
		return null;
	}
	public Iterator iterator(){
		return new Iterator() {
			private Node cur=head!=null?head.next:head;
			@Override
			public Object next() {
				if(cur==null){
					throw new NoSuchElementException();
				}
				Object object=cur.data;
				cur=cur.next;
				return object;
			}
			
			@Override
			public boolean hasNext() {
				if(cur==null){
					return false;
				}else{
					return true;
				}
				
			}
		};
	}
	
	
	private static  class Node{
		Object data;
		Node next;
	}
}
