package com.coding.basic;

/**
 * Created by songbao.yang on 2017/2/21.
 *
 */
public class LinkedList implements List {
	
	private Node head;
	private int elementCount;

    //head作为一个节点，其next的值指向List中的真正的第一个节点
	public LinkedList() {
        Node head = new Node();
        head.next = null;
        head.data = null;
        elementCount = 0;
	}

	public void add(Object o){
		Node newNode = new Node();
		newNode.data = o;
        newNode.next = null;

		Node cursor = head;
        while (cursor.next != null){
            cursor = cursor.next;
        }
        cursor.next = newNode;
		elementCount++;
	}


	public void add(int index , Object o){
        indexRangeCheck(index);
        Node newNode = new Node();
        newNode.data = o;

        Node cursor = head;
        for (int i = 0; i < index; i++) {
            cursor = cursor.next;  //将cursor移动到index-1节点处；
        }

        newNode.next = cursor.next; //将新节点指向原index处的节点
        cursor.next = newNode;//将原index-1处的节点指向新节点
        elementCount++;
    }

	private void indexRangeCheck(int index){
	    if (index < 0 || index >= size()){
	        throw new IndexOutOfBoundsException();
        }
    }

	public Object get(int index){
		indexRangeCheck(index);
        Node cursor = head;
        for (int i = 0; i < index; i++) {
            cursor = cursor.next;
        }
        return cursor.next;
    }

	public Object remove(int index){
	    indexRangeCheck(index);
        Node cursor = head;
        for (int i = 0; i < index; i++) {
            cursor = cursor.next;
        }
        Node indexNode = cursor.next;
        cursor.next = indexNode.next;
		return indexNode;
	}
	
	public int size(){
		return elementCount;
	}
	
	public void addFirst(Object o){
		
	}
	public void addLast(Object o){
		
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
	
	
	private static  class Node{
		Object data;
		Node next;
	}
}
