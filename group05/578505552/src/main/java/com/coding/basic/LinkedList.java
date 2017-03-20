package com.coding.basic;

import java.util.NoSuchElementException;

/**
 * Created by songbao.yang on 2017/2/21.
 *
 */
public class LinkedList implements List {
	
	private Node head;
	private int elementCount;

    //head作为一个节点，其next的值指向List中真正的第一个节点
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
        elementCount--;
		return indexNode;
	}
	
	public int size(){
		return elementCount;
	}
	
	public void addFirst(Object o){
        Node node = new Node();
        node.data = o;
        node.next = head.next;
        head.next = node;
        elementCount++;
    }

	public void addLast(Object o){

	    Node cursor = head;
        while (cursor.next != null){
            cursor = cursor.next;
        }
        Node newNode = new Node();
        newNode.data = o;
        newNode.next = null;
        cursor.next = newNode;
        elementCount++;
    }

	public Object removeFirst(){

	    if (size() == 0){
	        throw new RuntimeException("no element in list");
        }
        Node firstNode = head.next;
        head.next = head.next.next;
        elementCount--;
        return firstNode;
	}

	public Object removeLast(){
	    if (size() == 0){
            throw new RuntimeException("no element in list");
        }

	    Node cursor = head;
        for (int i = 0; i < size() - 1; i++) {
            cursor = cursor.next;
        }

        Node lastNode = cursor.next;
        cursor.next = null;
        elementCount--;

        return lastNode;
    }

	public Iterator iterator(){
		return new Itr();
	}

	private class Itr implements Iterator {

	    private Node itrCursor = head;

        public boolean hasNext() {

            return itrCursor.next != null;
        }

        public Object next() {
            if (hasNext()){
                return itrCursor.next;
            }
            throw new NoSuchElementException();
        }
    }

	private static  class Node{
		Object data;
		Node next;
	}
}
