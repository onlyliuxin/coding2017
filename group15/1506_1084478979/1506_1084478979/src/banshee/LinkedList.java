package banshee;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	private Node last;
	private int  size = 0;
	
	public void add(Object o){
		addAtLast(o);
	}
	public void add(int index , Object o){
		rangeCheck(index);
		if (index   == size) {
			addAtLast(o);
		}else{
			linkBrfore(o, node(index));
		}
	}
	public Object get(int index){
		rangeCheck(index);
		return node(index);
	}
	public Object remove(int index){
		Node e = node(index);
		remove(e);
		return null;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		final Node h = head ;
		final Node newNode = new Node(null, o, h);
		if (h == null) {
			last = newNode;
		}else{
			h.prev = newNode;
		}
		size++;
	}
	public void addLast(Object o){
		addAtLast(o);	
	}
	public Object removeFirst(){
		final Node h = head;
		final Object e = h.data;
		 Node next = h.next;
		h.data = null;
		h.next = null;
		head = next;
		if (next == null)
			last = null;
		else 
			next.prev = null;
		size--;
		return e;
	}
	public Object removeLast(){
		final Node l = last;
		final Object e  = l.data;
		Node newL = l.prev;
		l.data = null;
		l.prev = null;
		last = newL;
		if (newL == null) 
			head = null;
		else
			newL.next = null;
		size--;
		return e;
	}
	public Iterator iterator(){
		//TODO
		//不会...
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		Node prev;
		
		Node(Node prev, Object element,Node next){
			this.data = element ;
			this.next = next;
			this.prev  = prev ;
		}	
	}
	
	private void addAtLast(Object element){
		Node  l = last;
		Node newLink = new Node(l, element, null);
		last = newLink;
		if (l == null) {
			head = newLink;
		}else {
			l.next = newLink;
		}
		size++;
	}
	
	private void linkBrfore(Object element , Node spNode ){
			final Node pred = spNode.prev;
			final Node  newNode = new Node(pred, element, spNode);
			spNode.prev = newNode;
			if (pred == null) {
				head = newNode;
			}else{
				pred.next = newNode;
			}
			size++;
	}
	
    private void rangeCheck(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("指定的index超过界限");
        }
    }
    
    private Node node(int index) {
        if (index < (size >> 1)) {
            Node  x = head;
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
    
   private Object remove(Node e) {
       if (e == head )
          throw new NoSuchElementException();
       Object result = e. data;
      e. prev.next = e.next;
      e. next.prev = e.prev;
       e. next = e.prev = null;
       e. data = null;
       size--;
       return result;
   }
	
}


