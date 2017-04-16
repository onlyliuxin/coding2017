package test05.stack;

import java.util.ArrayList;

public class Stack<T> {
	private ArrayList<T> elementData = new ArrayList<T>();
	
	public void push(T o){	
		elementData.add(o);
	}
	
	public T pop(){
		return elementData.remove(size()-1);
	}
	
	public T peek(){
		if (size()>0) {
			return elementData.get(size()-1);		
		} else {
			return null;
		}

	}
	
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	
	public int size(){
		return elementData.size();
	}
	
	@Override
	public String toString() {
		StringBuffer sb=new StringBuffer();
		while (peek()!=null) {
			sb.append(pop()).append(",");
		}

		return sb.substring(0, sb.length()-1);
	}

}
