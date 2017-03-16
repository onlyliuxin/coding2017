package com.coding.basic;

public class Queue {
	private ArrayList elementData = new ArrayList();
	
	public void enQueue(Object o){
		int length = elementData.size();
		for (int i = 0; i < length; i++) {
			if(elementData.get(i)==null){
				elementData.add(i, o);
			}
		}
		length = elementData.size()-1;
	}
	
	public Object deQueue(){
		for (int i = 0; i < elementData.size(); i++) {
			if(elementData.get(i)!=null){
				elementData.remove(i);
				return elementData.get(i);
			}
		}
		return null;
	}
	
	public boolean isEmpty(){
		int a =0;
		for (int i = 0; i < elementData.size(); i++) {
			if(elementData.get(i)==null){
				a+=1;
			}
		}
		if(a==elementData.size()){
			return true;
		}else{
			return false;
		}
	}
	
	public int size(){
		int size=0;
		for (int i = 0; i < elementData.size(); i++) {
			if(elementData.get(i)==null){
				size += 1;
			}
		}
		return size;
	}
}