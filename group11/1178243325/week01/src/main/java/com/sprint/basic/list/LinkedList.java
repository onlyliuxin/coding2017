package com.sprint.basic.list;

import com.sprint.basic.exception.ConcurrentModificationException;
import com.sprint.basic.Iterator;
import java.util.Objects;
public class LinkedList implements List {
	
	private Node head;
	private int size;
	public LinkedList() {
		head = new Node(null, null);	
		size = 0;
	}

	public boolean add(Object o) {
		add(size, o);		
		return true;
	}

	public boolean add(int index , Object o) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
		Node frontNode = getNode(index-1);
		Node newNode = new Node(o, frontNode.next);		
		frontNode.next = newNode;
		size++;
		return true;
	}

	/*getNode getPreNodeByElement getNextNodeByElement的效率低些*/
	private Node getNode(int index) {
		Node node = head;
		int i = 0;
		while(node.next != null && i <= index) {
			node = node.next;
			i++;
		}
		return node;
	}

	private Node getPreNodeByElement(Object obj) {
		if (obj != null) {
			for (int i = 0; i < size(); i++) {
				if (getNode(i).data == obj) {
					return getNode(i-1);
				}
			}		
		}
		return null;
	}

	private Node getNextNodeByElement(Object obj) {
		if (obj != null) {
			for (int i = 0; i < size(); i++) {
				if (getNode(i).data == obj) {
					return getNode(i+1); 
				}
			}	
		}
		return null;
	}

	public Object get(int index){
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}

		Node node = getNode(index);	
		return node.data;
	}

	public Object remove(int index){
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
		Node frontNode = getNode(index-1);
		Node oldNode = getNode(index);
		frontNode.next = oldNode.next;
		size--;
		return oldNode.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		add(0, o);
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
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator {
		int index;
		final int capacity = size;
		LinkedListIterator() {
			index = 0;
		}
		@Override
		public boolean hasNext() {
			if (capacity != size)
				throw new ConcurrentModificationException("此对象没有修改同步");
			return index < capacity;	
		}

		@Override 
		public Object next() {
			if (capacity != size)
				throw new ConcurrentModificationException("此对象没有修改同步");
			if (index >= capacity)
				throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
			return get(index++);	
		}
	}

	private String outOfBoundsMsg(int index) {
		return "index:" + index + ", size:" + size;
	}	

	private static  class Node {
		Object data;
		Node next;
		
		Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}

		void setData(Object data) {
			this.data = data;	
		}

		Object getData() {
			return data; 
		}

		void setNext(Node next) {
			this.next = next;
		}

		Object getNext() {
			return next;
		}
	}
}
