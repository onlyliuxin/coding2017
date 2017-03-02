package data_Structures;


public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	

	public void add(Object o){
		
		elementData[size] = o;
		size++;
	}
	public void add(int index, Object o){
		
		int temp = size;
		while(temp > index)
		{
			elementData[temp] = elementData[temp-1];
			temp--;
		}
		
		elementData[index] = o;
		
		size++;
		
		
	}
	
	public Object get(int index){
		return elementData[index];
	}
	
	public Object remove(int index){
		
		int temp = index;
		while(temp < size-1)
		{
			elementData[temp] = elementData[temp+1];
			temp++;
		}
		size--;
		
		return elementData[index];
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}
