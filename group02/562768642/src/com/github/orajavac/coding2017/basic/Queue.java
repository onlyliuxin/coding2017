package com.github.orajavac.coding2017.basic;

public class Queue {
	
private ArrayList elementData = new ArrayList();
	
	public void enQueue(Object o){
		elementData.add(o);
	}
	
	public Object deQueue(){
		Object obj = elementData.get(0);
		elementData.remove(0);
		return obj;
	}
	
	public ArrayList getElementData() {
		return elementData;
	}

	public void setElementData(ArrayList elementData) {
		this.elementData = elementData;
	}

	public boolean isEmpty(){
		if (elementData.size()>0)
			return true;
		return false;
	}
	
	public int size(){
		return elementData.size();
	}
}
