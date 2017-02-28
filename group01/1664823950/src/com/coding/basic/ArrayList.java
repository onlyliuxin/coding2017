package com.coding.basic;

public class ArrayList implements List 
{
	
	private int size = 0;
	
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o)
	{
		elementData[size] = o;
		size ++;
	}
	
	public void add(int index, Object o)
	{
		if(index > size || index < 0)
		{
			return;
		}
		else
		{
			for (int i = size-1; i > index; i--)
			{
				elementData[i+1] = elementData[size];
			}
			elementData[index] = o;
			size++;
		}
		
	}
	
	public Object get(int index)
	{
		if(index < size || index >= 0)
		{
			return elementData[index];
		}
		return null;
	}
	
	public Object remove(int index)
	{
		Object removedObj;
		if(index >= size || index < 0)
		{
			removedObj = null;
		}
		else
		{
			removedObj = elementData[index];
			for (int j = index; j < elementData.length; j++)
			{
				elementData[j] = elementData[j+1];
			}
			size--;
		}
		return removedObj;
	}
	
	public int size()
	{
		return size;
	}
	
	public Iterator iterator()
	{
		return null;
	}
	
}
