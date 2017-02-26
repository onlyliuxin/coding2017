package week1;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList<E> implements List<E> {
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
	@Override
	public boolean add(E arg0) {
		Node n = new Node(arg0);
		n.next = tail;
		n.ahead = tail.ahead;
		tail.ahead.next = n;
		tail.ahead = n;
		size += 1;
		return true;
	}

	@Override
	public void add(int arg0, E arg1) {
		if(arg0<0) arg0=0;
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
        	
        }
		return res;
	}

	@Override
	public int indexOf(Object arg0) {

		return 0;
	}

	@Override
	public boolean isEmpty() {

		return false;
	}

	@Override
	public Iterator<E> iterator() {

		return null;
	}

	@Override
	public int lastIndexOf(Object arg0) {

		return 0;
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

		return false;
	}

	@Override
	public E remove(int arg0) {

		return null;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {

		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {

		return false;
	}

	@Override
	public E set(int arg0, E arg1) {

		return null;
	}

	@Override
	public int size() {

		return 0;
	}

	@Override
	public List<E> subList(int arg0, int arg1) {

		return null;
	}

	@Override
	public Object[] toArray() {

		return null;
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
