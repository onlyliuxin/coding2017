package day20170219;

public class LinkedList implements List {
	
	private Node head;
	private Node last;
	private int size = 0;
	
	public void add(Object o){
		addLast(o);
	}
	
	public void add(Object o, int index){
		
	}
	
	public Object get(int index){
		checkSize();
		return getNode(index).data;
	}
	
	public Object remove(int index){
		checkSize();
		final Node c = getNode(index);
		final Node p = c.prev;
		final Node n = c.next;
		p.next = n;
		n.prev = p;
		c.prev = null;
		c.next = null;
		return c.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		final Node h = head;
		final Node newNode = new Node(null, o, h);
		head = newNode;
		if(h == null){
			head = newNode;
		} else {
			h.prev = newNode;
		}
	}
	
	public void addLast(Object o){
		final Node l = last;
		final Node newNode = new Node(l, o, null);
		last = newNode;
		if (l == null){
			head = newNode;
		} else {
			l.next = newNode;
		}
		size ++;
	}
	
	public Object removeFirst(){
		final Node f = head;
		final Object element = head.data;
		final Node next = head.next;
		f.data = null;
		f.next = null;
		head = next;
		if (next == null){
			last = null;
		} else {
			next.prev = null;
		}
		size --;
		return element;
	}
	
	public Object removeLast(){
		final Node l = last;
		final Object element = last.data;
		final Node prev = last.prev;
		l.data = null;
		l.prev = null;
		last = prev;
		if (prev == null){
			head = null;
		} else {
			prev.next = null;
		}
		return element;
	}
	
	private Node getNode(int index) {
	        if (index < (size >> 1)) {
	            Node x = head;
	            for (int i = 0; i < index; i++)
	                x = x.next;
	            return x;
	        } else {
	            Node x = last;
	            for (int i = size - 1; i > index; i--)
	                x = x.prev;
	            return x;
	        }
	    }
	private void checkSize(){
		if(size < 0){
			throw new RuntimeException("out of size");
		}
	}
	private static class Node{
		
		private Node prev;
		private Object data;
		private Node next;
		
		Node(Node prev, Object data, Node next){
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		
	}
}
