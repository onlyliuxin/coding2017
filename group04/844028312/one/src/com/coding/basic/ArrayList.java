package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	private static final int DEFAULT_CAPACITY = 10;
	private Object[] elementData = new Object[100];
	/**
	 * 添加元素
	 */
	public void add(Object o){
		
		if(size<elementData.length){//没有超过数组容量
			elementData[size]=o;
		}
		
		else{						//超过数组容量扩容
			Object[] newData = new Object[elementData.length+DEFAULT_CAPACITY];	//新建一个大的数组
			System.arraycopy(elementData, 0, newData, 0, elementData.length);	//把原来的数组的数据复制到大的数组里
			newData[size]=o;		//添加o元素
			elementData=newData;	//elementData赋予扩容了的数组
		}
		size++;						//记录数据的大小
	}
	
	public void add(int index, Object o){
		if(index>=0 && index<=size){			//判断 index是否在size范围内  1 2 3 4
			if(size+1<elementData.length){	//判断数组是否越界
				System.arraycopy(elementData, index, elementData, index+1, elementData.length-index-1);	//将index及后面的数据后移一位
				elementData[index]=o;		//index下赋予o
			}
			else{
				Object[] newData = new Object[elementData.length+DEFAULT_CAPACITY];						//新建一个大的数组
				System.arraycopy(elementData, 0, newData, 0, elementData.length);						//把原数组复制到新的数组里
				System.arraycopy(newData, index, newData, index+1, newData.length-index-1);				//将新的数组的index及后面的数据后移一位
				newData[index]=o;																		//将新的数组的index赋予o
				elementData=newData;																	//将新数组赋予给elementData
			}
			size++;					//大小加一
		}
		else{
			System.out.println("index不在size范围内");
		}
		
	}
	
	public Object get(int index){
		if(index<size){					//判断是否在size范围内
			return elementData[index];  //获取index下标数据
		}
		else{
			return null;
		}
	}
	
	public Object remove(int index){
		Object o=null;
		if(index<size){					//判断是否在size范围内
			o=elementData[index];		//记录移除的数据
			elementData[index]=null;	//置空
			System.arraycopy(elementData, index+1, elementData, index, elementData.length-index-1);// 将index+1后面的数据移前一位 
			size--;						//大小减一
		}
		return o;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new Ir();
	}
	private class Ir implements Iterator{
		private int cursor;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cursor != size;
		}
		@Override
		public Object next() {
			// TODO Auto-generated method stub
			int i=cursor;
			if(cursor<size){
				cursor=i+1;
				return elementData[i];
			}
			return null;
		}
		
	}
	
	
}
