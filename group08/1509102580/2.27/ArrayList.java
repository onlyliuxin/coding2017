package com.zzk.coding2017.zuoye_1;


public class ArrayList implements List {
	
	private int size = 0;
	private int length = 100;
	private Object[] elementData = new Object[length];
	
	public void add(Object o){
		if(size<length){
			elementData[size]=o;
			size++;
		}else{
			length = length + 100;
			Object[] tmp = new Object[length];
			System.arraycopy(elementData, 0, tmp, 0, size);
			elementData = tmp;
			elementData[size]=o;
			size++;
		}
	}
	public void add(int index, Object o){
		if(index<0||index>size-1){
			return ;
		}else{
			if(size<length){
				Object[] tmp = new Object[size-1-index];
				System.arraycopy(elementData, index+1, tmp, 0, size-1-index);
				elementData[index+1]=o;
				System.arraycopy(tmp, 0, elementData, index+2, size-1-index);
				size++;
			}else{
				//增加空间
				length = length + 100;
				Object[] tmp1 = new Object[length];
				System.arraycopy(elementData, 0, tmp1, 0, size);
				elementData = tmp1;
				
				//插入
				Object[] tmp2 = new Object[size-1-index];
				System.arraycopy(elementData, index+1, tmp2, 0, size-1-index);
				elementData[index+1]=o;
				System.arraycopy(tmp2, 0 , elementData, index+2, size-1-index);
				size++;
			}
		}
	}
	
	public Object get(int index){
		if(index<0||index>size-1){
			return null;
		}else{
			return elementData[index];
		}
	}
	
	public Object remove(int index){
		if(index<0||index>size-1){
			return null;
		}else{
			Object result = elementData[index];
			if(index+1==size){//即index是最后一个元素
				elementData[index] = null;
				size--;
				return result;
			}else{
				System.arraycopy(elementData, index+1, elementData, index, size-1-index);
				size--;
				return result;
			}
		}
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new Iterator() {
			int current = 0;
			@Override
			public Object next() {
				// TODO Auto-generated method stub
				if(current<size){
					return elementData[current++];
				}else{
					return null;
				}
			}
			
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				if(current<size)
					return true;
				else
					return false;
			}
		};
	}
	
}
