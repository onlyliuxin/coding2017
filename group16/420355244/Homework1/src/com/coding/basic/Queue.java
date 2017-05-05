package com.coding.basic;

public class Queue {
	private Node first;
	private Node last;
	private int size = 0;
	public void enQueue(Object o){	
		if(null == first){
			Node node = new Node();
			node.data = o;
			node.next = null;
			first = node;
			last = node;
		}else{
			Node node = new Node();
			node.data = o;
			node.next = null;
			last.next = node;
			last = node;
		}
		size++;
	}
	
	public Object deQueue(){
		Node second = first.next;
		Object o = first.data;
		if(null != second){
			first = second;
			return o;
		}else{
			first = null;
			size = 0;
			return o;
		}
	}
	
	public boolean isEmpty(){
		if(size > 0){
			return false;
		}else{
			return true;
		}
	}
	
	public int size(){
		return size;
	}
	static class Node{
		Node next;
		Object data;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(null != first){
			sb.append(first.data.toString() + ",");
			Node node = first.next;
			sb.append(node.data.toString() + ",");
			while(null != node.next){
				node = node.next;
				sb.append(node.data.toString() + ",");
			}
		}
		return sb.toString();
	}
}
