package org.dul.CodingTask.homework_2017_2_25;

public class LinkedList implements List {

	private Node start;
	private Node end;
	private int size;

	public void add(Object object) {
		if (start == null) {
			start = new Node(object);
			end = start;
		} else {
			Node node = new Node(object);
			start.setNext(node);
			end = node;
		}
		size++;
	}

	public void add(int index, Object object) {
	}
	

	public Object get(int index) {
		if(index > size -1)
			return null;
		
		Node current = start;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getData();
	}

	public Object remove(int index) {
		return null;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object object) {
		Node node = new Node(object, start);
		start = node;
	}

	public void addEnd(Object object) {
		Node node = new Node(object);
		end.setNext(node);
		end = node;
	}

	public Object removeFirst() {
		Object data = start.getData();
		start = start.getNext();
		return data;

	}

	public Object removeEnd() {
		Object data = end.getData();
		Node current = start;
		while(true){
			if (current.getNext() == end){
				end = current;
				current.setNext(null);
				break;
			}else {
				current = current.getNext();
			}
		}
		return data;
	}

	public Iterator iterator() {
		return null;
	}
}
