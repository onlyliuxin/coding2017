package com.coding.basic;

public class ArrayIterator implements Iterator{

	private int index = 0;
	private List list ;
	
	public ArrayIterator(List list){
		this.list = list;
	}
	
	@Override
	public boolean hasNext() {
		
		if(index<list.size()){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Object next() {
		return list.get(index++);
	}
	
}
