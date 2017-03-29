package com.tiaozaoj;
import java.util.Arrays;

public class NewArrayList {
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	//直接在后面添加
	public void add(Object o){
		//先判断该数组是否已满
		if(this.size == elementData.length){
			this.grow(elementData,5);
		}
		this.elementData[size] = o;
		this.size++;
	}
	
	private void grow(Object[] src,int length){
		this.elementData = Arrays.copyOf(src, src.length+length);
	}
	
	public void add(int index,Object o){
		this.verifyIndex(index);
		//先判断该数组是否已满
		if(this.size == elementData.length){
			this.grow(elementData,5);
		}
		for(int i=size;i>index;i--){
			this.elementData[i+1] = this.elementData[i];
		}
		this.elementData[index] = o;
		this.size++;
	}
	
	private void verifyIndex(int index){
		try{
			if(index <0 || index > this.size)
				throw new Exception("下标越界");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Object get(int index){
		this.verifyIndex(index);
		return this.elementData[index];
	}
	
	public Object remove(int index){
		Object o = elementData[index];
		this.verifyIndex(index);
		for(int i=index;i<size;i++){
			this.elementData[i] = this.elementData[i+1];
		}
		this.size--;
		return o;
	}
	
	public int size(){
		return this.size;
	}
	
	public NewIterator iterator(){
		return new ArrayListIterator(this);
	}
	
	private class ArrayListIterator implements NewIterator{
		NewArrayList l = null;
		int pos = 0;
		
		private ArrayListIterator(NewArrayList l){
			this.l = l;
		}
		
		public boolean hasNext() {
			// TODO 自动生成的方法存根
			return pos < l.size();
		}
		
		public void remove(){
			this.l.remove(pos);
		}

		public Object next() {
			// TODO 自动生成的方法存根
			return l.get(pos++);
		}
	}
}
