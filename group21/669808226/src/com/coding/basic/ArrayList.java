package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private final int EXPAND_LENGTH = 20;
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		if(o==null)
			return;
		growSize();
		elementData[size - 1] = o;
	}
	public void add(int index, Object o){
		if(index < 0 || o == null)
			return;
		
		growSize();
		
		if(index >= size - 1)
		{
			elementData[size - 1] = o;
		}
		else
		{
			Object objToMoveAfterward = elementData[index];
			for(int i = index; i < size - 1; ++i)
			{
				elementData[i] = o;
				Object temp = elementData[i+1];
				elementData[i+1]= objToMoveAfterward;
				objToMoveAfterward = temp;
			}
		}
	}
	
	public Object get(int index){
		if(index >=0 && index < elementData.length)
		{
			return elementData[index];
		}
		return null;
	}
	
	public Object remove(int index){
		if(index >=0 && index < size && elementData[index] != null)
		{
			Object rtn = elementData[index];
			elementData[index] = null;
			
			for(int i = index; i < size; ++i)
			{
				elementData[i] = elementData[i + 1];
			}
			
			--size;
			
			return rtn;
		}
		return null;
	}
	
	public int size(){
		return this.size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator(this);
	}
	
	private void growSize()
	{
		++size;
		{
			//expand array length by EXPAND_LENGTH
			elementData = Arrays.copyOf(elementData, elementData.length + EXPAND_LENGTH);
		}
	}
	
	private class ArrayListIterator implements Iterator
	{

		private ArrayList data;
		
		private int index;
		
		public ArrayListIterator(ArrayList list)
		{
			if(list != null)
			{
				this.data = list;
				this.index = -1;
			}
		}
		
		@Override
		public boolean hasNext() {
			if(this.data != null)
			{
				//++index;
				if(this.data.size() > index + 1)
				{
					return true;
				}
			}
			return false;
		}

		@Override
		public Object next() {
			if(this.hasNext())
			{
				return this.data.get(++index);
			}
			return null;
		}
		
	}
	
}
