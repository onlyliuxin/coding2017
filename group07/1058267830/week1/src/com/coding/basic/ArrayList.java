package com.coding.basic;

public class ArrayList implements List, Iterator {
	
	private Object[] obj ;
	private int length; // 数组总长度
	private int size;  // 元素个数
	private int currentIndex; // 当前索引位置，默认为-1

	public int getLength() {
		return length;
	}

	public ArrayList(){
		this.obj = new Object[10];
		this.length = 10;
		this.size = 0;
		this.currentIndex = -1;
	}
	
	public ArrayList(int initSize){
		this.obj = new Object[initSize];
		this.length = initSize;
		this.size = 0;
		this.currentIndex = -1;
	}
	
	@Override
	public void add(Object o) {
		if(this.size < length){
			obj[size] = o;
			this.size++;
			
		}else{
			// 扩容,add数据
			Object[] obj1 = new Object[length * 2];
			System.arraycopy(obj, 0, obj1, 0, length);
			this.length = this.length * 2;
			this.obj = obj1;
			obj[this.size] = o;
			this.size++;
		}
	}

	@Override
	public void add(int index, Object o) {
		if(index < length){
			// 容量扩1，add数据
			Object[] obj1 = new Object[length + 1];
			System.arraycopy(obj, 0, obj1, 0, index);
			System.arraycopy(obj, index, obj1, index+1, length-index);
			obj1[index] = o;
			this.obj = obj1;
			this.length++;
			this.size++;
		}else{
			// 容量扩到index+1, add数据
			Object[] obj1 = new Object[index + 1];
			System.arraycopy(obj, 0, obj1, 0, length);
			obj1[index] = o;
			this.obj = obj1;
			this.length = index + 1;
			this.size++;
		}
	}

	@Override
	public Object get(int index) {
		if(index >= length)
			throw new RuntimeException("数组越界了...");
		return this.obj[index];
	}

	@Override
	public Object remove(int index) {
		if(index >= length)
			throw new RuntimeException("数组越界了...");
		Object tmp = obj[index];// 取值，最后返回
		Object[] obj1 = new Object[length -1];
		System.arraycopy(obj, 0, obj1, 0, index);
		System.arraycopy(obj, index+1, obj1, index, length-index-1);
		this.obj = obj1;
		this.length--;
		this.size--;
		return tmp;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean hasNext() {
		if(currentIndex == length-1){
			return false;
		}else{
			currentIndex++;
			return true;
		}
	}

	@Override
	public Object next() {
		return this.get(currentIndex);
	}

}
