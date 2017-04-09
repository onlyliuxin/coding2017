package com.company.code;

import java.util.Arrays;

public class ArrayList<I extends Number> implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];

	public void add(Object o){
		if(size+1>elementData.length){
			elementData= Arrays.copyOf(elementData,elementData.length/3*2);
		}
		elementData[size++]=o;
	}
	public void add(int index, Object o){
		if(index<0||index>size){
			throw new IndexOutOfBoundsException();
		}
		if(size+1>elementData.length){
			elementData=Arrays.copyOf(elementData,elementData.length/3*2);
		}
		System.arraycopy(elementData,index,elementData,index+1,size-index);
		elementData[index]=o;
		size++;
	}


	public Object get(int index){
		if(index<0||index>=size){
			throw new IndexOutOfBoundsException();
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		Object old=elementData[index];
		System.arraycopy(elementData,index+1,elementData,index,size-1-index);
		size--;
		return old;
	}
	
	public int size(){
		for(int i=0;i<elementData.length;i++){
			if(null==elementData[i]){
				size=i;
				break;
			}
		}
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}

	public class ArrayListIterator implements Iterator{


		private int currentIndex=0;
		@Override
		public boolean hasNext() {

			return currentIndex<size();
		}

		@Override
		public Object next() {
			Object o=get(currentIndex);
			currentIndex++;
			return o;
		}
	}
}
