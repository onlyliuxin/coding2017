package com.coding.basic.queue;

import java.util.NoSuchElementException;

/*
 * 链队列
 * 队头和队尾2个元素唯一确定一个链队列
 */
public class Queue<E> {
	private Node<E> first;
	private Node<E> last;
	private int size;
	
	private static class Node<E> {
		private E item;
		private Node<E> next;
	}
	
	public Queue(){
		first = null;
		last = null;
		size = 0;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public int size() {
		return size;
	}
	
	public void enQueue(E data){
		Node<E> oldlast = last;
		last = new Node<>();
		last.item = data;
		last.next = null;
		if(isEmpty()){
			first = last;
		} else {
			oldlast.next = last;
		}
		size++;
	}
	
	public E deQueue(){
		if(isEmpty()){
			throw new NoSuchElementException("Queue underflow");
		}
		
		E item = first.item;
		first = first.next;
		size--;
		if(isEmpty()){
			last = null;
		} 
		// 注意：删除队列头元素时，仅需修改头结点指针，但是当队列中最后一个元素被删除后，队列尾指针丢失，需要对尾指针重新赋值
		return item;
	}
	
	public static void main(String[] args){
		Queue<Integer> queue = new Queue<>();
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		while(!queue.isEmpty()){
			System.out.println(queue.deQueue());
		}
	}
}
