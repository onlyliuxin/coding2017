package test05.stack;

import java.util.ArrayList;

public class Stack {
	private ArrayList<Object> elementData = new ArrayList<Object>();
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		return elementData.remove(size()-1);
	}
	
	public Object peek(){
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
