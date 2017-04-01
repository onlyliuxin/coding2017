package com.coding.basic;

import java.util.Arrays;


public class ArrayList implements List{
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];

	private int length;
	private static final int MAX_SIZE = 50;

	public ArrayList(){
		this(MAX_SIZE);
	}
	public ArrayList(int maxSize){
		length = 0;
		elementData = new Object[maxSize];
	}
	
	public void add(Object o){
		if(! isFull()){
			elementData[size] =o;
			size++;
		}
		else 
			elementData = Arrays.copyOf(elementData, elementData.length*2);
			
		
	}
	public void add(int index, Object o){
		
		makeRoom(index);
		elementData[size-1] =o;
		size++;
		
		
		
	}
	
	public Object get(int index){
		if(index>0 && index<size)
		{
			return elementData[index];
		}
		else
			 throw new IndexOutOfBoundsException("����Խ��");
	}
	
	public Object remove(int index){
		if(index<0||index>size){
			System.out.println("����Խ��");
			return null;
		}
		Object o = elementData[size];
		System.arraycopy(elementData,index+1,elementData,index,size-index-1);
		elementData[size--] = null;
		return o;
		
		
	}
	
	public int size(){
		return elementData.length;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	public boolean isFull(){
		return size == elementData.length;
	}
	
	
	private void makeRoom(int index){
		for(int i = size;i>=index;i--){
			elementData[i] =elementData[i-1];
			
		}
	}
		
	public Iterator getiterator(){
			return  new ArrayListIterator(this);
			}
		
		private class ArrayListIterator implements Iterator{
			ArrayList l = null;
			private int nextIndex;
			ArrayListIterator(ArrayList l){
				nextIndex = 0;
				this.l = l;
				
			}
			@Override
			public boolean hasNext() {
				
				return nextIndex<size;
			}
			@Override
			public Object next() {
				
					
				return elementData[nextIndex++];
	
			}
			@Override
			public void remove() {
				ArrayList.this.remove(nextIndex);
				nextIndex--;
				
			}
		
	}
		
			
	
			
	}
	

