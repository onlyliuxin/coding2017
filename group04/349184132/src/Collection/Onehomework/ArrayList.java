package Collection.Onehomework;

import java.util.Date;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData ;

	public ArrayList(){ this(10); }
	public ArrayList(int capacity){
		if(capacity<0)
			throw new IllegalArgumentException();
		elementData = new Object[capacity];
	}
	public void add(Object o){
		if(size==elementData.length)
			ResizeCapacity();
		elementData[size++] = o;
	}
	private void ResizeCapacity(){
		Object[] newDatas = new Object[size*2+1];
		System.arraycopy(elementData,0,newDatas,0,size);
		elementData = newDatas;
	}
	private void rangeCheck(int index){
		if(index<0 || index > size-1)
			throw new IllegalArgumentException();
	}
	public void add(int index, Object o){
		rangeCheck(index);
		System.arraycopy(elementData,index,elementData,index+1,size-index);
		elementData[index] = o;
		size++;
	}

	
	public Object get(int index){
		rangeCheck(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		rangeCheck(index);
		Object oldElement = elementData[index];
		System.arraycopy(elementData,index+1,elementData,index,size-index-1);
		size--;
		return oldElement;
	}
	
	public int size(){
		return size;
	}
	public boolean isEmpty(){ return size==0; }
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator{
		private int pos = 0;
		@Override
		public boolean hasNext() {
			return pos<size;
		}

		@Override
		public Object next() {
			if(pos>size)
				throw new IllegalArgumentException();
			return elementData[pos++];
		}

	}

	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add(1);
		list.add("str");
		list.add(new Date());



		Iterator iter = list.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		list.add(2,123);
		list.add(0,15);
		Iterator iter2 = list.iterator();
		while(iter2.hasNext()){
			System.out.println(iter2.next());
		}



	}

	
}
