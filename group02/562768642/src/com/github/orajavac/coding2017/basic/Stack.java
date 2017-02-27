package com.github.orajavac.coding2017.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){		
		elementData.add(o);
	}
	
	public Object pop(){
		int s=elementData.size();
		Object obj = null;
		for (int i=s-1;i>=0;i--){
			if(elementData.get(i)!=null){
				obj = elementData.get(i);
				elementData.remove(i);
				break;
			}
		}
		return obj;
	}
	
	public Object peek(){
		int s=elementData.size();
		Object obj = null;
		for (int i=s-1;i>=0;i--){
			if(elementData.get(i)!=null){
				obj = elementData.get(i);
				break;
			}
		}
		return obj;
	}
	public boolean isEmpty(){
		if (elementData.size()>0)
			return true;
		return false;
	}
	public int size(){
		return elementData.size();
	}
	public ArrayList getElementData() {
		return elementData;
	}

	public void setElementData(ArrayList elementData) {
		this.elementData = elementData;
	}
}
