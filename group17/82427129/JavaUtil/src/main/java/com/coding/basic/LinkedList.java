package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	private int size = 0;
	
	private Node first;
	
	private Node last;
	
	public void add(Object o){
		add(size,o);
	}
	public void add(int index , Object o){
		rangeCheck(index);
		
		if(index == size){
			linkLast(o);
		}else{
			linkBefore(o, indexOf(index));
		}
	}
	private void linkBefore(Object o ,Node succ){
		final Node prev = succ.prev;
		final Node newNode = new Node(prev, o, succ);
		succ.prev = newNode;
		if(prev == null){
			first = newNode;
		}else{
			prev.next = newNode;
		}
		size++;
	}
	private void linkLast(Object o){
		final Node succ = last;
		final Node newNode = new Node(succ, o, null);
		last = newNode;
		if(succ == null){
			first = newNode;
		}else{
			succ.next = newNode;
		}
		size++;
	}
	private void rangeCheck(int index) {
		if(index > size|| index < 0 )
			throw new IndexOutOfBoundsException("Size"+size+":index"+index);
	}
	private void elementIndexCheck(int index){
		if(index >=size||index < 0)
			throw new IndexOutOfBoundsException("Size"+size+":index"+index);
	}
	/**
	 * 获取“下标”为index的值,
	 * index为size时返回null
	 * @param index
	 * @return
	 */
	private Node indexOf(int index){
		if(index < (this.size>>1) ){
			Node x = first;
			for (int i = 0; i < index; i++) {
				x = x.next;
			}
			return x;
		}else{
			Node x = last;
			for (int i = this.size-1; i > index; i--) {
				x = x.prev;
			}
			return x;
		}
	}

	public Object get(int index){
		elementIndexCheck(index);
		
		return indexOf(index);
	}
	public Object remove(int index){
		elementIndexCheck(index);
		
		if(index == 0){
			return removeFirst();
		}else if(index == size) {
			return removeLast();
		}else{
			return unlinkNode(indexOf(index));
		}
	}
	
	private Object unlinkNode(Node node) {
		final Node next = node.next;
		final Node prev = node.prev;
		final Object element = node.data;
		if(next == null){
			last = node;
		}else{
			next.prev = node;
			node.next = next;
		}
		if(prev == null){
			first = node;
		}else{
			prev.next = node;
			node.prev = prev;
		}
		size--;
		node.data = null;
		
		return element;
	}
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		linkBefore(o, first);
	}
	
	public void addLast(Object o){
		linkLast(o);
	}
	
	public Object removeFirst(){
		if(first == null)
			throw new NoSuchElementException("first is null");
		
		Object oldData = first.data;
		final Node next = first.next;
		first.data = null;
		first.next = null;//GC
		first = next;
		
		if(next == null){
			last = null;
		}else{
			next.prev = null;
		}
		size--;
		
		return oldData;
	}
	
	public Object removeLast(){
		if(last == null)
			throw new NoSuchElementException("last is null");
		
		Object oldData = last.data;
		final Node prev = last.prev;
		last.prev = null;
		last.data = null;//GC
		last = prev;
		
		if(prev == null){
			first = null;
		}else{
			prev.next = null;
		}
		size--;
		
		return oldData;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	private static class Node{
		Object data;
		Node next;
		Node prev;
		Node(Node prev,Object data,Node next){
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
}