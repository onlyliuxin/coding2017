package com.coding.basic;

public class ArrayList implements List{
	private int size = 0;
	
	private Object[] elementDate = new Object[100];
	
	
	@Override
	public void add(Object o) {
		
		this.elementDate[this.size]=o;
		this.size++;
	}

	@Override
	public void add(int index, Object o) {
		
	}

	@Override
	public Object get(int dex) {
		 
		return null;
	}

	@Override
	public Object remove(int index) {
		return null;
	}

	@Override
	public int size() {
		return -1;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}
