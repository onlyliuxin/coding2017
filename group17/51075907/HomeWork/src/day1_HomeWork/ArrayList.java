package day1_HomeWork;

 
 public class ArrayList implements List {
 	
 	private int size = 0;
 	
 	private Object[] elementData = new Object[100];
 	
 	//如果数组空间超过当前大小,保证容量
 	public void ensureCapacity( int newCapacity)
 	{
 		if ( newCapacity < size)
 			return;
 		
 		Object [] old =elementData;
 		elementData =(Object []) new Object[newCapacity];
 		for (int i=0; i<size(); i++ )
 			elementData[i] =old[i]; 			 		
 	}
 	
 	public void add(Object o){
 		add(size(),o);
 		return;
 	}
 	public void add(int index, Object o){
 		if( elementData.length== size() )
 			ensureCapacity ( size() *2 +1);
 		for ( int i = size; i > index; i--)
 			elementData[i] =elementData [i-1];
 		elementData[index]= o;
 		size ++;
 		
 	}
 	
 	public Object get(int index){
 		if ( index<0 || index >=size())
 			throw new ArrayIndexOutOfBoundsException();
 		return elementData[index];
 	}
 	
 	public Object remove(int index){
 		Object remove_elementData=elementData[index];
 		for ( int i=index;i<size()-1;i++)
 			elementData[i]= elementData[i+1];
 		size --;
 		return remove_elementData;
 	}
 	
 	public int size(){
 		return size;
 	}
 	
 	public java.util.Iterator iterator(){
 		return null;
 	}
 	
 	private class ArrayListIterator implements java.util.Iterator
 	{
 		private int current =0;
 		
 		public boolean hasNext()
 		{
 			return current <size();
 		}
 		
 		public Object next()
 		{
 			if (!hasNext())
 				throw new java.util.NoSuchElementException();
 			return elementData[current++];
 		}
 	}
 	
 }