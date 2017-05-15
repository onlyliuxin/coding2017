package com.coding.basic;

import java.util.Iterator;


public class ArrayList implements List , Iterable{
	
	private int size = 0;//element size, not capbility 也代表着最后一个有效数的下一个位置
	
	private Object[] elementData = new Object[100];
	
	private void resize(int capbility){
		Object[] tmp = new Object[capbility];
		for(int i=0; i<size; i++){
			tmp[i] = elementData[i];
		}
		elementData = tmp;
	}
	
	public ArrayList(){
		
	}
	
	public void add(Object o){
		//full
		if(size == elementData.length){
			resize(2*elementData.length);
		}
		elementData[size++] = o;
	}
	
	public void add(int index, Object o){
		//full
		if(size == elementData.length){
			resize(2*elementData.length);
		}
		if(index >= size){
			System.out.println("index:" + index + " >= size:" + size);
			elementData[index] = o;
		}else{
			System.out.println("index:" + index + " < size:" + size);
			for(int i=size-1; i>=index; i--){
				elementData[i+1] = elementData[i];
			}
			elementData[index] = o;
		}
		size = index+1;
	}
	
	public Object get(int index){
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index >= size){
			System.out.println("remove wrong:index >= size");
		}
		Object tmp = elementData[index];
		for(int i=index+1;i<size;i++){
			elementData[i-1] = elementData[i];
		}
		size--;
		return tmp;
	}
	
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size == 0;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator
	{
		private int i = -1;
		@Override
		public boolean hasNext() {
			return i < size-1;
		}

		@Override
		public Object next() {
			return elementData[++i];
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList arrList = new ArrayList();
		System.out.println( arrList.size() );
		
		System.out.println("==============");
		arrList.add("123");
		System.out.println(arrList.get(0));
		
		System.out.println("==============");
		arrList.add(1, "222");
		System.out.println(arrList.get(1));
		System.out.println(arrList.get(2));
		
		System.out.println("==============");
		arrList.add(2, "333");
		System.out.println(arrList.get(2));
		System.out.println(arrList.get(3));
		
		System.out.println("==============");
		arrList.remove(3);
        //System.out.println();
		
		System.out.println("==============");
		for(Object o:arrList)
			System.out.println(o);
	}
	
}