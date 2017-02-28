package com.coding.basic;

public class LinkedList implements List {

    private Node head;
    private int size;
    
    public LinkedList(){
    	this.head = null;
    	this.size = 0;
    }
    
    public void add(Object o) {
        Node newNode = new Node(o);
        if(isEmpty()){
        	head = newNode;
        }
        else{
	        newNode.next = head;
	        head = newNode;
        }
        size++;
    }

    public void add(int index, Object o) {
    	if (index < 0 || index > size) 
			throw new IndexOutOfBoundsException("下标越界");
    	
        Node indexNode = node(index);
        
        Node newNode = new Node(o);
        if(isEmpty()){
        	head = newNode;
        }else {
            newNode.next = indexNode;
            indexNode = newNode;
        }
        size++;
    }

    public Object get(int index) {
		if (index < 0 || index > size) 
			throw new IndexOutOfBoundsException("下标越界");
        return node(index).data;
    }

    public Object remove(int index) {
        Node indexNode = node(index);
        Node preNode = node(index);
        preNode.next = indexNode.next;
        indexNode.next = null;
        size--;
        return indexNode.data;
    }

    public int size() {
        return size;
    }

    public void addFirst(Object o) {
    	if(o == null){
    		throw new RuntimeException("不能加入null元素");  
    	}
        Node newNode = new Node(o);
        newNode.next = head;
        size++;
    }

    public void addLast(Object o) {
        Node newNode = new Node(o);
        Node last = node(size);

        newNode.next = last.next;
        last.next = newNode;
        size++;
    }

    public Object removeFirst() {
        Node oldNode = head;
        head = head.next;
        head.next = null;
        size--;
        return oldNode.data;
    }

    public Object removeLast() {
        Node oldNode = node(size);
        Node preNode = node(size - 1);
        preNode.next = null;
        size--;
        return oldNode.data;
    }

    public Iterator iterator(int index) {
        return new Itr(index);
    }
    
    // TODO: 2017/2/24  
    private class Itr implements com.coding.basic.Iterator {
        private int nextIndex;

        public Itr(int index) {
            this.nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public Object next() {
            return null;
        }
    }


    private static class Node {
        public Object data;
        public Node next;

        public Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    Node node(int index) {
        Node x = head;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    }
    
    //
	public boolean isEmpty() {
		return head ==null;
	}
}
