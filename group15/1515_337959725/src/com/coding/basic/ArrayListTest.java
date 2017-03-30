package com.coding.basic;

import java.util.Arrays;

public class ArrayListTest implements ListTest{
	private Object[] obj;
	
	public ArrayListTest() {
		obj=new Object[0];
	}

	public void add(Object o) {
		 obj = Arrays.copyOf(obj, obj.length+1);
		 obj[obj.length-1]=o;
	}

	public void add(int index, Object o) {
		obj = Arrays.copyOf(obj, obj.length+1);
		for(int i=index;i<obj.length-1;i++){
			obj[i+1]=obj[i];
		}
		obj[index]=o;
	}

	public Object get(int index) {
		return obj[index];
	}

	public Object remove(int index) {
		Object o=obj[index];
		for(int i=index;i<obj.length-1;i++){
			obj[i]=obj[i+1];
		}
		obj[obj.length-1]=null;
		obj=Arrays.copyOf(obj, obj.length-1);
		return o;
	}

	public int size() {
		return obj.length;
	}
	
	public Its getIts(){
		Its its=new Its();
		return its;
	}
	
	class Its implements IteratorTest{
		int count=0;

		public boolean hasNext() {
			if(count<=size()-1){
				return true;
			}else{
				return false;
			}
		}

		public Object next() {
			Object obj = get(count);
			count++;
			return obj;
		}
	}
}
