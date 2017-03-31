package com.coding.basic;

public class IteratorArrayList implements Iterator  {

	
	private Object[] elementData;
	private int length;
	
	private int index = 0;
	
	public IteratorArrayList(Object[] elementData,int length){
		this.elementData = elementData;
		this.length = length;
	}
	
	@Override
	public boolean hasNext() {
		
		if ((index+1)<=length) {
			return true;
		}
		
		return false;
	}

	@Override
	public Object next() {
		if ((index+1)<=length) {
			index = index + 1;
			return elementData[index-1];
		}else{
			System.out.println("Ö¸Õë³¬¹ý·¶Î§");
			return null;
		}
			
	}


	
}
