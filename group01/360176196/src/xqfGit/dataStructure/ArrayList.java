package xqfGit.dataStructure;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	

	public void add(Object o){
		if(this.size >= elementData.length){
		   elementData = Arrays.copyOf(elementData, size+1);
		   elementData[size] = o;
		   size++;
		}
		 else{
		  elementData[size-1] = o;	
		  size++;
		}
		
	}

	public void add(int index, Object o){
		if(index<0 || index>elementData.length){
		   throw new ArrayIndexOutOfBoundsException("OutOfBounds");
		}
		else{
		   System.arraycopy(elementData, index, elementData, index+1, elementData.length+1);
		   elementData[index] = o;
		   size++;
		}
	}
	

	public Object get(int index){
		if(index<0 || index>elementData.length){
		   throw new ArrayIndexOutOfBoundsException("OutOfBounds");
		}
		else{
		   return elementData[index];
		}
	}
	

	public Object remove(int index){
		if(index<0 || index>elementData.length){
		   throw new ArrayIndexOutOfBoundsException("OutOfBounds");
		}
		else{
		   Object reObject = elementData[index];
		   System.arraycopy(elementData, index+1, elementData, index, elementData.length-1);
		   size--;
		   return reObject;
		}
	}
	
	public int size(){
		if(this.size < elementData.length){
			return size;
		}else{
			elementData = Arrays.copyOf(elementData, size);
			return size;
		}
	}
	
	
	public Iterator iterator(){
		return null;
	}
	
}
