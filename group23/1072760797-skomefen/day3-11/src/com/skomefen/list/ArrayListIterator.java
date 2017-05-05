package com.skomefen.list;

public class ArrayListIterator implements Iterator {
	private ArrayList array = null;
	private int index = 0;
	public ArrayListIterator(ArrayList array) {
		this.array = array;
	}
	public boolean hasNext() {
		if(array==null){
			return false;
		}
		if(index<array.size()){
			return true;
		}
		return false;
	}

	public Object next() {
	
		return array.get(index++);
		
	}

}
