package com.coding.basic;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[100];

	public void add(Object o) {
		elementData[size] = o;
		size++;
	}

	public void add(int index, Object o) {
		for (int i = size; i > index; i--) {
			elementData[i] = elementData[i - 1];
		}

		elementData[index] = o;

	}

	public Object get(int index) {
		return elementData[index];
	}

	public Object remove(int index) {
		Object oj = elementData[index];
		for (int i = index; i < size; i++) {
			elementData[i] = elementData[i + 1];
		}
		elementData[size--] = null;
		return oj;
	}

	public int size() {

		return size;
	}

	public Iterator iterator() {
		return new MyIterator();
	}

	public class MyIterator implements Iterator {

		int i = 0;

		@Override
		public boolean hasNext() {
			while (elementData[i] != null) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			return elementData[i++];
		}
	}

	
	public static void main(String[] args) {
		ArrayList a = new ArrayList();
		a.add(1);
		a.add(2);
		a.add(3);
		
		MyIterator b = (MyIterator) a.iterator();
		
		while(b.hasNext()){
			System.out.println(b.next());
		}
	}
	

	
}
