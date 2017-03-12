package basic;


import javax.swing.*;

public class LinkedList implements List {
	
	private Node head;

	private int N;

	private static  class Node {

		Object data;
		Node next;

		public Node(Object data) {
			this.data = data;
			next = null;

		}
	}


	public void add(Object o){

		if (head == null) {
			head = new Node(o);
		} else {
			Node oldHead = head;
			while (oldHead.next != null){
				oldHead = oldHead.next;
			}
			oldHead.next = new Node(o);
		}
		N++;
		
	}

	public void add(int index , Object o){

		if(index < 0 || index > N ) throw new IndexOutOfBoundsException("Index incorrect");

		if (index == 0) {
			Node node = new Node(o);
			node.next = head;
			head = node;
		} else {
			Node oldHead = head;
			for (int i = 0 ; i < index - 1; i++){
				oldHead = oldHead.next;
			}

			Node node = new Node(o);
			node.next = oldHead.next;
			oldHead.next = node;
		}
		N++;
	}

	public Object get(int index){

		if(index < 0 || index > N ) throw new IndexOutOfBoundsException("Index incorrect");
		Node oldNode = head;
		for (int i = 0;i < index - 1; i ++ ) {
			oldNode = oldNode.next;
		}

		return oldNode.data;
	}

	public Object remove(int index){

		if(index < 0 || index > N ) throw new IndexOutOfBoundsException("Index incorrect");
		Node oldNode = head;

		if (index == 0) {
			head = head.next;
		} else {

			Node pos = head;
			for (int i = 0;i<index - 1 ;i++ ){
				pos = pos.next;
			}
			oldNode = pos.next;
			pos.next = pos.next.next;

		}

		return oldNode.data;
	}
	
	public int size(){
		return N;
	}
	
	public void addFirst(Object o){
		add(0,o);
	}
	public void addLast(Object o){
		add(N,o);
	}
	public Object removeFirst(){
		return remove(0);
	}
	public Object removeLast(){
		return remove(N-1);
	}


	public Iterator iterator(){
		return new LinkedListIterator();
	}

	class LinkedListIterator implements Iterator{

		private Node node = head;
		private int pos = 0;
		@Override
		public boolean hasNext() {
			return pos < N;
		}

		@Override
		public Object next() {
			pos++;
			if(pos != 1){
				node = node.next;
			}
			return node.data;
		}
	}

}
