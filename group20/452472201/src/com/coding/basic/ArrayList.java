package com.coding.basic;

public class ArrayList implements List {

	private  int size=0;

	private  Object[] elementData =new Object[5];


	private void  ensureCapacityInternal(){
		if(size==elementData.length){
			Object[] newArray = new Object[size*2];
			System.arraycopy(elementData, 0, newArray, 0, elementData.length);
			elementData=newArray;
		}
	}

	public void add(Object o){
		ensureCapacityInternal();
		elementData[size]=o;
		size++;
	}

	public void add(int index, Object o){
		ensureCapacityInternal();
		if(index<0){
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.arraycopy(elementData, index, elementData, index+1,size-index );
		elementData[index]=o;
		size++;
	}

	public Object get(int index){
		if(index<0||index>=size){
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return elementData[index];
	}

	public Object remove(int index){
		if(index>=size){
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{

			int numMoved=size-index-1;
			if(numMoved>0){
				System.arraycopy(elementData, index+1, elementData, index, numMoved);
			}

			elementData[size--] = null;			
		}
		return index;

	}


	public int size(){
		return size;
	}


	private class Iter implements Iterator {

		private int coursor=-1;

		public boolean hasNext(){
			return coursor+1<size;
		}

		public String next(){
			coursor++;
			return (String) elementData[coursor];
		}
	}

	public Iterator iterator(){

		return new Iter();

	}

	public static void main(String[] args){
		ArrayList alist = new ArrayList();
		alist.add("1");
		alist.add("2");
		alist.add("3");
		alist.add("4");
		alist.add(2, "五");
		System.out.println(alist.size());
		Iterator i = alist.iterator();
		while(i.hasNext()){
		System.out.print(i.next());
		}

	}
}




