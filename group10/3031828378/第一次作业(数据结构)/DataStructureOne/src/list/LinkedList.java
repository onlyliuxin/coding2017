package list;

public class LinkedList implements List {
	private int size ; 
		Node first;
		Node last;

	@Override
	public void add(int paramInt, Object paramE) {
		checkPositionIndex(paramInt);
		if(paramInt==size){
			linkLast(paramE);
		}else{
			linkBefore(paramE,node(paramInt));
		}
		
		
	}

	private Node node(int paramInt) {
		
			return null;
		
	}

	private void linkBefore(Object paramE,Node object) {
		
	}

	private void checkPositionIndex(int paramInt) {
		if (paramInt < 0 || paramInt > size) {
			throw new IndexOutOfBoundsException("Index: "+paramInt+", Size: "+size);
		}		
	}

	@Override
	public boolean add(Object paramE) {
		linkLast(paramE);		
		return true;
	}

	private void linkLast(Object paramE) {
		Node l = last;
		Node newNode = new Node(last,paramE,l);
		last = newNode;
		if(l==null){
			first=newNode;
		}else{
			l.next=newNode;
		}
		size++;		
	}

	@Override
	public void clear() {
		
	}

	@Override
	public boolean contains(Object paramObject) {
		return false;
	}

	@Override
	public Object get(int paramInt) {
		return null;
	}

	@Override
	public int indexOf(Object paramObject) {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Iterator iterator() {
		return null;
	}

	@Override
	public Object remove(int paramInt) {
		return null;
	}

	@Override
	public boolean remove(Object paramObject) {
		return false;
	}

	@Override
	public Object set(int paramInt, Object paramE) {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}
	
	private static  class Node{
		Object item ;
		Node next;
		Node prev;
		Node(Node prev,Object item  , Node next){
			this.item = item;
			this.next = next;
			this.prev =prev;
		}
		
	}

}
