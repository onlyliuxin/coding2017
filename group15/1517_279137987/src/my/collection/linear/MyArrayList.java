package my.collection.linear;

import java.util.Arrays;

public class MyArrayList implements MyList{

	private int size = 0;
	private int CAPACITY = 10;
	private Object[] elementData = new Object[CAPACITY];

	public MyArrayList(int len){
		this.elementData = new Object[len];
	}
	
	public void add(Object obj){
		add(size,obj);
	}
	
	public void add(int index, Object obj){
		if(index < 0){
			System.out.println("insert position illegal.");
		}else{
			if(size + 1 < elementData.length){
				System.arraycopy(elementData, 0, this.elementData, 0, index);
				System.arraycopy(elementData, index, this.elementData, index+1, elementData.length-index-1);
				this.elementData[index] = obj;
			}else{
				Object[] newElementData = Arrays.copyOf(elementData, elementData.length + CAPACITY);
				System.arraycopy(elementData, index, newElementData, index+1, elementData.length - index);
				newElementData[index] = obj;
				this.elementData = newElementData;
			}
			size++;
		}
	}
	
	public Object remove(int index){
		if(index == 0){
			System.arraycopy(elementData, 1, this.elementData, 0, elementData.length-1);
		}else{
			System.arraycopy(elementData, 0, this.elementData, 0, index);
			System.arraycopy(elementData, index + 1, this.elementData, index, elementData.length-index-1);
		}
		size--;
		return elementData[index];
	}
	
	public Object get(int index){
		if(index < 0 || index > elementData.length-1){
			return "get position illegal.";
		}else{
			return elementData[index];
		}
	} 
	
	public int size(){
		return size;
	}
	
	public String toString(){
		String str ="toString():";
		for(int i=0; i<size; i++){
			str += elementData[i] + "\t";
		}
		return str;
	}

	/*private void grow(int stepLen){
		Object[] largerElement = new Object[CAPACITY + stepLen];
		System.arraycopy(elementData, 0, largerElement, 0, elementData.length);
		elementData = largerElement;
	}*/
	
	public MyIterator myIterator(){
		return new MyArrayListIterator(this);
	}
	
	private class MyArrayListIterator implements MyIterator{
		@SuppressWarnings("unused")
		MyArrayList list = null;
		int pos = 0;
		
		private MyArrayListIterator(MyArrayList list){
			this.list = list;
		}
		
		public boolean hasNext() {
			if(++pos > size){
				return false;
			}else{
				return true;
			}
		}

		public Object next() {
			return get(pos);
		}
		
		public Object remove(){		//?
			 return MyArrayList.this.remove(this.pos);
		}
		
	}
}
