package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;

	//每次增加的长度
	private Integer addArrayLength = 10;
	//初始  数组长度
	private Object[] elementData = new Object[10];
	
	public void add(Object o){
		if(size < elementData.length)
		{
			elementData[size]=o;
		}else{
			//扩容数组
			 grow();
			elementData[size] = 0;
		}
		size++;
		
	}
	public void add(int index, Object o){
		if(index>size)
		{
			throw new RuntimeException("ArrayIndexOutOfBoundsException");
		}

		//截取索引开始到原数组结尾  组成一个新的数组
		Object [] tempObjs =  Arrays.copyOfRange(elementData,index,elementData.length);
		//覆盖原有索引位置的对象
		elementData[index] = o;
		//数组扩容
		elementData = Arrays.copyOf(elementData,elementData.length+1);

		//将临时生成的数组合并回原数组
		System.arraycopy(tempObjs,0,elementData,index+1,tempObjs.length);
		size++;
	}

	
	public Object get(int index){
		return elementData[index];
	}
	
	public Object remove(int index){

		if(index>size)
		{
			throw new RuntimeException("ArrayIndexOutOfBoundsException");
		}

		Object o = elementData[index];

		//截取索引开始到原数组结尾  组成一个新的数组
		Object [] tempObjs =  Arrays.copyOfRange(elementData,index+1,elementData.length);
		elementData = Arrays.copyOf(elementData,elementData.length-1);
		//将临时生成的数组合并回原数组
		System.arraycopy(tempObjs,0,elementData,index,tempObjs.length);
		size--;

		return o;
	}
	
	public int size(){
		return this.size;
	}
	
	public Iterator iterator(){
		ArratListIterator arratListIterator = new ArratListIterator(this);

		return arratListIterator;
	}

	private void grow(){
		elementData = Arrays.copyOf(elementData,elementData.length+addArrayLength);
	}

	class ArratListIterator implements Iterator{

		ArrayList arrayList = new ArrayList();

		int index = 0;

		ArratListIterator(ArrayList arrayList){

			this.arrayList = arrayList;
			index = arrayList.size;
		}

		@Override
		public boolean hasNext() {
			if(index == 0)
			{
				return false;
			}
			return true;
		}

		@Override
		public Object next() {
			return this.arrayList.get(--index);
		}
	}
	
}
