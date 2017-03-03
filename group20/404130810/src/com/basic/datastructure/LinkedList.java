package com.basic.datastructure;

public class LinkedList implements List {
	private Node first;
	private Node last;

	private int size;

	public void add(Object item) {
		addLast(item);
	}

	public void add(int index, Object item) {
		checkRange(index);
		if (index == 0) {
			addFirst(item);
		} else if (index == size) {
			addLast(item);
		} else {
			Node tmpNode = new Node(item);
			Node preNode = get(index - 1);
			Node nextNode = get(index + 1);
			preNode.next = tmpNode;
			tmpNode.next = nextNode;
			size ++;
		}
	}

	public Node get(int index) {
		checkRange(index);
		if(size > 0){
			int loopTimes = 0;
			Node p = first;
			while(index  > loopTimes){
				p = p.next;
				loopTimes ++;
			}
			return p;
		}

		return null;
	}

	public Object remove(int index) {
		checkRange(index);
		Node tmpNode = null;
		if(index == 0){
			removeFirst();
		}else if(index == size -1){
			removeLast();
		}else{
			tmpNode = get(index);
			Node preNode = get(index-1);
			Node nextNode = get(index + 1);
			preNode.next = nextNode;
			size --;
		}
		
		return tmpNode;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object item) {
		if (size == 0) {
			first = new Node(item);
			last = first;
		} else {
			Node tmpNode = new Node(item);
			tmpNode.next = first;
			first = tmpNode;
		}
		size++;
	}

	public void addLast(Object item) {
		if (size == 0) {
			first = new Node(item);
			last = first;
		} else {
			last.next = new Node(item);
			last = last.next;
		}
		size++;
	}

	public Object removeFirst() {
		Node tmpNode = first;
		if(tmpNode == null){
			last = null;
		}else if(size == 2){
			first = last;
			last = null;
			size --;
		}else{
			first = first.next;
			size--;
		}
		return tmpNode;
	}

	public Object removeLast() {
		Node tmpNode = null;
		if(size == 1){
			this.removeFirst();
		}else if(size >0){
			int index = size - 1;
			tmpNode = last;
			get(index - 1).next = null;
			last = get(index - 1);
			size --;
		}
		return tmpNode;
	}

	private void checkRange(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + "Size: " + size);
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node p = first;
		while (p != null) {
			sb.append(p.item + "\n");
			p = p.next;
		}
		return sb.toString();
	}

	private static class Node {
		private Object item;
		private Node next;
		Node(Object item) {
			this.item = item;
		}
	}

	public static void main(String[] args) {
		
		/*Test add
		LinkedList list = new LinkedList();
		for (int i = 0; i <= 5; i++) {
			list.add(i);
		} 
		list.add(3, "test");
		System.out.println(list);
		*/
		
		/*Test remove
		list.remove(3);
		System.out.println(list);
		*/
		
		/*Test removeLast and removeFirst
		System.out.println(list);
		list.removeLast();
		System.out.println(list);
		list.removeLast();
		System.out.println(list);
		list.removeLast();
		System.out.println(list);
		*/
		
		/*Test from Java API
		java.util.LinkedList<String> linkedList = new java.util.LinkedList<String>();
		linkedList.add("test");
		linkedList.removeFirst();
		System.out.println(linkedList);
		*/
		
	}
}
