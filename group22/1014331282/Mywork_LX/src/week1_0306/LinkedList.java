package week1_0306;



public class LinkedList implements List
{	
	private Node head;
	
	private static int size = 0;
	
	public LinkedList()
	{
		 head=new Node();

	}
	
	
	private static class Node
	{
		Object data;
		Node next;
	}
	
	public void add(Object o)
	{
		Node h = head;
		while(h.next!= null)
		{
			h=h.next;
		}
		Node p = new Node();
		p.data=o;
		p.next=null;
		h.next= p;
		
		size++;
	}
	
	public void add(int index, Object o)
	{
		Node h=head.next;
		int i=0;
		while(i<index && h!=null)
		{
			h=h.next;
			i++;
		}
		if(h!=null)
		{
			Node p = new Node();
			p.data=o;
			p.next=null;
			h.next= p;
		}
		
		size++;
	}
	
	public Object get(int index)
	{
		Node h= head.next;
		int i=0;
		while(i<index && h!=null)
		{
			h=h.next;
			i++;
		}
		if(h!=null)
		{
			return h.data;	
		}
		else return null;
			
	}
	
	public Object remove(int index)
	{
		Node h= head.next;
		int i=0;
		while(i<index-1 && h!=null)
		{
			h=h.next;
			i++;
		}
		if(h!=null)
		{
			if(h.next!=null)
			{
				Node p = h.next;
				h.next=h.next.next;
				size--;
				return p.data;
			}
			else return null;
		}
		else return null;
	}
	
	public int size()
	{
		int i=0;
		Node h = head;
		while(h.next!= null)
		{
			h=h.next;
			i++;
		}
		return i;
	}
	
	public void addFirst(Object o)
	{
			Node h = head.next;
			Node p=new Node();
			p.data=o;
			head.next=p;
			p.next=h;	
	}
	
	public Object removeFirst()
	{
		Node h = head.next.next;
		Node p = new Node();
		p=head.next;
		head.next=h;
		return p.data;
	}
	
	public Object removeLast()
	{
		Node h=head;
		while(h.next.next!=null)
		{
			h=h.next;
		}
		Node p=new Node();
		p=h.next;
		h.next=null;
		return p.data;
	}
	
	public Iterator iterator()
	{
		return new LinkedListIterator(this);
	}

	private class LinkedListIterator implements Iterator
	{
		LinkedList l=null;
		int pos = 0;
		private LinkedListIterator(LinkedList l)
		{
			this.l=l;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			pos++;
			if(pos > size)
				return false;
			else return true;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			return l.get(pos);
			
		}
		
		public Object remove()
		{
			return this.l.remove(pos);
		}
		
	}
	
	
}

