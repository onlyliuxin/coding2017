package Collection.Concrete;

import java.util.NoSuchElementException;
import Collection.AbstractList;
import Collection.Iterator;


public class LinkedList<E> extends AbstractList<E> {

	private Node head;
	private int size;
	
	public LinkedList() {
		this.head = new Node<E>(null);
		this.size = 0;
	}
	
	@Override
	public void add(E e) {
		addLast(e);
	}
	
	
	@Override
	public E get(int index) {
		checkIndex(index);
		return getNode(index).data;
	}
	
	public E getFirst() {
		return get(0);
	}
	
	public E getLast() {
		return get(size-1);
	}
	
	
	public void add(int index, E e) {		
		if (index == size) {
			addLast(e);
			return;
		} 
		
		if (index == 0) {
			addFirst(e);
			return;
		}
		
		checkIndex(index);
		Node<E> pNode = new Node<E>(e);
		Node<E> p = getNode(index);
		synchronized (this) {
			getNode(index-1).next = pNode;
			pNode.next = p;
			size++;
		}
	}

	public void addFirst(E e){
		checkCapacity();
		Node<E> pNode = new Node<E>(e);
		Node oldHead = head;
		head = pNode;
		pNode.next = oldHead;
		size++;
		return;
	}
	
	public void addLast(E e){
		if (size == 0) {
			addFirst(e);
			return;
		}
		
		checkCapacity();
		Node res = new Node<E>(e);
		setLastNode(res);
		size++;
		return;
	}
	
	public E removeFirst(){
		return remove(0);
	}
	public E removeLast(){
		return remove(size-1);
	}

	public E remove(int index) {
		checkIndex(index);
		Node<E> pNode = getNode(index);
		if (index == 0) {
			head =  head.next;
		} else if (index == size-1 ) {
			getNode(index-1).next = null;
		} else {
			getNode(index-1).next = getNode(index+1);
		}
		size--;
		return pNode.data;
	}

	@Override
	public int size() {
		return size;
	}
	
	public Iterator<E> iterator(){
		return new LinkedListIterator<E>(this);
	}

	private void checkCapacity() {
		if (size > MAX_SIZE)
			throw new IndexOutOfBoundsException("Reached max capacity: "+ MAX_SIZE);
	}	

	private Node<E> getNode(int index) {
		if (size == 0)
			return head;
	
		Node pNode = head;
		for ( int i = 0; i < index ; i++) {
			pNode = pNode.next;
		}
		return pNode;
	}
	
	private void setLastNode(Node res) {
		getNode(size-1).next = res;
	}

	private static class Node<E> {
		E data;
		Node next;
		
		public Node(E data) {
			this.data = data;
			this.next = null;
		}

		@Override
		public String toString() {
			return data.toString();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((data == null) ? 0 : data.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (data == null) {
				if (other.data != null)
					return false;
			} else if (!data.equals(other.data))
				return false;
			return true;
		}
	}
	
	@SuppressWarnings("hiding")
	private class LinkedListIterator<E> implements Iterator<E> {

		private LinkedList<E> myLinkedList;
		private int pos;
		
		public LinkedListIterator(LinkedList<E> linkedList) {
			myLinkedList = linkedList;
			pos = 0;
		}

		@Override
		public boolean hasNext() {
			return pos < size;
		}

		@Override
		public E next() {
			if (hasNext())
				return (E) get(pos++);
			throw new NoSuchElementException();
		}
	}
}
