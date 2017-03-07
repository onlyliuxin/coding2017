package com.github.fei9009.coding2017.basic;

public class ArrayList implements List {
	
	private int size = 0;
	private int capacity;
	
	private Object[] elementData = new Object[100];
	
	public ArrayList() {
		this.capacity = 20;
	}
	
	private void extend() {
		Object[] updatedElementData = new Object[this.elementData.length + this.capacity];
		System.arraycopy(this.elementData, 0, updatedElementData, 0, this.elementData.length);
		this.elementData = updatedElementData;
	}
	
	public void add(Object o){
		if (this.size == elementData.length) {
			extend();
		}
		elementData[size] = o;
		this.size++;
	}
	
	public void add(int index, Object o){
		if (this.size == elementData.length) {
			extend();
		}
		int i;
		for (i = this.size - 1; i >= index; i--) {
			this.elementData[i + 1] = this.elementData[i];
		}
		this.elementData[i + 1] = o;
		this.size++;
	}
	
	public Object get(int index){
		if (index >= 0 && index < this.size) {
			return this.elementData[index];
		}else {
			return null;
		}
	}
	
	public Object remove(int index){
		if (index >= 0 && index < this.size) {
			int i = 0;
			Object deletedElement = this.elementData[index];
			for (i = index + 1; i < this.size; i++) {
				this.elementData[i - 1] = this.elementData[i]; 
			}
			this.elementData[i] = null;
			this.size--;
			return deletedElement;
		}else {
			return null;
		}
	}
	
	public int size(){
		return this.size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}
