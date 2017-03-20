package com.coding.basic;

public class ArrayList implements List{

	private int size = 0;
	private Object[] elements;
	
	public ArrayList() {
		this.elements = new Object[5];
	}
	
	public ArrayList(int size) {
		this.elements = new Object[size];
	}
	public void add(Object o) {
		// TODO Auto-generated method stub
		if(isFull())
		{
			resize();
		}
		this.elements[this.size] = o;
		this.size++;
	}
	public boolean isFull ()
	{
		if(this.size == this.elements.length)
		{
			return true;
		}
		
		return false;
	}
	
	public void resize()
	{
		Object[] newElements = new Object[this.elements.length*2];
		System.arraycopy(elements, 0, newElements, 0, elements.length);
		this.elements = newElements;
		newElements = null;
	}
	public void add(int index, Object o) {
		// TODO Auto-generated method stub
		rangeCheck(index);
		if(isFull())
		{
			resize();
		}
		System.arraycopy(elements, index, elements, index+1,size-index);
		this.elements[index] = o;
		size++;
	}
	void rangeCheck(int index)
	{
		if(index > size || index < 0)
		{
			throw new IndexOutOfBoundsException("下标越界");
		}
	}
	public Object get(int index) {
		// TODO Auto-generated method stub
		rangeCheck(index);
		return elements[index];
	}

	public Object remove(int index) {
		// TODO Auto-generated method stub
		rangeCheck(index);
		Object elem = elements[index];
		System.arraycopy(elements, index+1, elements, index, size-index-1);
		size--;
		elements[size] = null;
		return elem;
	}

	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}
	public com.coding.basic.Iterator Iterator ()
	{
		return new Itr();
	}
	public class Itr implements com.coding.basic.Iterator{
		int cur = 0;
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(size==cur)
			{
				return false;
			}
			return true;
		}

		public Object next() {
			// TODO Auto-generated method stub
			int i = cur;
			if(i < elements.length)
			{
				cur = i+1;	
				return elements[i];
			}
			return null;
		}
		
	}
}
