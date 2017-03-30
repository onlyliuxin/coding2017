package com.coding.basic;

public class ArrayList implements List {
	
	private int size = 0;
	
	
	private Object[] elementData = new Object[100];
	
	private int stepLength = 50;
	
	public void add(Object o){
		if(size > elementData.length)
		{
			Object[] elementDataNew = new Object[elementData.length + stepLength];
			System.arraycopy(elementData, 0, elementDataNew, 0, elementData.length);
			elementData = elementDataNew;
		}
		
		elementData[size] = o;
		size++;
		
	}
	public void add(int index, Object o){
		if(size > elementData.length)
		{
			Object[] elementDataNew = new Object[elementData.length + stepLength];
			System.arraycopy(elementData, 0, elementDataNew, 0, elementData.length);
			elementData = elementDataNew;
		}
		System.arraycopy(elementData, index, elementData, index+1, size-index+1);
		elementData[index] = o;
		size++;
		
	}
	
	public Object get(int index){
		return elementData[index];
	}
	
	public Object remove(int index){
		Object obj = elementData[index];
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		size--;
		return obj;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<size;i++)
		{
			if(i!=0)
			{
				sb.append(",");
			}
			sb.append(elementData[i]);
		}
		return sb.toString();
		
	}
}
