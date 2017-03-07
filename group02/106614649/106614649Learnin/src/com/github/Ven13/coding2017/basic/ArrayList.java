package com.github.Ven13.coding2017.basic;

public class ArrayList implements List {
	
	//���ؼ��ϴ�С
	private int size = 0;
	
	//�ȸ���һ������Ϊ10������
	Object[] elementData = new Object[100];
	
	@Override
	//��̬���Ԫ��
	public void add(Object o) {
		//���ж������Ƿ�����
		if(size == elementData.length) {
			Object[] newObjects = new Object[elementData.length * 2];
			System.arraycopy(elementData, 0, newObjects, 0, elementData.length);
			elementData = newObjects;
		}
		
		//Ϊ����ӵ�Ԫ��ָ���±�
		elementData[size] = o;
		size++;
	}

	@Override
	public void add(int index, Object o) {
		//���ж������Ƿ�����
		if(size == elementData.length) {
			Object[] newObjects = elementData;
			this.elementData = new Object[elementData.length * 2];
			for(int j = 0; j < newObjects.length; j++) {
				this.elementData[j] = newObjects[j];
			}
		}
		
		for(int i = size - 1; i >= index; i--) {
			elementData[i+1] = elementData[i];
		}
		
		elementData[index] = o;
		size++;
	}

	@Override
	public Object get(int index) {
		return elementData[index];
	}

	@Override
	public Object remove(int index) {
		if (index > size) {
			return null;
		};
		
		int moveSize = size - index - 1;
		
		if (moveSize > 0) {
			System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		}
		elementData[--size] = null;

		//for(int i = index; i < elementData.length; i++) {
		//	elementData[i] = elementData[i+1];
		//}
		
		return elementData;
	}

	@Override
	public int size() {
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator {

		private int currentIndex = 0;
		
		@Override
		public boolean hasNext() {
			if(currentIndex >= size) return false;
			else return true;
		}

		@Override
		public Object next() {
			Object o = elementData[currentIndex];
		    currentIndex ++;
		    return o;
		}
	}
	
}
