package com.github.mrwengq.first;

public class ArrayList implements List {

	private int size = 0;
	private Object elementData[] = new Object[5];
	
	public void add(Object o) {
		if (size >= elementData.length)
			elementData = copyAddArray(elementData);
		elementData[size] = o;
		size++;
	}

	public void add(int index, Object o) {
		if (index > size - 1 || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			elementData = addUpdateArray(elementData, index);
			elementData[index] = o;
			size++;
			return;
		}
	}

	public Object get(int index) {
		if (index > size - 1 || index < 0)
			throw new ArrayIndexOutOfBoundsException();
		else
			return elementData[index];
	}

	public Object remove(int index) {
		if (index > size - 1 || index < 0)
			throw new ArrayIndexOutOfBoundsException();
		if (index == size - 1) {
			elementData[index] = null;
			size--;
			return null;
		} else {
			delUpdateArray(elementData, index);
			size--;
			return null;
		}
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new Iterator() {

			int index=-1;

			public boolean hasNext() {
				index++;
				if(index<size){
					
					Object ob = elementData[index];
					return true;
				}
				return false;
			}

			public Object next() {
				return elementData[index];
			}

		};
	}

	private Object[] copyAddArray(Object elementData[]) { //对数组扩容 增加量为原长度3/4
		Object ob[] = new Object[elementData.length+(elementData.length * 3) / 4];
		System.arraycopy(((Object) (elementData)), 0, ((Object) (ob)), 0,
				elementData.length);
		return ob;
	}

	private Object[] addUpdateArray(Object elementData[], int index) {//添加时修改数组索引
		Object temp = null;   //中间变量
		for (int i = 0; i < size; i++)
			if (i > index) {//判断受影响索引
				temp = elementData[index];
				elementData[index] = elementData[i];
				elementData[i] = temp;
				if (i == size - 1) {  //判断为最后一位
					if (size > elementData.length)
						elementData = copyAddArray(elementData);
					elementData[size] = elementData[index];
				}
			}

		return elementData;
	}

	private void delUpdateArray(Object elementData[], int index) {//删除时修改索引
		for (int i = 0; i < size; i++){
			
			if (i > index && i < size ){//判断受影响索引
				elementData[i - 1] = elementData[i];				
				if (i == size - 1){
					elementData[i] = null;
				}
			}

		}
	}

}
