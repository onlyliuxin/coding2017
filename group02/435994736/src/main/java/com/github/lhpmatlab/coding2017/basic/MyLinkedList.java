package com.github.lhpmatlab.coding2017.basic;

/**
 * Created by andy on 2017/2/18.
 */
public class MyLinkedList<T> {
	 private class Node<T> {
	        public Node<T> pre;
	        public Node<T> next;
	        public T data;

	        public Node(Node<T> pre,Node<T> next,T data) {
	            this.pre = pre;
	            this.next = next;
	            this.data = data;
	        }
	    }

	    private int dataSize;

	    private Node head;
	    private Node tail;

	    public MyLinkedList() {
	        head = new Node<T>(null,null,null);
	        tail = new Node(head, null, null);
	        head.next = tail;
	        dataSize = 0;
	    }

	    public void add(T t) {
//	        add(size(), t);
	        Node<T> newNode = new Node<>(null, tail, t);
	        newNode.pre = tail.pre;
	        tail.pre.next = newNode;
	        tail.pre = newNode;
	        dataSize++;

	    }

	    /**
	     * 根据索引添加没有实现
	     * @param index
	     * @param element
	     */
	    public void add(int index,T element) {
	        //TODO 根据索引添加元素
//	        addBefore(getNode(index,0,size()-1),element);
//	        if (index == dataSize) {
//	            add(element);
//	        } else {
	//
//	        }
	    }

	    public T get(int index) {
	        return getNode(index).data;
	    }

	    public T set(int index, T newValue) {
	        Node<T> node = getNode(index);
	        T oldData = node.data;
	        node.data = newValue;
	        return oldData;
	    }

	    public T remove(int index) {
	        Node<T> node = getNode(index);
	        node.next.pre = node.pre;
	        node.pre.next = node.next;
	        dataSize--;

	        return node.data;

	    }

	    private void addBefore(Node<T> node, T element) {
//	        newNode.pre.next = newNode;
//	        node.pre = newNode;
	        Node<T> pre = node.pre;
	        Node<T> newNode = new Node<>(node.pre, node, element);
	        node.pre = newNode;
	        pre.next = newNode;

	        dataSize++;
	    }

	    private Node<T> getNode(int index) {
	        return getNode(index, 0, size());
	    }

	    private Node<T> getNode(int index, int lower, int upper) {
	        Node<T> p;
	        if (index < lower || index > upper) {
	            throw new IndexOutOfBoundsException();
	        }

	        if (index < size() / 2) {
	            p = head.next;
	            for (int i = 0; i < index; i++) {
	                p = p.next;
	            }
	        } else {
	            p = tail.pre;
	            for (int i = size()-1; i > index; i--) {
	                p = p.pre;
	            }
	        }
	        return p;
	    }

	    public int size() {
	        return dataSize;
	    }

	    public boolean isEmpty() {
	        return size() == 0;
	    }
}
