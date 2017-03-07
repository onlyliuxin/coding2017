package dataStructure;

/**
 * Created by LvZhenxing on 2017/2/21.
 */
public class LinkedList implements List {

	private Node head=new Node();
	private int size = 0;

	public void add(Object o) {
		addToNode(head,o);
		size++;
	}

	public void add(int index, Object o) {
		if (index <0 || index > size) {
			throw new IndexOutOfBoundsException("index out of bound");
		}

		addToNode(getLastNode(index),o);
		size++;
	}

	private Node getLastNode(int index){

		Node nowNode = head;
		for (int pos = 1; pos <= index; pos++) {
			nowNode = nowNode.next;
		}
		return nowNode;
	}
	private void addToNode(Node node,Object o){
		if (node.next == null) {
			Node newNode = new Node();
			newNode.data = o;
			node.next = newNode;
		} else {
			Node newNode = new Node();
			newNode.data = o;
			newNode.next = node.next;
			node.next = newNode;
		}
	}

	public Object get(int index) {
		if (index <0 || index > size - 1) {
			throw new IndexOutOfBoundsException("index out of bound");
		}

		Node node= getLastNode(index);
		return node.next==null?null:node.next.data;
	}

	public Object remove(int index) {
		if (index <0 || index > size - 1) {
			throw new IndexOutOfBoundsException("index out of bound");
		}

		Node node= getLastNode(index);
		Node nowNode=node.next;
		if(nowNode.next!=null){
			node.next=node.next.next;
		}else{
			node.next=null;
		}
		size--;
		return nowNode.data;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		add(0,o);
	}

	public void addLast(Object o) {
		add(size,o);
	}

	public Object removeFirst() {
		return remove(0);
	}

	public Object removeLast() {
		return remove(size-1);
	}

	public Iterator iterator() {
		return new LinkedListInterator();
	}

	private class LinkedListInterator implements Iterator {

		private int nowIndex = 0;

		public boolean hasNext() {
			if (nowIndex <= size - 1) {
				return true;
			}
			return false;
		}

		public Object next() {
			return get(nowIndex++);
		}
	}


	private static class Node {
		Object data;
		Node next;
	}
}
