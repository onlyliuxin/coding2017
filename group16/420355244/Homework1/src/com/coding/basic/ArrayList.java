package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		if(size < elementData.length){
			elementData[size] = o;
		}else{
			Object[] newElementData = new Object[elementData.length + elementData.length/2];
			System.arraycopy(elementData, 0, newElementData, 0, size);
			newElementData[size] = o;
			this.elementData = newElementData;
		}
		size++;
		
	}
	public void add(int index, Object o){
		if(index >= 0 && index <= size){
			//1.不扩容
			if(index == size - 1){
				//1.1 加在最后
				elementData[index] = o;
			}else{
				//1.2 加在前面
				//index的位置的数值变为改对象，index以后位置的都往后挪一位
				Object[] newElementData = new Object[elementData.length];
				System.arraycopy(elementData, 0, newElementData, 0, index);
				newElementData[index] = o ;
				System.arraycopy(elementData, index, newElementData, index + 1, size - index);
				this.elementData = newElementData;
			}
			size++;
		}else{
			throw new IndexOutOfBoundsException();
		}
	}
	
	public Object get(int index){
		if(index < size){
			return elementData[index];
		}else{
			throw new IndexOutOfBoundsException();
		}
	}
	
	public Object remove(int index){
		if(index < size){
			Object obj = elementData[index];
			Object[] newElementData = new Object[elementData.length];
			if(size != 1){
				//1.若集合长度为1
				if(0 == index){
					//1.1.如果remove的是0索引的
					System.arraycopy(elementData, 1, newElementData, 0, size - 1);
				}else if(index == size -1){
					//1.2.如果remove的是最后索引的
					System.arraycopy(elementData, 0, newElementData, 0, size - 1);
				}else{
					//1.3.在中间
					System.arraycopy(elementData, 0, newElementData, 0, index);
					System.arraycopy(elementData, index + 1, newElementData, index, size - index - 1);
				}
			}
			this.elementData = newElementData;
			size--;
			return obj;
		}else{
			throw new IndexOutOfBoundsException();
		}
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	@Override
	public String toString() {
		return "ArrayList [size=" + size + ", elementData=" + Arrays.toString(elementData) + "]";
	}
	
	
}
