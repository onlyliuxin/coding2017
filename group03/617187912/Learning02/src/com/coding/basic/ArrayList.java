package com.coding.basic;


import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData;

	public ArrayList(){
		this(64);
	}

	public ArrayList(int intSize) {
		elementData = new Object[intSize];
	}

	public void add(Object o) {
		checkMaxSize();
		elementData[size] = o;
		size++;
	}

	public void add(int index, Object o) {
		checkMaxSize();
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}

	private void checkIndexRange(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("index超出数组界限�?");
		}
	}

	private void checkMaxSize() {
		if (size >= elementData.length) {
			elementData = Arrays.copyOf(elementData, elementData.length * 2);
		}
	}

	public Object get(int index) {
		checkIndexRange(index);
		return elementData[index];
	}

	public Object remove(int index) {
		Object o = get(index);
		if (index == size - 1) {
			elementData[index] = null;
		} else {
			System.arraycopy(elementData, index + 1, elementData, index, size - index);
		}
		size--;
		return o;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new MyIterator();
	}

	private class MyIterator implements Iterator {

		private int current = 0;

		@Override
		public boolean hasNext() {
			return current != size;
		}

		@Override
		public Object next() {
			if (current >= size) {
				throw new NoSuchElementException();
			}
			return elementData[current++];
		}
	}

	public static void main(String[] args) {
		ArrayList arrayList = new ArrayList(5);
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		arrayList.add(4);
		arrayList.add(5);
		arrayList.add(6);
		System.out.println(arrayList.get(1));
		arrayList.add(1, 100);
		System.out.println(arrayList.get(1));
		System.out.println(arrayList.size);	
		System.out.println(arrayList.remove(2));
		System.out.println(arrayList.get(2));

		
		ArrayList mixArraylist = new ArrayList(5);
		mixArraylist.add("String");
		mixArraylist.add(100);
		mixArraylist.add('f');
		mixArraylist.add(3.1f);
		mixArraylist.add(4L);
		System.out.println(mixArraylist.get(1));
		mixArraylist.add(1, 101);
		System.out.println(mixArraylist.get(1));
		System.out.println(mixArraylist.size);	
		System.out.println(mixArraylist.remove(2));
		System.out.println(mixArraylist.get(2));
	}
}