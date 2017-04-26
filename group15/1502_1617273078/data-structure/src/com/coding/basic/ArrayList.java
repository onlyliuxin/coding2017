package com.coding.basic;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[5];

	public void add(Object o) {
		add(size(),o);
		//size++;
	}

	public void add(int index, Object o) {
		if (size+1 > elementData.length) {
			Object[] newelementData = new Object[elementData.length * 2];
			System.arraycopy(elementData,0,newelementData,0,elementData.length);
			System.arraycopy(newelementData,index,newelementData,index+1,newelementData.length-index-1);
			newelementData[index] = o;
			elementData = newelementData;
			newelementData = null;
			size = size + 1;
//			Arrays.copyOf()
		} else {
			System.arraycopy(elementData,index,elementData,index+1,elementData.length-index-1);
			elementData[index] = o;
			size=size+1;
		}

}

	public Object get(int index) {

		return elementData[index];
	}

	public Object remove(int index) {
		System.arraycopy(elementData,index+1,elementData,index,size-2);
		return elementData;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {

		return new Iterator() {
			int cuindex = 0;
			int lastRet = -1;

			@Override
			public boolean hasNext() {
				return cuindex != size;
			}

			@Override
			public Object next() {
				int i = cuindex;
				if (i>=size){
					throw new NoSuchElementException();
				}
				if (i >= elementData.length)
					throw new ConcurrentModificationException();
				cuindex = i + 1;
				return elementData[lastRet=i];
			}

		};
	}


}
