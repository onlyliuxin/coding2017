package com.coding.basic;

public class ArrayList implements List, Iterator {
	
	private int size;
	private Object[] data;
	public ArrayList() {
		data = new Object[10];
	}

	@Override
	public void add(Object o) {
		size = size();
		if(data.length <= size){
			grow();
		}
		data[size] = o;
		size++;
	}
	
	/* (non-Javadoc)
	 * @see dataStructure.List#add(java.lang.Object, int)
	 * 在第index元素前插入元素
	 */
	@Override
	public void add(int index, Object o){
		if (index >= size()){
			return;
		}
		size = size();
		if(data.length <= size){
			grow();
		}
		for(int i = size , len = size - index; i < len; i -- ){
			data[i] = data[i -1];
		}
		data[index] = o;
		size++;
	}

	@Override
	public Object get(int index) {
		return data[index];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object remove(int index) {
		if (index >= size()){
			return null;
		}else{
			Object o = data[index];
			for(int i = index; i < size; i ++){
				data[i] = data[i + 1];
			}
			data[size] = null;
			size--;
			return o;
		}

	}
	
	private void grow(){
		size = size();
		int length = 0;
		if(size < 10000){
			length = size * 2;
		}else{
			length = (int)(size * 1.5);
		}
		size = length;
		
		Object[] temData = new Object[length];
		for(int i = 0, j = data.length; i < j; i ++){
			temData[i] = data[i];
		}
		data = temData;
	}

	private int index = 0;
	@Override
	public boolean hasNext() {
		return index < size;
	}

	@Override
	public Object next() {
		index++;
		return data[index - 1];
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for(int i = 0; i < size; i++){
			sb.append(data[i].toString() + ",");
		}
		sb.append("]");
		return sb.toString();
	}

}
