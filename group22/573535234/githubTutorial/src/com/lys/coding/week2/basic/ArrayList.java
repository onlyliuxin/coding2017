package com.lys.coding.week2.basic;

import java.util.Arrays;

public class ArrayList implements List{
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	@Override
	public void add(Object o) {
		//先检查数组容量是否充足,不足则要扩增
		if(size>=elementData.length){
			int newLength = elementData.length*3/2+1;
			Arrays.copyOf(elementData, newLength);
		}
		//往数组里面增添数据
		elementData[size+1]=o;
		//数组容量size加一
		size++;
	}

	@Override
	public void add(int index, Object o) {
		//先检查数组容量是否充足,不足则要扩增
		if(size>=elementData.length){
			int newLength = elementData.length*3/2+1;
			Arrays.copyOf(elementData, newLength);
		}
		if(index!=size-1){
			//把当前数组index后面的数据往后挪
			System.arraycopy(elementData, index, elementData, index+1, size-index);
		}
		//往index位置放
		elementData[index]=o;
		//数组容量size加一
		size++;
	}

	@Override
	public Object get(int index) {
		//检查index是否非法
		if(index>=size){
			throw new IndexOutOfBoundsException("Index: "+index+",Size:"+size);
		}else if(index<0){
			throw new IllegalArgumentException("Index: "+index+",<0!");
		}
		return elementData[index];
	}

	@Override
	public Object remove(int index) {
		Object o = elementData[index];
		//检查index是否非法
		if(index>=size){
			throw new IndexOutOfBoundsException("Index: "+index+",Size:"+size);
		}else if(index<0){
			throw new IllegalArgumentException("Index: "+index+",<0!");
		}
		if(size!=index+1){
			//把当前index后面的数据往前挪
			System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		}
		elementData[size]=null;
		//数组容量size减一
		size--;
		return o;
	}

	@Override
	public int size() {
		return size;
	}
	
}
