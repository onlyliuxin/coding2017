package com.coding.basic;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		
		if(size<elementData.length)
		{elementData[size] = o;
			this.size++;}
		else{
			elementData = grow(elementData,1);
			elementData[size] = o;
			this.size++;
		}
		
		
	}
	
	public void add(int index, Object o){	
			if(index<(this.size)&&this.size<elementData.length)
			{
				for(int i=this.size;i>index;i--){
					elementData[i]=elementData[i-1];
				}
				elementData[index] = o;
				this.size++;
			}
			else if(index<(this.size)&&this.size==elementData.length)
				{
				elementData = grow(elementData,1);
				elementData[index] = o;
				this.size++;
				}
			else{
				System.out.println("index/>size¼ÓÈëÊ§°Ü");
				
			}
			
		
	}
	
	public Object get(int index){
		
		if(this.size>0&&index<(this.size))
		return elementData[index];
		else{
			return null;
		}
	}
	
	public Object remove(int index){
		
		if(this.size>0&&index<(this.size))
		{  Object o= elementData[index];
			for(int i=index;i<this.size;i++){
				elementData[i]=elementData[i+1];
			}
			this.size--;
			return o;
			
			}
			else{
				return null;
			}
	}
	
	public int size(){
		return this.size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator(this);
	}
	
	private static Object[] grow (Object[]src,int size){
		Object[] target = new Object[src.length+size];
		System.arraycopy(src, 0, target, 0, src.length);
		return target;
	}
	private class ArrayListIterator implements Iterator{
	private	ArrayList arrayList;
		private int pos = 0;
public ArrayListIterator(ArrayList arrayList) {
	this.arrayList =arrayList;
	
}

		@Override
		public boolean hasNext() {
			pos++;
			if(pos>arrayList.size){				
				return false;
			}else
			return true;
		}

		@Override
		public Object next() {
			
			return  arrayList.get(pos-1);
		}}
}
