package com.coding.basic;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];

	public void add(Object o){
        elementData[size++] = o;
	}
	public void add(int index, Object o){

	    if (index < 0|| index > size)
            throw new IndexOutOfBoundsException();
        for (int j = size; j > index; j--) {
            elementData[j] = elementData[j++];
        }
        elementData[index] = o;
    }
	
	public Object get(int index){
        if (index < 0|| index > size)
            throw new IndexOutOfBoundsException();
		return elementData[index];
	}
	
	public Object remove(int index){
        if (index < 0|| index > size)
            throw new IndexOutOfBoundsException();
        int i;
        for (i = 0; i < size; i++) {
            if(i == index)
                break;
        }
        if(i > index)
            return null;
        else{
            for (int j = i; j < size; j++) {
                elementData[j] = elementData[++j];
            }
            return elementData[index--];
        }
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}
