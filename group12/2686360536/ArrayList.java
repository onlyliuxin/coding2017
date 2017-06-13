package org.dul.CodingTask.homework_2017_2_25;

public class ArrayList implements List {

	private int size = 0;

	private Object[] data = new Object[100];

	public void add(Object object) {
		data[size] = object;
		size++;
	}

	public void add(int index, Object object) {
		
	}

	public Object get(int index) {
		return data[index];
	}

	public Object remove(int index) {
		return null;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return null;
	}

}
