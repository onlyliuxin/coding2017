package com.coding.basic;

public class ArrayList implements List {

	private int size = 0;
	private Object[] obj = new Object[5];
	
	@Override
	public void add(Object o) {
		if(this.size < 0 )
			System.out.print("Error");
		this.extend(100);
		obj[this.size] = o;
		this.size ++;
	}

	@Override
	public void add(int index, Object o) {
		
		if(index < 0)
			System.out.println("Error");
		if(index < this.size){
			extend(100);
			for(int i = this.size;i < index; i--){
				obj[i+1] = obj[i];
			}
			obj[index] = o;
		}else if(index >= size){
			extend(100);
			obj[size] = o;
		}
		this.size++;
	}

	@Override
	public Object get(int index) {
		if(index < 0 || index > size){
			System.out.println("Error");
			return null;
		}
		return obj[index];
	}

	@Override
	public Object remove(int index) {
		if(index < 0 || index > size){
			System.out.println("Error");
			return null;
		}
		for(int i = index;i <= size;i++){
			obj[i] = obj[i+1];
		}
		size--;
		return obj[index];
	}

	@Override
	public int size() {
		return size;
	}
	public int length(){
		return obj.length;
	}
	public void extend(int newLength){
		if (this.size >= obj.length){
			//À©Õ¹Êý×é
			Object[] old = obj;
			obj = new Object[size+newLength];
			for(int i = 0;i < size; i++){
				obj[i] = old[i];
			}
		}
		return;
	}
	public void Iteror(){
		for(int i = 0 ;i < size ; i++){
			System.out.println(obj[i]);
		}
	}
}
