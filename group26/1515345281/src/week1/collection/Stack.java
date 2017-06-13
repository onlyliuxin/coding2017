package week1.collection;

import java.util.EmptyStackException;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		if(isEmpty()){
			throw new EmptyStackException();
		}
		
		Object data=elementData.get(size()-1);
		elementData.remove(size()-1);
		return data;
	}
	
	public Object peek(){	
		return (Object)elementData.get(size()-1);
	}
	
	public boolean isEmpty(){
		if(elementData.size()==0)
			return true;
		else
			return false;
	}
	
	public int size(){
		return elementData.size();
	}
}
