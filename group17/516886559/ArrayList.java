package com.rd.p2p.common.util.liuxin;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {
	
	private int size = 0;
	
	private Object[] elementData = new Object[10];
	
	public void add(E o){
		if(size >= elementData.length){
			elementData = Arrays.copyOf(elementData, elementData.length * 2);
		}
		elementData[size++] = o;
		size++;
	}
	
	public void add(int index, E o){
		if(index > size){
			throw new ArrayIndexOutOfBoundsException("插入索引数不能大于数列总长度   " + index + ">" + size);
		}
		if(size >= elementData.length){
			elementData = Arrays.copyOf(elementData, elementData.length * 2);
		}
		Object[]  tempData = Arrays.copyOfRange(elementData, index, size);
		elementData[index] = o;
		for(int i = 1; i <= tempData.length; i++){
			elementData[i+index] = tempData[i-1];
		}
		size++;
		
	}
	
	public Object get(int index){
		if(index >= size){
			throw new ArrayIndexOutOfBoundsException("取出数组索引不能大于等于数组总长度   " + index + ">=" + size);
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index >= size){
			throw new ArrayIndexOutOfBoundsException("移除索引数不能大于等于数列总长度   " + index + ">=" + size);
		}
		Object data = get(index);
		Object[]  tempData = Arrays.copyOfRange(elementData, index+1, size);
		for(int i = 0; i < tempData.length; i++){
			elementData[index+i] =  tempData[i];
		}
		elementData[size - 1] = null;
		size--;
		return data;
	}
	
	public int size(){
		return size;
	}
	
	@Override
	public String toString() {
		for (Object object : elementData) {
			System.out.println(object);
		}
		return null;
	}
	
	//迭代器
	public Iterator iterator(){
		return new Iterator() {
			
			private int index = 0;
			
			@Override
			public Object next() {
				if(index >= size){
					throw new ArrayIndexOutOfBoundsException("取出数组索引不能大于等于数组总长度   " + index + ">=" + size);
				}
				return get(index++);
			}
			
			@Override
			public boolean hasNext() {
				if(size > index){
					return true;
				}else{
					return false;
				}
			}
		};
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3,3);
		Iterator in = list.iterator();
		while(in.hasNext()){
			System.out.println(in.next());
		}
	}
}