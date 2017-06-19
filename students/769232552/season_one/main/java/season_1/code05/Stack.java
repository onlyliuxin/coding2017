package code05;

import code01.ArrayList;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		if(o == null){
			return;
		}
		elementData.add(o);
	}


	public Object pop(){
		Object last = null;
		int last_index = elementData.size() - 1;
		if(last_index >= 0){
			last = elementData.get(last_index);
			elementData.remove(last_index);
		}
		return last;
	}
	
	public Object peek(){
		Object last = null;
		int last_index = elementData.size() - 1 ;
		if(last_index >= 0){
			last = elementData.get(last_index);
		}
		return last;
	}
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	public int size(){
		return elementData.size();
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int i = 0;
		for (; i < size() - 1; i++) {
			sb.append(elementData.get(i));
			sb.append(", ");
		}
		sb.append(elementData.get(i));
		sb.append("]");
		return sb.toString();
	}
}
