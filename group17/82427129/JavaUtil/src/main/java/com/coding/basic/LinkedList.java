package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {
	private int size = 0;
	
	private Node<E> first;
	
	private Node<E> last;
	
	public void add(E o){
		add(size,o);
	}
	public void add(int index , E o){
		rangeCheck(index);
		
		if(index == size){
			linkLast(o);
		}else{
			linkBefore(o, indexOf(index));
		}
	}
	private void linkBefore(E o ,Node<E> succ){
		final Node<E> prev = succ.prev;
		final Node<E> newNode = new Node<E>(prev, o, succ);
		succ.prev = newNode;
		if(prev == null){
			first = newNode;
		}else{
			prev.next = newNode;
		}
		size++;
	}
	private void linkLast(E o){
		final Node<E> succ = last;
		final Node<E> newNode = new Node<E>(succ, o, null);
		last = newNode;
		if(succ == null){
			first = newNode;
		}else{
			succ.next = newNode;
		}
		size++;
	}
	/**
	 * range check,
	 * permit the range [0,size]
	 * @param index
	 */
	private void rangeCheck(int index) {
		if(index > size|| index < 0 )
			throw new IndexOutOfBoundsException("Size"+size+":index"+index);
	}
	/**
	 * element's index check,
	 * permit the index [0,size)
	 * @param index
	 */
	private void elementIndexCheck(int index){
		if(index >=size||index < 0)
			throw new IndexOutOfBoundsException("Size"+size+":index"+index);
	}
	/**
	 * 
	 * @param index
	 * @return
	 */
	private Node<E> indexOf(int index){
		if(index < (this.size>>1) ){
			Node<E> x = first;
			for (int i = 0; i < index; i++) {
				x = x.next;
			}
			return x;
		}else{
			Node<E> x = last;
			for (int i = this.size-1; i > index; i--) {
				x = x.prev;
			}
			return x;
		}
	}

	public E get(int index){
		elementIndexCheck(index);
		
		return indexOf(index).data;
	}
	public E remove(int index){
		elementIndexCheck(index);
		
		if(index == 0){
			return removeFirst();
		}else if(index == size) {
			return removeLast();
		}else{
			return unlinkNode(indexOf(index));
		}
	}
	
	private E unlinkNode(Node<E> node) {
		final Node<E> next = node.next;
		final Node<E> prev = node.prev;
		final E element = node.data;
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
	
	public void addFirst(E o){
		linkBefore(o, first);
	}
	
	public void addLast(E o){
		linkLast(o);
	}
	
	public E removeFirst(){
		if(first == null)
			throw new NoSuchElementException("first is null");
		
		E oldData = first.data;
		final Node<E> next = first.next;
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
	
	public E removeLast(){
		if(last == null)
			throw new NoSuchElementException("last is null");
		
		E oldData = last.data;
		final Node<E> prev = last.prev;
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
	
	private static class Node<E>{
		E data;
		Node<E> next;
		Node<E> prev;
		Node(Node<E> prev,E data,Node<E> next){
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
}