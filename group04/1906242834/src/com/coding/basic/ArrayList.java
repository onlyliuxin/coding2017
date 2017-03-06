package com.coding.basic;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		//若插入元素后会溢出，则对数组进行扩容，在这里只把空间加1
		if ((size()+1)>elementData.length) {
			System.arraycopy(elementData, 0, elementData, 0, elementData.length+1);
			elementData[size] = o;
		}else {
		//若插入元素后不出现溢出，则直接添加在末尾
			elementData[size] = o;
		}
	}
	//index在length范围之内正常插入，若index大于length则抛出异常
	public void add(int index, Object o){
		Object temp;
		if (index<elementData.length) {
			System.arraycopy(elementData, 0, elementData, 0, elementData.length+1);
			temp = elementData[elementData.length-1];
			elementData[elementData.length] = temp;
			for (int i = elementData.length; i > index; i--) {
				elementData[i-1] = elementData[i-2];
			}
			elementData[index] = o;
		}else if (index==elementData.length) {
			add(o);
		}else{
			System.out.println("ArrayIndexOutOfBoundsException");
		}
	}
	
	public Object get(int index){
		if (index<=elementData.length-1) {
			return elementData[index];
		}else {
			System.out.println("ArrayIndexOutOfBoundsException");
		}
		return elementData;
	}
	
	public Object remove(int index){
		if (index<elementData.length) {
			for (int i = index; i < elementData.length; i++) {
				elementData[i] = elementData[i+1];
			}
			return elementData;
		}else {
			System.out.println("ArrayIndexOutOfBoundsException");
		}
		return elementData;
	}
	
	public int size(){
		for (Object object : elementData) {
			if (object!=null) {
				size += 1;
			}
		}
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}