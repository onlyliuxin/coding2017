package task1.basic;

import java.util.Arrays;

public class MyArrayList implements List {

	private int size=0;
	private Object[] elementData=new Object[3];
	
	public void add(Object o){
		
		checkLength(size+1);
		
		elementData[size++]=o;
		
	}
	
	public void checkLength(int minLength){
		
		if(minLength>elementData.length){
			grow(minLength);
			
		}
	}
	
	public void grow(int minLength){
		
		int oldLength = elementData.length;
		int newLength = oldLength + oldLength>>1;
		
		if(newLength < minLength){
			newLength=minLength;
		}
		
		elementData= Arrays.copyOf(elementData, newLength);
		
		
		
	}
	public void add(int index ,Object o){
		checkLength(size+1);
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index]=o;
		size++;
		
	}
	public Object get(int index){
		
		return elementData[index];
		
		
	}
	public Object remove(int index){
		
		Object element=elementData[index];
		
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
	
		elementData[size-1]=null;
		
		size--;
		
		return element;
	}
	
	public int size(){
		
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	class ArrayListIterator implements Iterator{
		
		int pos=0;
		
		public boolean hasNext(){
			
			if(elementData[pos]!=null){
				return true;
			}
			
			return false;
			
		}
		
		public Object next(){
			
			return elementData[pos++];
		}
		
	}
	public static void main(String[] args){
		MyArrayList my=new MyArrayList();
		my.add(0);
		my.add(1);
		my.add(2,10);
		my.add(1,11);
		my.add(3,32);
		
		Object ele=my.remove(2);
		
		System.out.println(ele);
		System.out.println(my.get(1));
		System.out.println(my.size());
		
		System.out.println("---------");
		
		Iterator it=my.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		
		
	}
	
}
