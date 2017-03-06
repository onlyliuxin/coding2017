package com.coding.basic.list;

import java.util.Arrays;

public class ArrayList implements List {
	static final int length = 10;
	private Object[] elementData = new Object[length];
	private int index = 0;

	private Object[] getElementData() {
		return elementData;
	}

	private void setElementData(Object[] elementData) {
		this.elementData = elementData;
	}

	private int getIndex() {
		return index;
	}

	private void setIndex(int index) {
		this.index = index;
	}

	private void increaseElementDataLength() {
		int size = getElementData().length;
		if (getIndex() >= size) {
			size += length;
			setElementData(Arrays.copyOf(getElementData(), size));
		}
	}

	public void add(Object o) {
		increaseElementDataLength();
		getElementData()[getIndex()] = o;
		setIndex(getIndex()+1);
	}

	public void add(int index, Object o){
		if (index >= size()) {
			throw new ArrayIndexOutOfBoundsException("数组越界");
		} else {
			if (null != get(size()-1)) {
				increaseElementDataLength();
			}
		}
		Object[] tempElementData = Arrays.copyOfRange(elementData, index, size());
		setIndex(size()+1);
		for (int i = 0 ; i < tempElementData.length ; i++) {
			int pos = index+i+1;
			elementData[pos] = tempElementData[i];
		}
		elementData[index] = o;
	}

	public Object get(int index) {
		return getElementData()[index];
	}

	public Object remove(int index) {
		if (index >= size()) {
			throw new ArrayIndexOutOfBoundsException("数组越界");
		}
		Object object = get(index);
		// 如果是删除的最后一个元素，就不需要移动前面的元素
		if (index != size()-1) {
			Object[] tempElementData = Arrays.copyOfRange(elementData, index+1, size());
			for (int i = 0 ; i < tempElementData.length ; i++) {
				elementData[index+i] = tempElementData[i];
			}	
		} 
		// 移动元素完成后，收回最后一个元素内存空间
		elementData[index] = null;
		setIndex(size()-1);
		
		// 收回多余的内存空间
		int tempLength = size()%length == 0 ? size() : (size()/length+1)*length;
		if (getElementData().length > tempLength) {
			setElementData(Arrays.copyOf(getElementData(), tempLength));
		}
		return object;
	}

	public int size() {
		return getIndex();
	}

	public Iterator iterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator {
		private int iteratorIndex = 0;

		@Override
		public boolean hasNext() {
			return iteratorIndex >= size();
		}

		@Override
		public Object next() {
			Object object = getElementData()[iteratorIndex];
			iteratorIndex++;
			return object;
		}

	}

}
