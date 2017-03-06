import java.util.*;

public  class MyLinkedList implements List {
	
	private Node first;
	private Node last;
	private int size = 0 ;
	public void Myadd(Object o){
		final Node l = last;
		final Node newNode = new Node(l,o,null);
		last = newNode;
		if(l == null)
			first = newNode;
		else
			l.next = newNode;
		size++;
	}
	public void Myadd(int index , Object o){
		checkPosition(index);
		if(index == size){
			Myadd(o);
		}
		else{
			final Node PreNode =GetNodeByIndex(index).prev;
			final Node newNode = new Node(PreNode,o,GetNodeByIndex(index));
			PreNode.next =newNode; 
			if(PreNode == null)
				first =  newNode; //为什么要付给首指针？ 
		   else
			   PreNode.next = newNode;
		   size++;
	       }
	}
	public Object get(int index){
		checkPosition(index);
		return GetNodeByIndex(index);
	}
	public void remove(int index){
		Node node = GetNodeByIndex(index);
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node = null;
		size--;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		final Node FirstNode= first;
		final Node newNode = new Node(null,o,first);
		first = newNode;
		if(FirstNode == null)
			last = newNode;
		else
			first.prev = newNode;
	    size++;
			
	}
	public void addLast(Object o){
		final Node LastNode = last;
		final Node newNode = new Node(last,o,null);
		last = newNode;
		if(last == null)
			first = newNode;
		else
			last.next = newNode;
		size++;
	}
	public void removeFirst(){
		final Node f = first;
		if(f == null)
			throw new NoSuchElementException();
        first = f.next;
        first = null;
		size--;
	}
	public void removeLast(){
		final Node f = last;
		if(f == null)
			throw new NoSuchElementException();
		last = last.prev;
		last = null;
        size--;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object item;
		Node next;
		Node prev;
		Node (Node prev,Object element ,Node next){
			this.item = element;
			this.next = next;
			this.prev = prev;		
		}	
	}

	private Node GetNodeByIndex(int index){
       if(index > size/2)
       {
    	   Node Temp = first;
    	   for(int i = 0; i< index;i++)
    		   Temp = Temp.next; //
    	   return Temp;
       }
       else
       {
    	   Node Temp = last;
    	   for(int i = size-1; i> index; i--)
    		   Temp = Temp.prev;
    	   return Temp;   
       }
	}

	private void checkPosition(int index){
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("index:"+ index+"is llegal");
	}
}