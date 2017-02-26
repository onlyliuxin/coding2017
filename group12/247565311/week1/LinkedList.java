package week1;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList<E> implements List<E>,Cloneable {
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
	@Override
	public boolean add(Object val) {
		Node n = new Node(val);
		n.next = tail;
		n.ahead = tail.ahead;
		tail.ahead.next = n;
		tail.ahead = n;
		size += 1;
		return true;
	}

	@Override
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

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
        for(E o:arg0){
        	this.add(o);
        }
		return true;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1) {
        for(E e:arg1){
        	this.add(arg0,e);
        	arg0+=1;
        }
		return true;
	}

	@Override
	public void clear() {
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.ahead = head;
        size = 0;
	}

	@Override
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

	@Override
	public boolean containsAll(Collection<?> arg0) {
        for(Object e:arg0){
        	if(!this.contains(e)) return false;
        }
		return true;
	}

	@Override
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

	@Override
	public int indexOf(Object arg0) {
        boolean flag = arg0 == null;
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

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public Iterator<E> iterator() {

		return null;
	}

	@Override
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

	@Override
	public ListIterator<E> listIterator() {

		return null;
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {

		return null;
	}

	@Override
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

	@Override
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

	@Override
	public boolean removeAll(Collection<?> arg0) {
		for(Object o:arg0){
			if(!this.remove(o)) return false;
		}
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// ?
		return false;
	}

	@Override
	public E set(int arg0, E arg1) {
        if(arg0<0 || arg0>size-1) return null;
        Node n=head;
        for(int i=0;i<arg0;i++){
        	n = n.next;
        }
        n.next.val = arg1;
		return (E)(n.next.val);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<E> subList(int arg0, int arg1) {

		return null;
	}

	@Override
	public Object[] toArray() {
		Object[]res = new Object[size];
		Node n = head;
		for(int i=0;i<size;i++){
			n = n.next;
			res[i] = n.val;		// if we change res[],will this list be changed?
		}
		return res;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {

		return null;
	}
	private static class Node{
		Object val = null;
		Node next = null,ahead=null;
		public Node(Object arg0){val = arg0;}
	}
}
