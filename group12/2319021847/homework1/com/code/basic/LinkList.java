package com.coding.basic;

public class LinkList implements List{
	
	private LinkNode head;
	// TODO Auto-generated method stub
	private int size;
	
	public LinkList()
	{
		this.head = null;
		this.size = 0;
	}
	public void add(Object o) {
		// TODO Auto-generated method stub
		LinkNode currPtr = this.head;
		if(this.head == null)
		{
			this.head =  new LinkNode(o, this.head ,null);
		}
		while(currPtr != null)
		{
			currPtr = currPtr.getNext();
		}
		currPtr.setNext(new LinkNode(o, currPtr, null));
		size++;
	}

	public void add(int index, Object o) {
		// TODO Auto-generated method stub
		LinkNode currPtr = this.head;
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("下标越界");
		int i = 0;
		if(index == 0)
		{
			LinkNode node = new LinkNode(o,currPtr,currPtr.getPrv());
			currPtr.getNext().setPrv(node); 
			currPtr = node;
		}
		while(i < index)
		{
			currPtr = currPtr.getNext();
			i++;
		}
		LinkNode node = new LinkNode(o,currPtr.getPrv(),currPtr);
		currPtr.getPrv().setNext(node);
		currPtr.setPrv(node);
		size++;
	}

	public Object get(int index) {
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("下标越界");
		int i = 0;
		LinkNode currPtr = this.head;
		while(i < index)
		{
			currPtr = currPtr.getNext();
			i++;
		}
		
		return currPtr.getData();
	}

	public Object remove(int index) {
		// TODO Auto-generated method stub
		int i = 0;
		LinkNode currPtr = this.head;
		while(i < index)
		{
			currPtr = currPtr.getNext();
			i++;
		}
		currPtr.getNext().setPrv(currPtr.getPrv());
		currPtr.getPrv().setNext( currPtr.getNext());
		size--;
		return currPtr.getData();
	}

	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

}
