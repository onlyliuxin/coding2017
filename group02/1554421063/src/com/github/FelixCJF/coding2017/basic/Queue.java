package com.github.FelixCJF.coding2017.basic;


public class Queue {
	
	private Node head;//头节点
	private Node last;//尾节点
	private int size;//记录节点
	
	public void enQueue(Object o){
		//设置一个节点变量存放原先的尾节点
		final Node oldLast = last;
		//创建一个新的节点
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = null;
		//添加到队列
		if (isEmpty()) {
			head = newNode;
		} else {
			oldLast.next = newNode;
		}
		//新节点变为尾节点
		last = newNode;
		size ++;
	}
	
	public Object deQueue(){

		Object object = head.data;
		
		head = head.next;
		
		if (isEmpty()) {
			last = null;
		}
		size --;
		return object;
	}
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public int size(){
		return size;
	}
	
	private static  class Node{
		Object data;
		Node next;
	}
}