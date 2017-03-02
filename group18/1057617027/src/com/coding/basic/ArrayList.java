package com.coding.basic;


@SuppressWarnings("rawtypes")
public class ArrayList implements List{
	
		private int size = 0;
		
		private Object[] elementData = new Object[100];
		public void kuorong(int size){
			if(size>elementData.length){
				Object[] elementDataTemp = new Object[size*2];
				System.arraycopy(elementData, 0, elementDataTemp, 0, elementData.length);
				elementData = elementDataTemp;
			}
		}
		public void add(Object o){
			kuorong(size);
			elementData[size] = o;
			++size;
		}
		public void add(int index, Object o){
			if(index>size||index<0) 
				throw new IndexOutOfBoundsException("请确保你的index值不大于"+size+"且不小于0");
			kuorong(++size);
			System.arraycopy(elementData, index, elementData, index+1, size-index);
			elementData[index] = o;
		}
		
		public Object get(int index){
			
			return elementData[index];
		}
		
		public Object remove(int index){
			if(index>size||index<0) 
				throw new IndexOutOfBoundsException("请确保你的index值不大于"+size+"且不小于0");
			
			System.arraycopy(elementData, index+1, elementData, index, size-index);
			size--;
			return elementData;
		}
		
		public int size(){
			return size;
		}
		
		public Iterator iterator(){

			return new myiterator();
		}
		public class myiterator implements Iterator{
			private int nextIndex;
			public boolean hasNext(){
				return nextIndex!=size;
			}
			public Object next(){
			    return elementData[nextIndex++];
			}
			
		}
	public static void main(String[] args) {
		ArrayList al = new ArrayList();
		
		al.add(1);
		al.add(2);
		al.add(3);
		al.add(4);
		al.add(2,5);
		al.remove(2);
		for(int i= 0;i<5;i++){
		System.out.println(al.get(i));}
		System.out.println(al.size());

	}

}
