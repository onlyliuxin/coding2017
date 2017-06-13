package org.dul.CodingTask.homework_2017_2_25;

public class Node {
	private Object data;
	private Node next;
	private Node previous;

	public Node() {
	}

	public Node(Object data){
		this.data = data;
	}
	
	public Node(Object data, Node next) {
		this.data = data;
		this.next = next;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}
}