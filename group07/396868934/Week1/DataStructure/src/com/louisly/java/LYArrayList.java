package com.louisly.java;
import com.louisly.java.LYIterator;

public class LYArrayList {
	private Object[] elementData = new Object[10];
	private int currentCount = 0;
	public void addObject(Object obj) {
		if (currentCount >= elementData.length) {
			grow();
		}
		elementData[currentCount] = obj;
		currentCount++;
	}
	
	public boolean removeObject(Object obj) {
		boolean existObj = false;
		int removeIndex = -1;
		int index = currentCount;
		for (int i = 0; i < index; i++) {
			Object element = elementData[i];
			boolean remove = false;
			if (element != null && element.equals(obj)) {
				elementData[i] = null;
				existObj = true;
				remove = true;
				// 以防存在一样的第二个元素
				if (removeIndex == -1) {
					removeIndex = i;
				}
				currentCount--;
			}
			// 将元素往前移动
			if (!remove) {
				elementData[removeIndex] = element;
				elementData[i] = null;
				removeIndex++;
			}
		}
		return existObj;
	}
	
	public boolean removeAtIndex(int index) {
		if (index > currentCount) {
			return false;
		}
		elementData[index] = null;
		
		for (int i = index+1; i < currentCount; i++) {
			elementData[i-1] = elementData[i];
			elementData[i] = null;
		}
		currentCount--;
		return true;
	}
	
	public void grow() {
		Object[] target = new Object[elementData.length*2];
		System.arraycopy(elementData, 0, target, 0, elementData.length);
		elementData = target;
	}
	
	public Object get(int index) {
		if (index > currentCount) {
			return null;
		}
		return elementData[index];
	}
	
	public int size() {
		return currentCount;
	}
	
	public LYIterator iterator() {
		return new LYArrayListIterator(this);
	}
	private class LYArrayListIterator implements LYIterator {
		LYArrayList arrayList = null;
		int position = 0;
		public LYArrayListIterator(LYArrayList arrayList) {
			this.arrayList = arrayList;
		}
		
		@Override
		public boolean hasNext() {
			
			return false;
		}
		@Override
		public Object next() {
			return elementData[position];
		}
		public void remove() {
			
		}
	}
}
