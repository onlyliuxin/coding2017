package data_structure;


import java.util.Iterator;
import java.util.NoSuchElementException;



public class MyLinkedList {
	private int size;
	private Node head;
	private Node last;
	
	public void add(Object o){
	
			linkLast(o);
		
	}
	
	public Object get(int index){
		checkPositionIndex(index);
		Node node=node(index);
		return node.item;
	}
	public Object remove(int index){
		checkPositionIndex(index);
		Node node=node(index);
	    isnull(node);
	    Object o=null;
		Node before=null;
		if(index==0){
			o=node.item;
			node.next=head;
			node=null;
		}
		else
		{
			before=node(index-1);
			before.next=node.next;
			o=node.item;
			node=null;
			
		}
		
		return o;
	}
	public int size(){
		
		return size;
	}
	public void addFirst(Object o){
		
		linkFirst(o);
			
		
	}
	public void addLast(Object o){
		linkLast(o);
		
	}
	public Object removeFirst(){
		 Node f=head;
	     isnull(f);
		final Node next=head.next;
		Object o=f.item;
		f=null;
		head=next;
		if(next==null)
			last=null;
		size--;
		
		return o;
	}
	//这个方法用多了也爆炸
	public Object removeLast(){
		 Node l=last;
	     isnull(l);
	     Object o=null;
	     if(size>=2){
			final Node before=node(size-1);
			o=l.item;
			l=null;
			last=before;
		
			if(before==null)
				head=null;
	     }
	     else{
	    	o=l.item;
	    	l=null;
	    	last=null;
	    	head=null;
	     }
	    	 

			size--;
		return o;
		
	}
	public Iterator<Object> iterator(){
		
		 
	       return new ListItr();
	}
	
	
	private static class Node{
		Object item;
		Node next;
	   Node(Object o, Node next){
			this.item=o;
			this.next=next;
		}
	}
    public void add(int index, Object o) {
        checkPositionIndex(index);

        if (index == 0)
            linkFirst(o);
        else
            linkNext(o, node(index-1));
    }
    private void linkNext(Object o,Node node){
    	final Node next=node.next;
    	final Node newnode=new Node(o,next);
    	node.next=newnode;   		
    	size++;
    }
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
    private void linkLast(Object o){
    	final Node l=last;
		final Node node=new Node(o,null);
		last=node;
		if(head==null)
			head=node;
		else
			l.next=node;
    	size++;
    }
    private void linkFirst(Object o){
    	final Node f=head;
		final Node node=new Node(o,null);
		head=node;
		if(last==null)
			last=node;
		else
			head.next=f;
		size++;
    }
    private void isnull(Node node){
    	 if (node == null)
	            throw new NoSuchElementException();
    }
    Node node(int index) {
        // assert isElementIndex(index);

       
            Node x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
       
     
    }
    
    private class ListItr implements Iterator<Object> {
    	   private Node next=head;
           private int nextIndex;
           private Node lastReturned = null;
           
           public boolean hasNext() {
               return nextIndex < size;
           }
           
           public Object next() {
        
               if (!hasNext())
                   throw new NoSuchElementException();

               lastReturned = next;
               next = next.next;
               nextIndex++;
               //加上死循环了
//               if(nextIndex==size){
//            	   next=head;
//            	   nextIndex=0;
//               }
               return lastReturned.item;
           }

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
    }
        


}
