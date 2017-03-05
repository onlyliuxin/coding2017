package com.coding.basic;
//import java.util.Iterator;

//import java.util.List;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		//int int_inarry = 10;
		grow(o);
	
	}
	public void add(int index, Object o){
		
		
		
		
	}
	
	public Object get(int index){
		return null;
	}
	
	public Object remove(int index){
		return null;
	}
	
	public int size(){
		return -1;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	private void grow(Object nbradd){
		
		if (this.size>this.elementData.length){
			Object[] arrayRefVar = new Object[this.elementData.length+1];
			growcopy(nbradd,arrayRefVar);
		}else{
			Object[] arrayRefVar = new Object[this.elementData.length];
			growcopy(nbradd,arrayRefVar);
		}
	}
	private void growcopy(Object nbraddcopy,Object[] arrayRefVarcopy){
		System.arraycopy(this.elementData, 0, arrayRefVarcopy, 0, this.elementData.length);
		this.elementData[0]=nbraddcopy;
		System.arraycopy(arrayRefVarcopy, 0, this.elementData, 1, this.size+1);
		this.size++;			
	}
	private void instrgrow(int nbrindex,Object[] arraynbo,Object ino){
		
		//Object[] arrayRefVar2 = new Object[nbrindex];
		Object[] arrayRefVar3 = new Object[this.size-nbrindex];
		

		System.arraycopy(this.elementData, nbrindex, arrayRefVar3, nbrindex, this.size);
		this.elementData[nbrindex]=ino;
		System.arraycopy(arrayRefVar3, 0, this.elementData, nbrindex+1, this.size);
		
		
	}
}
