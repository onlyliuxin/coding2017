package com.danny.hw1;

import java.lang.reflect.Array;
import java.util.Arrays;

import javax.print.attribute.Size2DSyntax;

public class ArrayList implements List{
	//ArrayList Size
	private int size=0;
	private int array_len=2;

	//total element in here
	private Object[] elementData = new Object[array_len];

	@Override
	public void add(Object o) {
		// TODO Auto-generated method stub
		adjustCapacity();
		this.elementData[size++] = o;
	}

	@Override
	public void add(int index, Object o) {
		// TODO Auto-generated method stub
		//Check range. if index is invalid,then would not add anythings
		rangeCheckForAdd(index);

		adjustCapacity();
		System.arraycopy(elementData, index, elementData, 
						 index + 1,size - index);
		this.elementData[index] = o;
		this.size++;

	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		rangeCheckExist(index);
		
		return elementData[index];

	}

	@Override
	public Object remove(int index) {
		// TODO Auto-generated method stub
		rangeCheckExist(index);
		
		Object oldValue = elementData[index];
		//deal with copy operation
        int moveStep = size - index - 1;
        if ( moveStep > 0 )
            System.arraycopy(elementData, index+1, elementData, index,moveStep);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}
	
	//Get Iterator
	public Iterator iterator() {
		return new ArrayListIterator();
	}

	/*******  Iterator  *******/
	private class ArrayListIterator implements Iterator{

		private int currentIndex = 0;
		
		@Override
		public boolean hasNext() {
			return currentIndex < size();
		}

		@Override
		public Object next() {
			rangeCheckExist(currentIndex);
			
			return elementData[currentIndex++];
		}
	}
	

	/*******Inner function*******/
	//Increase arraylist size
	private void adjustCapacity(){
		//array length can not satisfy data size;
		if ( this.array_len < size() + 1 ) {
			this.array_len *= 2;
			this.elementData = Arrays.copyOf(elementData, array_len);
		} else {
			return ;
		}
	}

	private void rangeCheckForAdd(int index) {
		//Add operation is allow to add value in [ size() ] even if [ size() ] has not data
		if ( index > size() || index < 0 )
			throw new IndexOutOfBoundsException("Invalid Adding Index:"+index);
	}
	private void rangeCheckExist(int index) {
		if ( index >= size() || index < 0 )
			throw new IndexOutOfBoundsException("Invalid Index:"+index);
	}




}
