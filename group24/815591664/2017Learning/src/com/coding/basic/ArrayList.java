package com.coding.basic;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author hugaoqing
 * created on 2017-3-8
 */
public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[3];
	
	/* 
	 * 添加元素
	 * 
	 */
	public void add(Object o){
		/*if(elementData.length == size){
			Object[] buffer = new Object[size+15];
			System.arraycopy(elementData, 0, buffer, 0, size);
			elementData = buffer;
			elementData[size] = o;
			size++;
		}else {
			
			elementData[size] = o;
			size++;
		}*/
		add(size, o);
		
	}
	
	
	/* 
	 * 
	 * 指定位置添加元素
	 */
	public void add(int index, Object o){
		if(index <0 || index > size){
			throw new IndexOutOfBoundsException();			
		}
		if(size+1<elementData.length){
			Object [] newArr = new Object[size-index+1];
			newArr[0] = o;
			System.arraycopy(elementData, index, newArr, 1, size-index);
			System.arraycopy(newArr, 0, elementData, index, size-index+1);
			size++;
		
		}else{
			Object[] buffer = new Object[(size*3)/2+1];
			System.arraycopy(elementData, 0, buffer, 0, size);
			elementData = buffer;
			
			Object [] newArr = new Object[size-index+1];
			newArr[0] = o;
			System.arraycopy(elementData, index, newArr, 1, size-index);
			System.arraycopy(newArr, 0, elementData, index, size-index+1);
			size++;
		}

	}
	
	public Object get(int index){
		if(index<0||index>=size){
			throw new IndexOutOfBoundsException();
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index<0||index>=size){
			throw new IndexOutOfBoundsException();
		}
		Object out = elementData[index];
		Object[] temp = new Object[size-index-1];
		System.arraycopy(elementData, index+1, temp, 0, size-index-1);
		System.arraycopy(temp, 0, elementData,index, size-index-1);
		//将移除的元素的指针去掉，避免内存泄漏
		elementData[size-1] = null;
		size--;
		return out;
	}
	
	public int size(){
		return this.size;
	}
	
	@Override
	public String toString() {
		Object[]  objs = new Object[size];
		System.arraycopy(elementData, 0,objs , 0, size);
		return Arrays.toString(objs);
		
	}
	public Iterator iterator(){
		return new Iterator() {
			int cursor = 0;
			public Object next() throws Exception {
				cursor++;
				return get(cursor-1);
			}
			
			public boolean hasNext() {
				
				return this.cursor<size();
			}
		};
	}
	
	
	
	public static void main(String[] args) throws Exception {
		List list = new ArrayList();
		list.add(1);
		list.add(2);
	
		list.add(3);
		list.add(3,4);
		System.out.println(list);
		list.add(0,0);
		System.out.println(list);
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}

	}
	
	
	
}
