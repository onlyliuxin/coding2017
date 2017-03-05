package com.coding.basic;

public class ArrayList implements List {

	private  int size=0;

	private  Object[] elementData =new Object[10];


	//数组扩容
	private void  ensureCapacityInternal(){
		if(size==elementData.length){
			Object[] newArray = new Object[size*2];
			System.arraycopy(elementData, 0, newArray, 0, elementData.length);
			elementData=newArray;
		}
	}

	public void add(Object o){
		ensureCapacityInternal();
		elementData[size]=o;
		size++;
	}

	public void add(int index, Object o){
		ensureCapacityInternal();
		if(index<0){
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.arraycopy(elementData, index, elementData, index+1,size-index );
        elementData[index]=o;
        size++;
	}

	public Object get(int index){
		if(index<0||index>=size){
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return elementData[index];
	}

	public Object remove(int index){
		if(index>=size){
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{

			int numMoved=size-index-1;
			if(numMoved>0){
				System.arraycopy(elementData, index+1, elementData, index, numMoved);
			}

			elementData[size--] = null;			
		}
		return index;

	}


	public int size(){
		return size;
	}


	private class Iter implements Iterator {
		//计数器 
		private int coursor=-1;
		//判断是否存在下一个
		public boolean hasNext(){
			return coursor+1<size;
		}
		//获取下一个
		public String next(){
			coursor++;
			return (String) elementData[coursor];
		}
	}
		public Iterator iterator(){

			return new Iter();

		}
}



