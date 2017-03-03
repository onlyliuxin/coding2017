package com.dudy.learn01.base;


public class MyLinkedList implements MyList {

	private int size = 0;

	private Node head;

	public void add(Object o) {
		Node newNode = new Node(o);
		newNode.next = head;// next --> head
		head = newNode;     // head --> newNode
		size++;
	}

	public void add(int index, Object o) {
		checkRange(index);
		Node current = getCurrentNode(index);
		Node newNode = new Node(o);
		newNode.next = current.next;//new next --> next 
		current.next = newNode; // next -->new 
		size++;
	}

	private Node getCurrentNode(int index) {// 获取当前节点
		Node current = head;
		for(int i = 0; i< size-index -1; i++){
			current = current.next;
		}
		return current;
	}

	private void checkRange(int index) {
		if(index > size || index < 0){
			throw new RuntimeException("indexOutOfException:" + "Index: " + index + ", Size: " + size);
		}
	}

	public Object get(int index) {
		checkRange(index);
		Node node = getCurrentNode(index);
		return node.data;
	}

	public Object remove(int index) {
		if(size < 1){// ①没有元素的情况
			throw new RuntimeException("NoSuchElementException: size= " + size);
		}
		if(index == 0) return removeFirst();
		if(index == size - 1) return removeLast();
			
		Node node = getCurrentNode(index+1);
		node.next = node.next.next;
		size--;
		return node.data;
	}

	public int size() {
		return size;
	}

	public void addLast(Object o) {
		add(o);
	}

	public void addFirst(Object o) {
		Node current = head;
		while(current.next != null){// 找到最后一个节点
			current = current.next;
		}
		Node newNode = new Node(o);
		current.next = newNode;
		size++;
	}

	public Object removeFirst() {
		Node currentNode = getCurrentNode(1);
		Node tmp = currentNode.next;
		currentNode.next = null;
		size--;
		return tmp ==null? currentNode.data:tmp.data;// 可能只有一个数据
	}

	public Object removeLast() {
		Node tmp = head;
		head = head.next;
		size--;
		return tmp.data;
	}

	public MyIterator iterator() {
		return new MyLinkedListItr();
	}


	public class  MyLinkedListItr implements  MyIterator {

	    int cursor;

        public boolean hasNext() {
            return cursor != size;
        }

        public Object next() {
            Node currentNode = getCurrentNode(cursor++);
            return currentNode.data;
        }
    }

	private static class Node {
		Object data;
		Node next;
		public Node() {
		}
		public Node(Object data) {
			this.data = data;
		}
	}

	
	private void displayLink() {// 自己调试使用
		Node current = head;
		while(current != null){
			System.out.print(current.data);
			current = current.next;
		}
		System.out.println("");
	}
	
	
}