package data_structure;


import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class MyArrayList {
	private int modCount;
	
	private static final Object[] EMPTY_ELEMENTDATA = {};
	//容量
    private int  capacity;
	//实际添加元素长度
	private int size=0;
	//定义一个私有的element数组
	private Object[] elementData;
	
	public  MyArrayList(){
		elementData=EMPTY_ELEMENTDATA;
		capacity=0;
	}
	
    public MyArrayList(int initialCapacity) {
     
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        this.elementData = new Object[initialCapacity];
        capacity=initialCapacity;
    }
	
	public void add(Object o){
		
	
		 ensureCapacityInternal(size + 1); 
		elementData[size++] = o;
	
		
	}
	
	public void add(int index ,Object o){
		
		rangCheck(index);
		ensureCapacityInternal(size + 1); 

		System.arraycopy(elementData, index, elementData, index + 1,size - index);
		elementData[index] =o;
		size++;

			
			
		
	}
	public Object get(int index){
		rangCheck(index);
		return elementData[index];
	}
	public Object remove(int index){
		modCount++;
		rangCheck(index);
		Object o=null;
		o=elementData[index] ;
		int numMoved = size - index - 1;
		if (numMoved > 0)
		
		System.arraycopy(elementData, index+1, elementData, index ,size - index-1);
		elementData[--size] = null; 
	
		
		return o;
	}
	public int size(){
		return size;
		
	}
	public int Capacity(){
		return capacity;
	}
	private void rangCheck(int index){
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}
	
	private String outOfBoundsMsg(int index) {
	        return "Index: "+index+", Size: "+size;
	    }

//	private class MyIndexOutOfBoundsException extends RuntimeException{
//		@SuppressWarnings("unused")
//		public MyIndexOutOfBoundsException(String e) {
//	        super();
//	    }
//	}
    private void ensureCapacityInternal(int minCapacity) {
    	
		modCount++;
        if (elementData == EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(10, minCapacity);
            capacity=minCapacity;
        }
        if (minCapacity - elementData.length > 0)

          grow(minCapacity);
    }
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        capacity=newCapacity;
  
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
    public Iterator<Object> iterator() {
        return new Itr();
    }
    private class Itr implements Iterator<Object> {
    	int cursor;      
        int lastRet = -1;
        int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor != size;
        }

		@Override
		public Object next() {
		
	      int i = cursor;
	      if (i >= size)
	      throw new NoSuchElementException();
	      Object[] elementData = MyArrayList.this.elementData;
	      if (i >= elementData.length)
	      throw new ConcurrentModificationException();
	            cursor = i + 1;
	      return elementData[lastRet = i];
		
		}

		@Override
		public void remove() {
			 if (lastRet < 0)
	                throw new IllegalStateException();
	            checkForComodification();

	            try {
	                MyArrayList.this.remove(lastRet);
	                cursor = lastRet;
	                lastRet = -1;
	                expectedModCount = modCount;
	            } catch (IndexOutOfBoundsException ex) {
	                throw new ConcurrentModificationException();
	            }
			
		}
	    final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

 
}
