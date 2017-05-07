package com.homework01;

public class LinkedList {
	private static Node head;
	private Node last;
	public int size;

	public void add(Object o) {
		Node l = last;
		Node newNode = new Node(l, o, null);
		last = newNode;
		if (head == null) {
			head = newNode;
			size = 1;
		} else {
			l.next = newNode;
			size++;
		}
	}

	public void add(int index, Object o) {
		Node n = node(index);
		System.out.println(n.data);
		Node pred = n.prev;
		Node newNode = new Node(pred, o, n);
		if (pred == null) {
			head = newNode;
		} else {
			pred.next = newNode;
		}
		size++;
	}

	
	public Node get(int index){
		return node(index);
	}
	public Node node(int index) {
		Node n = head;
		for (int i = 0; i < index; i++) {
			n = n.next;
		}
		return n;
	}

	public Node remove(int index){
		Node del = node(index);
		Node after = del.next;
		Node before = del.prev;
		before.next = after;
		after.prev = before;
		size--;
		return del;
	}
	private static class Node {
		Node next;
		Object data;
		Node prev;

		private Node(Node prev, Object data, Node next) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}

	public static void main(String[] arg) {
		LinkedList ll = new LinkedList();
		ll.add("hello");
		ll.add("java");
		ll.add("jvm");
		ll.add("jvmd");
		// System.out.println(ll.get(2));
//		ll.add(1, "ds");
		System.out.println(ll.get(0).data);
		System.out.println(ll.get(1).data);
		System.out.println(ll.get(2).data);
		System.out.println(ll.get(3).data);
		System.out.println(ll.size);
	System.out.println(ll.remove(1).data);
	System.out.println(ll.get(0).data);
	System.out.println(ll.get(1).data);
	System.out.println(ll.get(2).data);
	System.out.println(ll.size);
	}

}
