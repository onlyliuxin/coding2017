package com.coding.week1;




public class ArrayList  implements List{
	private int size = 0;
	
	private Object[] elementData = {};
	
	public void add(Object o){
		extendIndex();
		elementData[size] = o;
		size++;
	}
	public void add(int index, Object o){
		
		if(index>size){
			System.out.println("Exception in thread \""+Thread.currentThread()+"\" java.lang.IndexOutOfBoundsException:Index:"+index+",Size:"+size);
			return;
		}
		int length = elementData.length;
		if(size==elementData.length){
			length=elementData.length+1;
		}
		Object[] newElement = new Object[length];
		System.arraycopy(elementData, 0, newElement, 0, index);
		System.arraycopy(elementData, index, newElement,index+1,size-index );
		newElement[index]=o;
		elementData = newElement;
		size++;
	}
	
	public Object get(int index){
		boolean isRange=rangeCheck(index);
		if(!isRange){
			return null;
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		boolean isRange=rangeCheck(index);
		if(!isRange){
			return null;
		}
		Object rmData = elementData[index];
		Object[] newElement = new Object[elementData.length];
		System.arraycopy(elementData, 0, newElement, 0, index);;
		System.arraycopy(elementData, index+1, newElement,index,size-index-1 );
		elementData = newElement;
		size--;
		return rmData;
	}
	
	public int size(){
		return size;
	}
	
	public com.coding.week1.Iterator iterator(){
		return new Ito();
	}
	public boolean rangeCheck(int index){
		
		if(index>size-1||index<0){
			System.err.println("Exception in thread \""+Thread.currentThread()+"\" java.lang.IndexOutOfBoundsException:Index:"+index+",Size:"+size);
			return false;
		}
		return true;
	}
	public void extendIndex(){
		Object[] newElement = new Object[elementData.length+1];
		System.arraycopy(elementData, 0, newElement, 0, size);
		elementData = newElement;
		
	}
	public class Ito implements com.coding.week1.Iterator{
		int cursor;
		@Override
		public boolean hasNext() {
			if(cursor!=size){
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			Object o=elementData[cursor];
			cursor++;
			return o;
		}
	}

	
}
