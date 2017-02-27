package com.sx.structures;

public class MyLinkedList implements MyList{
	private Node head;
	private int size = 0;

	public MyLinkedList() {
		head = new Node();
	}
	@Override
	public void add(Object o) {
		Node node = createNode(o);
		Node pre = head;
		while(pre.next!=null){
			pre = pre.next;
		}
		pre.next = node;
		size++;
	}

	@Override
	public void add(int index, Object o) {
		if(index < 0){
			System.out.println("����Խ��");return;
		}
		Node node = createNode(o);
		Node pointer = head;
		while(index>0){
			pointer = pointer.next;
			index--;
		}
		node.next = pointer.next;
		pointer.next = node;
		size++;
	}

	@Override
	public Object get(int index) {
		Node pointer = head;
		while(index>=0){
			pointer = pointer.next;
			index--;
		}
		return pointer.data;
	}

	@Override
	public Object remove(int index) {
		Object data = null;
		Node pre = head;
		while(index>0){
			pre = pre.next;
			index--;
		}
		data = pre.next.data;
		pre.next = pre.next.next;
		size--;
		return data;
	}

	@Override
	public int size() {
		return size;
	}
	
	public void addFirst(Object o){
		add(0, o);
	}
	public void addLast(Object o){
//		Node node = createNode(o);
//		Node p = head;
//		while(p.next!=null)
//			p = p.next;
//		p.next = node;
//		size++;
		add(o);
	}
	public Object removeFirst(){
		return remove(0);
	}
	public Object removeLast(){
		Object data = null;
		Node re = head;
		Node pre = head;
		while(re.next!=null){
			re = re.next;
			pre = re;
		}
		data = re.data;
		re=null;
		pre.next = null;
		size--;
		return data;
	}
	private Node createNode(Object o){
		Node node = new Node();
		node.data=o;
		return node;
	}
	private static class Node{
		Object data = null;
		Node next = null;
	}

}
