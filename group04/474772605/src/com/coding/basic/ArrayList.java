package com.coding.basic;

public class ArrayList implements List {
	
	private int size = 0;
	private int length = 1;
	
	private Object[] elementData = new Object[length];
	
	public void add(Object o){
		System.out.println("进行add方法");
		capacity(size+1);
		System.out.println(elementData.length);
		elementData[size++] = o;
		//size++;	
	}
	public void add(int index, Object o){
		capacity(size+1);
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index] = o ;
	}
	
	public Object get(int index){
		System.out.println("进行get方法");
		return elementData[index];
	}
	
	public Object remove(int index){
		System.out.println("进行remove方法");
		if(index>size){
			return elementData;
		}
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);	
		elementData[--size]=null;
		System.out.println(size);
		return elementData;
	}
	
	public int size(){
		return this.size;
	}
	
	public Iterator iterator(){
		return  new Iterarorimp(this.elementData);
	}
	
	public class Iterarorimp implements Iterator{

		int index;
		Object[] data = null;
		
		public Iterarorimp(Object[] data){
			this.data = data;
		}
		
		
		public boolean hasNext() {
			if(index>=data.length){
				return false;
			}
			return true;
		}


		public Object next() {

			return this.data[index++];
		}
		

	}
	
	
	
	public void capacity(int newlength) {
		System.out.println("进行扩容方法");
		if(newlength>length){
			Object[] newelementData = new Object[length*2];
			System.arraycopy(elementData, 0, newelementData, 0, size);
			this.elementData = newelementData;	
			length = length*2;
		}				
	}
	
}
