package basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		if (size< elementData.length)		{
		
		}
		else{
			Arrays.copyOf(elementData, elementData.length +1);
			
		}
		size++;
		elementData[size]=o;
			
	}
	public void add(int index, Object o){
		if (index >= size || index < 0){
			return;	
		}
		
		if (size >= elementData.length)		{
			Arrays.copyOf(elementData, elementData.length +1);
		} 
		
		size++;
		for (int i =  elementData.length-1; i>index;i ++){
			elementData[i]=elementData[i-1];
		}
		elementData[index]=o;
	}
	
	public Object get(int index){
		if (index >= size || index < 0){
			return null;
		}
		else {
			return elementData[index];
		}
		
	}
	
	public Object remove(int index){
		if (index >= size || index < 0){
			return null;
		}
		else {
			Object o = elementData[index];
			for (int i =index; i< size-1; i++){
				elementData[i]= elementData[i+1];
			}
			size--;
			return o;
		}
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}
