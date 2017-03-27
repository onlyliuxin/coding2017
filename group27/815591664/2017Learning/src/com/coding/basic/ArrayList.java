package com.coding.basic;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author hugaoqing
 * created on 2017-3-8
 */
public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	
	
	/* 
	 * 当size等于容器的length时，用buffer数组替代原容器
	 * 
	 */
	public void add(Object o){
		if(elementData.length == size){
			Object[] buffer = new Object[size+15];
			System.arraycopy(elementData, 0, buffer, 0, size);
			elementData = buffer;
			elementData[size] = o;
			size++;
		}else {
			
			elementData[size] = o;
			size++;
		}
		
	}
	
	
	/* 
	 * 指定位置添加元素
	 * 当size+1等于 length要先扩大容量再进行添加操作
	 */
	public void add(int index, Object o) throws Exception{
		if(index <0){
			throw new Exception("索引不能为0！");
			
		}else if(index >= size){
			throw new Exception("索引超限！");
		}else if(size+1<elementData.length){
			Object [] newArr = new Object[size-index+1];
			newArr[0] = o;
			System.arraycopy(elementData, index, newArr, 1, size-index);
			System.arraycopy(newArr, 0, elementData, index, size-index+1);
			size++;
		
		}else{
			
			Object[] buffer = new Object[size+15];
			System.arraycopy(elementData, 0, buffer, 0, size);
			elementData = buffer;
			
			Object [] newArr = new Object[size-index+1];
			newArr[0] = o;
			System.arraycopy(elementData, index, newArr, 1, size-index);
			System.arraycopy(newArr, 0, elementData, index, size-index+1);
			size++;
		}
		
		
		
		
	}
	
	public Object get(int index) throws Exception{
		if(index>=size){
			throw new Exception("索引超限！");
		}
		return elementData[index];
	}
	
	public Object remove(int index) throws Exception{
		if(index>=size){
			throw new Exception("索引超限！");
		}
		Object out = elementData[index];
		Object[] temp = new Object[size-index-1];
		System.arraycopy(elementData, index+1, temp, 0, size-index-1);
		System.arraycopy(temp, 0, elementData,index, size-index-1);
		
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
		list.add(6);
		list.add(0, 5);
		list.add(4,7);
		list.add(2,9);
		System.out.println(list);
		System.out.println(list.size());
		System.out.println(list.get(3));
		System.out.println(list.remove(0));
		System.out.println(list);
		
		LinkedList<Integer> ll =  new LinkedList<Integer>();
		ll.add(null);
		
		
		
	}
	
	
	
}
