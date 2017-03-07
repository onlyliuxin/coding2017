package Week01;

import java.util.NoSuchElementException;

/*
 * time:2017-2-25 13:19 created by Doen
 * change
 * */
public class Stack {

	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		if (isEmpty())
			throw new  NoSuchElementException();
		return elementData.remove(elementData.size()-1);
	}
	
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	
	public int size(){
		return elementData.size();
	}
}
