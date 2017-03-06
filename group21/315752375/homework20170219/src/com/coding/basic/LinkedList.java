package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;//ͷ���
	private int size;//��������Ԫ�ظ���
	public LinkedList() {
		head = new Node();
		head.next=null;
		size=0;
	}
	public void add(Object o){
		addLast(o);
		
	}
	public void add(int index , Object o){
		rangeCheckAdd(index);
		Node preNode=getNode(index-1);
		Node node=new Node();
		node.data=o;
		node.next=preNode.next;
		preNode.next=node;
		size++;
	}
	private Node getNode(int index) {
		rangeCheck(index);
		int count=-1;
		Node node=head;
		while(count!=index){
			node=node.next;
			count++;
		}
		return node;
	}
	public Object get(int index){
		return getNode(index).data;
	}
	public Object remove(int index){
		rangeCheck(index);
		Node preNode=getNode(index-1);
		Node node=preNode.next;
		preNode.next=preNode.next.next;
		size--;
		return node.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node=new Node();
		node.data=o;
		node.next=head.next;
		head.next=node;
		size++;
	}
	public void addLast(Object o){
		add(size, o);
	}
	public Object removeFirst(){
		return remove(0);
	}
	public Object removeLast(){
		return remove(size-1);
	}
	public Iterator iterator(){
		return new LinkedIterator();
	}
	private void rangeCheck(int index){//ͷ���Ϊ-1������get
		if(index<-1||index>size-1)throw new IndexOutOfBoundsException();
	}
	private void rangeCheckAdd(int index){//����add
		if(index<0||index>size)throw new IndexOutOfBoundsException();
	}
	
	private static class Node{
		Object data;
		Node next;
	}
	private class LinkedIterator implements Iterator{
		Node nextNode=head.next;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return nextNode!=null;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			Object object=nextNode.data;
			nextNode=nextNode.next;
			return object;
		}
		
	}
}
