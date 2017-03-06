package com.coding.basic;

public class ArrayList {

    private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	
	//在ArrayList的尾部添加
	public void add(Object o){
		
		   size = elementData.length + 1;
		   Object[] tempData = new Object[size];	
		   System.arraycopy(elementData, 0, tempData,0, elementData.length);
		   elementData = tempData;
		   
		   elementData[size-1] = o;
		
	}
	
	//在ArrayList中的某一个元素后面添加, 这里的关键在于先移动末尾的元素
	public void add(int index, Object o){
		
		   size = elementData.length + 1;
		   Object[] tempData = new Object[size];	
		   System.arraycopy(elementData, 0, tempData,0, elementData.length);
		   elementData = tempData;
		    
		for (int i=elementData.length-2; i>index; i--) {
			elementData[i+1] = elementData[i];
		}
		
		  elementData[index+1] = o;
		
	}
	
	//按下标来访问ArrayList中的元素
	public Object get(int index){
		return elementData[index];
	}
	
	//按下标来删除ArrayList中的元素
	public Object remove(int index){
		Object r = elementData[index];  
		for (int i=index; i<elementData.length-1; i++) {
			elementData[i] = elementData[i+1];
		}
		
		size = elementData.length - 1;
		
		Object[] tempData = new Object[size];	
		System.arraycopy(elementData, 0, tempData,0, size);
		elementData = tempData;
		return r;
	}
	
	//获取ArrayList的大小
	public int size(){
		return size;
	}
	
	//public Iterator iterator(){
	//	return null;
    //}
	
}
