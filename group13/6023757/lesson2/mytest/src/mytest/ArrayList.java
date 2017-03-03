package mytest;

import java.util.Arrays;

public class ArrayList {
	private int size = 0;
	private Object[] elementData;
	
	public ArrayList(){
		elementData = new Object[10];
	}

	public ArrayList(int InitSize){
		elementData = new Object[InitSize];
	}
	public void add(Object o){
		if(this.size == this.elementData.length){
			this.enlarge();
			
		}		
		this.elementData[size] = o;
		this.size++;
	}
	
	public void add(int index, Object o){
		if(this.size == this.elementData.length){
			this.enlarge();
		}		
		if(index >= this.size){
			throw new IndexOutOfBoundsException("Index: "+index+" is out of band");
		}
		else{
			Object[] newArray = new Object[this.elementData.length];
			System.arraycopy(this.elementData, 0, newArray, 0, index);
			newArray[index] = o;
			System.arraycopy(this.elementData, index, newArray, index + 1, this.size - index);
			this.elementData = newArray;
			this.size++;
		}
	}
	
	public Object get(int index){
		if(index > -1 && index < this.size()){
			return elementData[index];	
		}
		else{
			return null;
		}
	}
	
	public void remove(int index){
		if(index >= this.size){
			System.out.println("Index is out of scope");
			return;
		}
		
		if(index == this.size - 1){
			this.elementData[index] = null;
		}
		else if(index == 0){
			Object[] newArray = new Object[this.elementData.length];
			System.arraycopy(this.elementData, 1, newArray, 0, this.size -1);
			this.elementData = newArray;			
		}
		else{
			Object[] newArray = new Object[this.elementData.length];
			System.arraycopy(this.elementData, 0, newArray, 0, index-1);
			System.arraycopy(this.elementData, index, newArray, index-1, this.size - index);
			this.elementData = newArray;
		}
		
		this.size--;
	}
	
	public int size(){
		return this.size;
	}
	
	public void enlarge(){
		Object[] newArray = new Object[this.elementData.length+5];
		System.arraycopy(this.elementData, 0, newArray, 0, this.elementData.length);
		this.elementData = newArray;
	}
		
}

