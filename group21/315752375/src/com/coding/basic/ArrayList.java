package com.coding.basic;

public class ArrayList implements List {
	private int size = 0;// 数组之中存储的实际元素个数
	private int last = -1;// 指向数组最后一个元素
	private int length;// 数组的的长度
	private Object[] elementData = null;

	public ArrayList() {
		this.elementData = new Object[16];
		this.length = 16;
	//	System.out.println("success");
	}

	public ArrayList(int length) {
		if(length<0)
		throw new IllegalArgumentException("Illegal Capacity: " + length);
		this.elementData = new Object[length];
		this.length = length;
		//System.out.println("success");
	}

	public void add(Object obejct) {
		if (size>=length) {
			elementData = grow(elementData, length);
		}
		last++;
		elementData[last] = obejct;
		size++;
	}

	public void add(int index, Object object) {
		rangeCheckAdd(index);
		if (last> length-1) {
			elementData = grow(elementData, length);
		}
		System.arraycopy(elementData, index, elementData, index + 1, last
				- index + 1);
		elementData[index] = object;
		last++;
		size++;
	}

	public Object get(int index) {
		rangeCheck(index);
		return elementData[index];
	}

	public Object remove(int index) {
		rangeCheck(index);
		Object object = elementData[index];
		System.arraycopy(elementData, index + 1, elementData, index, last - index);
		last--;
		size--;
		return object;

	}

	public int size() {
		return size;
	}

	public Object[] grow(Object[] src, int length) {
		Object[] target = new Object[src.length + length];
		System.arraycopy(src, 0, target, 0, src.length);
		return target;
	}

	private void rangeCheck(int index) {
		if (index > last)
			throw new IndexOutOfBoundsException();
	}
	private void rangeCheckAdd(int index) {
		if(index > last+1)
			throw new IndexOutOfBoundsException();
	}
	public Iterator iterator() {

		return new ArrayIterator(); 
	}
	private  class ArrayIterator implements Iterator{
		int next=-1;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return next!=last;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			next++;
			return get(next);
		}
		
	}

}
