package com.coderising.linkedlist;


/**
 * 单向链表简单实现
 * @author 240094626
 *
 */
public class SLinkedList<E> implements List<E>{
	transient Node<E> head;
	transient Node<E> last;
	transient int size;
	
	public SLinkedList(){
		head  = new Node<E>(null,last);
		last = null;
		size = 0;
	}
	
	
	private void addAfter(Node<E> n , E e){
		if(n == null){
			last = new Node<E>(e,head);
			head.next = last;
		}else{
			n.next = new Node<E>(e,n.next);
		}
		size++;
	}
	private void checkRange(int index){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Index:"+index+",Size:"+size);
		}
	}
	
	private Node<E> getNode(int index){
		checkRange(index);
		Node<E> curr = head;
		for(; index >= 0 ; index--){
			curr = curr.next;
		}
		return curr;
	}

	public void add(E e) {
		addAfter(last,e);
	}

	public void add(int index, E e) {
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("Index:"+index+",Size:"+size);
		}
		addAfter(getNode(index-1), e);
	}

	public E get(int index) {
		Node<E> n = getNode(index);
		return n.e;
	}

	
	public E remove(int index) {
		checkRange(index);
		Node<E> preN = null,
				currN = null;
		if(index == 0){
			preN = head;
		}else{
			preN = getNode(index-1);
		}
		currN = preN.next;
		preN.next = currN.next;
		size--;
		return currN.e=null;
		
	}

	public int size() {
		return size;
	}
	
	
	private static class Node<E>{
		E e ;
		Node<E> next;
		
		public Node(E e,Node<E> next){
			this.e = e;
			this.next = next;
		}
	}
	
	private Iterator<E> iterator(){
		return new SLinkedListIterator<E>();
	}

	private class SLinkedListIterator<E> implements Iterator<E>{

		int index;
		public SLinkedListIterator(){
			index = 0;
		}
		
		@Override
		public boolean hasNext() {
			if(index < size){
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			Node<E> curr = (Node<E>) getNode(index++);
			return curr.e;
		}
		
	}
}
