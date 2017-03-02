package com.sx.structures;

public class MyArrayList implements MyList {
	
	private int size;
	private int ex=10;
	private int last=-1;
	private Object [] arr;
	
	public MyArrayList() {
		size = 10;
		arr = new Object[size];
	}

	@Override
	public void add(Object o) {
		last++;
		if(last==size){
			size += ex; 
			Object[] temp = new Object[size];
			System.arraycopy(arr, 0, temp, 0, arr.length);
			arr = temp;
		}
		arr[last]=o;
	}

	@Override
	public void add(int index, Object o) {
		add(o);
		for(int i=arr.length-1;i>index;i--)
			arr[i]=arr[i-1];
		arr[index]=o;
	}

	@Override
	public Object get(int index) {
		return arr[index];
	}

	@Override
	public Object remove(int index) {
		Object element = arr[index];
		for(int i=index;i<last;i++)
			arr[i]=arr[i+1];
		last--;
		return element;
	}

	@Override
	public int size() {
		return last+1;
	}
	
	public Iterator Iterator(){
		return new MyArrListIterator(this);
	}
	
	private class MyArrListIterator implements Iterator{
		
		private MyList list;
		private int p = 0;
		public MyArrListIterator(MyList list) {
			this.list = list;
		}

		@Override
		public boolean hasNext() {
			p++;
			if(p>list.size())
				return false;
			return true;
		}

		@Override
		public Object next() {
			if(hasNext())
				return list.get(p-1);
			return -1;
		}
		
	}

}
