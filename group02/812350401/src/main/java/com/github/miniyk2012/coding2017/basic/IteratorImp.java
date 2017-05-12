package com.github.miniyk2012.coding2017.basic;

public class IteratorImp implements Iterator {

	private List aList;
	private int cursor = 0;
	
	@Override
	public boolean hasNext() {
		return cursor != aList.size();
	}

	@Override
	public Object next() {
		int i = cursor;
		Object next = aList.get(i);
		cursor = i+1;
		return next;
	}
	
	public IteratorImp(List aList) {
		this.aList = aList;
	}
}
