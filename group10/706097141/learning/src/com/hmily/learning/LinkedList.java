package com.hmily.learning;
/**
 * ����д
 * @author zyzhmily
 *
 */
public class LinkedList implements List {
	
	//����ͷ
	private Node head;
	//����β
	private Node tail;
	//�����С
	private int size;
	
	public void add(Object o){
		Node t = tail;
        Node node = new Node(o, null);
        tail = node;
        if (t == null) {
            head = node;
        } else {
            t.next = node;
        }
        size++;
	}
	public void add(int index , Object o){
		if(index<0||index>size()){
			throw new IndexOutOfBoundsException();
		}
		if (index == size) {
            add(o);
        } else {
         Node node = head;
         for (int i = 0; i < index-1; i++) {
        	 node = node.next;
             }
         Node newNode = new Node(o, node);
         node.next = newNode;
         newNode.next = node;
         size++;
        }
	}
	public Object get(int index){
		if(index<0||index>size()){
			throw new IndexOutOfBoundsException();
		}
		Node node=head;
		for(int i=0;i<index;i++){
			node=node.next;
		}
		return node.data;
	}
	public Object remove(int index){
		if(index<0||index>size()){
			throw new IndexOutOfBoundsException();
		}
		return null;
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		Node newNode = new Node(o,null);
		newNode.next=head.next;
	}
	public void addLast(Object o){
		add(o);
	}
	public Object removeFirst(){
		return null;
	}
	public Object removeLast(){
		return null;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static class Node{
        Object data;
        Node next;
        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
