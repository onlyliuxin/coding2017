package com.coding.basic;

import java.util.Iterator;

public class LinkedList<E> implements List<E> {
	
	private Node<E> last;
	private Node<E> head;	
	private int xsize = 0;
	
	public LinkedList() {
		init();
	}
	
	private void init() {
		head = new Node<E>(null,null,null);
		last = new Node(null,head,null);		
		head.next = last;		
		xsize = 0;
	}
	
	//抽象出来的一个方法，供内部使用;在index之前插入一个节点
	private void add(Node<E> node,int index) {
		if(index < 0 || index > xsize) {
			throw new IndexOutOfBoundsException("下标越界");
		}
		node.pre = getNode(index-1);
		getNode(index-1).next = node;
		node.next = getNode(index);
		getNode(index).pre = node;
		xsize++;
	}
	public void add(E e){
		add(new Node<E>(e,null,null),xsize);
	}
	
	public void add(int index,E e){
		add(new Node<E>(e,null,null),index);
	}
	private Node<E> getNode(int index){
		Node<E> newNode;
		int current;
		if(index == -1) {
			return head;
		} else if (index == xsize ){
			return last;
		} else {
			current = 0;
			newNode = head.next;
			while (current < index) {
				newNode = newNode.next;
				current++;
			}
		}
		return newNode;
	}
	
	public E get(int index) {  
        return getNode(index).e;  
    }  
	
	public void remove(int index){
		Node node = getNode(index);
		node.pre.next = node.next;
		node.next.pre=node.pre;
		xsize--;		
	}
	
	public int size(){
		return xsize;
	}
	
	public void addFirst(E e){
		add(new Node<E>(e,null,null),0);
	}
	public void addLast(E e){
		add(new Node<E>(e,null,null),xsize);
	}
	public void removeFirst(){
		remove(0);		
	}
	public void removeLast(){
		remove(xsize);
	}
	public Iterator iterator(){
		return null;
	}
	
	private static  class Node<E>{
		public E e;		
		Node<E> next;
		Node<E> pre;
		public Node (E e,Node<E> pre,Node<E> next) {
			this.pre = pre;
			this.next = next;
			this.e = e;
		}
		
	}


	public boolean contains(Node node) {
		Node mnode = head.next;
		while(mnode !=null) {
			if(mnode.equals(node)) {
				return true;
			}
			mnode = mnode.next;
		}
		return false;
	}
	public boolean isEmpty() {		
		return xsize == 0 ? true:false;
	}
	public void clear() {
		init();
	}
	
	public boolean contains(E e) {
		// TODO Auto-generated method stub
		Node<E> node = null;
		while((node=(head.next)) != null) {
			if(e.equals(node)) {
				return true;
			} else {
				node = node.next;
			}
		}
		return false;
	}

	
}
