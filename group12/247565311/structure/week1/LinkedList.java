package structure.week1;

import java.util.Collection;

public class LinkedList<E> {
	private Node head = null;
	private Node tail = null;
	private int size = 0;
	
	public LinkedList(){
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.ahead = head;
        size = 0;
	}
	public LinkedList(int arg0){
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.ahead = head;
        size = 0;
	}
	public Object clone(){
		LinkedList<E> clone = null;
		try {
			clone = (LinkedList<E>)(super.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		clone.head = new Node(null);
		clone.tail = new Node(null);
		clone.size = 0;
		for(Node x = head.next;x!=null;x = x.next){
			clone.add(x.val);
		}
		return clone;
	}
	public void add(Object val) {
		Node n = new Node(val);
		n.next = tail;
		n.ahead = tail.ahead;
		tail.ahead.next = n;
		tail.ahead = n;
		size += 1;
	}

	public void add(int arg0, E arg1) {
		if(arg0<0 || arg0>size) arg0=0;
		Node n=new Node(arg1),p=head;
		for(int i=0;i<arg0;i++){
			p = p.next;
		}
		n.next = p.next;
		n.ahead = p;
		p.next.ahead = n;
		p.next = n;
		size += 1;
	}

	public boolean addAll(Collection<? extends E> arg0) {
        for(E o:arg0){
        	this.add(o);
        }
		return true;
	}

	public boolean addAll(int arg0, Collection<? extends E> arg1) {
        for(E e:arg1){
        	this.add(arg0,e);
        	arg0+=1;
        }
		return true;
	}

	public void clear() {
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.ahead = head;
        size = 0;
	}

	public boolean contains(Object arg0) {
		boolean flag = arg0==null;
        Node n = head;
        for(int i=0;i<size;i++){
        	n = n.next;
        	if(flag){
        		if( n.val == null) return true;
        	}else{
        		if(arg0.equals(n.val)) return true;
        	}
        }
		return false;
	}

	public boolean containsAll(Collection<?> arg0) {
        for(Object e:arg0){
        	if(!this.contains(e)) return false;
        }
		return true;
	}

	public E get(int arg0) {
		E res = null;
        if(arg0>-1 && arg0 < size){
        	Node n = head;
        	for(int i=0;i<arg0;i++){
        		n = n.next;
        	}
        	res = (E) n.val;
        }
		return res;
	}

	public int indexOf(Object arg0) {
        boolean flag = (arg0 == null);
        Node n=head;
        for(int i=0;i<size;i++){
            n = n.next;
        	if(flag){
        		if(n.val == null) return i;
        	}else{
        		if(arg0.equals(n.val)) return i;
        	}
        }
		return -1;
	}

	public boolean isEmpty() {
		return size==0;
	}

	public Iterator<E> iterator() {

		return null;
	}

	public int lastIndexOf(Object arg0) {
		boolean flag = arg0==null;
		Node n = tail;
		for(int i=size-1;i>-1;i--){
			n = n.ahead;
			if(flag){
				if(n.val == null) return i;
			}else{
				if(arg0.equals(n.val)) return i;
			}
		}
		return -1;
	}

	public boolean remove(Object arg0) {
        Node n = head;
        int index = this.indexOf(arg0);
        if(index == -1) return false;
        for(int i=0;i<index;i++){
        	n = n.next;
        }
        Node d = n.next;
        n.next = d.next;
        d.next.ahead = n;
		return true;
	}

	public E remove(int arg0) {
        Node n = head;
        if(arg0 <0 || arg0>size-1) return null;
        for(int i=0;i<arg0;i++){
        	n = n.next;
        }
        Node d = n.next;
        n.next = d.next;
        d.next.ahead = n;
		return (E)(d.val);
	}

	public boolean removeAll(Collection<?> arg0) {
		for(Object o:arg0){
			if(!this.remove(o)) return false;
		}
		return true;
	}

	public boolean retainAll(Collection<?> arg0) {
		// ?
		return false;
	}

	public E set(int arg0, E arg1) {
        if(arg0<0 || arg0>size-1) return null;
        Node n=head;
        for(int i=0;i<arg0;i++){
        	n = n.next;
        }
        n.next.val = arg1;
		return (E)(n.next.val);
	}

	public int size() {
		return size;
	}

	public Object[] toArray() {
		Object[]res = new Object[size];
		Node n = head;
		for(int i=0;i<size;i++){
			n = n.next;
			res[i] = n.val;		// if we change res[],will this list be changed?
		}
		return res;
	}

	public <T> T[] toArray(T[] arg0) {
		T[]res = (T[]) new Object[size];
		Node n = head;
		for(int i=0;i<size;i++){
			n = n.next;
			res[i] = (T)n.val;		// if we change res[],will this list be changed?
		}
		return res;
	}
	private static class Node{
		Object val = null;
		Node next = null,ahead=null;
		public Node(Object arg0){val = arg0;}
	}

	public boolean hasNext() {
		return false;
	}

	public Object next() {
		return null;
	}
}
