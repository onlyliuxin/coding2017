package com.hmily.learning;

public class MyArrayList implements List,Iterator{
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	/**
	 * 添加元素
	 */
	public void add(Object o){
		 if(size==elementData.length){
			Object[] newElementData = new Object[elementData.length+1];
			for(int i=0;i<elementData.length;i++){
				newElementData[i] = elementData[i];
			       }
				newElementData[elementData.length+1]=o;
				elementData = newElementData;
				size++;
			}else{
				elementData[size+1]=o;
				size++;
			}
	}
	/**
	 * 在指定位置添加元素
	 */
	public void add(int index, Object o){
		if(size()==elementData.length){
			Object[] newElementData = new Object[size()*2+1];
			for(int i=0;i<elementData.length;i++){
				newElementData[i] = elementData[i];
			       }
				elementData = newElementData;
		}
		for(int i=size();i>index;i--){
			elementData[i]=elementData[i-1];
		}
		elementData[index]=o;
		size++;
	}
	/**
	 * 获取元素
	 */
	public Object get(int index){
		if(index>=size()||index<0){
			throw new ArrayIndexOutOfBoundsException();
		}
		return elementData[index];
	}
	/**
	 * 移除元素
	 */
	public Object remove(int index){
		if(index<0||index>size()){
			throw new ArrayIndexOutOfBoundsException();
		}
		Object o=elementData[index];
		for(int i=index;i<size();i++){
			elementData[i]=elementData[i+1];
		}
		return o;
	}
	
	public int size(){
		return this.size;
	}
	/**
	 * 数组下标
	 */
	private int bounds=0;
	
	public Iterator iterator(){
		return this.iterator();
	}
	/**
	 * 
	 */
	@Override
	public boolean hasNext() {
		return bounds<size();
	}
	@Override
	public Object next() {
		if(!hasNext()){
			throw new ArrayIndexOutOfBoundsException();
		}
		return elementData[bounds++];
	}
}
