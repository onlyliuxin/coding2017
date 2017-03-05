package com.coding.basic;

public class LinkedList implements List {

	private Node head;
	private Node tail;

	public void add(Object o){
		Node nowNode = new Node(o);
		if (head == null) {
			head = nowNode;
		} else {
			tail.next = nowNode;
		}
		tail = nowNode;
	}
	public void add(int index , Object o){
		int count = 0;
        Node lastOne = null;
		Node tpHead = head;
		while (tpHead != null && count != index) {
			count++;
            lastOne = tpHead;
			tpHead = tpHead.next;
		}
        if (count != index) {
            throw new IndexOutOfBoundsException();
        }


        Node nowNode = new Node(o);
        if (lastOne == null) {
            head = nowNode;
            head.next = tpHead;
        } else {
            lastOne.next = nowNode;
            nowNode.next = tpHead;
        }
	}
	public Object get(int index){
        int count = 0;
        Node tpHead = head;
        while (tpHead != null && count != index) {
            count++;
            tpHead = tpHead.next;
        }
        if (count != index) {
            throw new IndexOutOfBoundsException();
        }

		return tpHead.data;
	}
	public Object remove(int index){
        int count = 0;
        Node lastOne = null;
        Node tpHead = head;
        while (tpHead != null && count != index) {
            count++;
            lastOne = tpHead;
            tpHead = tpHead.next;
        }
        if (count != index) {
            throw new IndexOutOfBoundsException();
        }

        if (lastOne == null) {
            head = tpHead.next;
        } else {
            lastOne.next = tpHead.next;
        }

        if (tpHead.next == null) {
            tail = lastOne;
        }

		return tpHead.data;
	}
	
	public int size(){
        int count = 0;
        Node tpHead = head;
        while (tpHead != null) {
            count ++;
            tpHead = tpHead.next;
        }

        return count;
	}
	
	public void addFirst(Object o){
        Node nowNode = new Node(o);
        if (head == null) {
            head = nowNode;
            tail = nowNode;
        } else {
            nowNode.next = head;
            head = nowNode;
        }
	}
	public void addLast(Object o){
        Node nowNode = new Node(o);
        if (head == null) {
            head = nowNode;
            tail = nowNode;
        } else {
            tail.next = nowNode;
            tail = nowNode;
        }
	}
	public Object removeFirst(){
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }

        Node nowValue = head;

        Node nextNode = head.next;
        if (nextNode == null) {
            tail = null;
        }
        head = nextNode;

		return nowValue.data;
	}
	public Object removeLast(){
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }

        Node nowValue = tail;

        Node lastOne = null;
        Node tpHead = head;
        while (tpHead != tail) {
            lastOne = tpHead;
            tpHead = tpHead.next;
        }
        if (lastOne == null) {
            head = null;
        } else {
            lastOne.next = null;
        }
        tail = lastOne;

		return nowValue.data;
	}

    private class LinkIter implements Iterator {

        Node cursor = head;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public Object next() {
            Node ret = cursor;
            cursor = cursor.next;
            return ret.data;
        }
    }

	public Iterator iterator(){
        return new LinkIter();
	}
	
	
	private static class Node{
		Object data;
		Node next;

		public Node(Object data) {
			this.data = data;
		}
	}

    @Override
    public String toString() {
        Node tpHead = head;
        StringBuilder sb = new StringBuilder("[");
        while (tpHead != null) {
            sb.append(tpHead.data);
            sb.append(",");
            tpHead = tpHead.next;
        }
        sb.append("]");
        return sb.toString();
    }

}
