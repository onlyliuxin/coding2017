package com.coding.basic;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		//找出最后一个空的位置赋值
		for(int i=0;i<elementData.length;i++){
			if(elementData[i]==null){
				elementData[i]=o;
				break;
			}
			if(i==elementData.length){
				this.expansion();
				elementData[i]=o;
				break;
			}
		}
		size++;
		 
	}
	
	private void expansion(){
		Object[] newElementData = new Object[this.elementData.length+10];
		System.arraycopy(elementData, 0, newElementData, 0, elementData.length-1);
		this.elementData=newElementData;
	}
	
	public void add(int index, Object o){
		if(elementData.length<=size){
			expansion();
		}
		System.arraycopy(elementData,index,elementData,index+1,size);
		elementData[index]=o;
		size++;
	}
	
	public Object get(int index){
		if(index>=size){
			return this.elementData[elementData.length];
		}else{
			return this.elementData[index];
		}
	}
	
	public Object remove(int index){
		Object object = get(index);
		System.arraycopy(elementData,index+1,elementData,index,size-1);
		elementData[size-1]=null;
		size--;
		return object;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		
		return new Iterator() {
			
			private int nextNum=0;
			
			@Override
			public Object next() {
				return get(nextNum++);
			}
			
			@Override
			public boolean hasNext() {
				return nextNum>=size?false:true;
			}
		};
	}
	
}
