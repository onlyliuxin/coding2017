package com.maple.basic;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		//不够了怎么扩容
		elementData[size++]=o;
	}
	public void add(int index, Object o){
		if(index<0||index>size){
			throw new IndexOutOfBoundsException("Joy Index "+index+", Size: "+size);
		}
		for(int i=size;i>index;i--){
			elementData[i-1]=elementData[i];
		}
		elementData[index]=o;
		size++;
	}
	
	public Object get(int index){
		if(index<0||index>=size){
			throw new IndexOutOfBoundsException("Joy Index "+index+", Size: "+size);
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index<0||index>=size){
			throw new IndexOutOfBoundsException("Joy Index "+index+", Size: "+size);
		}
		Object removeObj=elementData[index];
		for(int i=index;i<size-1;i++){
			elementData[i]=elementData[i+1];
		}
		size--;
		return removeObj;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new Iterator() {
			int ref=0;
			@Override
			public Object next() {
				if(hasNext()){
					return elementData[ref++];
				}
				return null;
			}
			
			@Override
			public boolean hasNext() {
				if(ref<0||ref>=size) return false;
				return true;
			}
		};
	}
	
}
