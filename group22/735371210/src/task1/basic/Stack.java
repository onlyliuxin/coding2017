package task1.basic;

import java.util.ArrayList;
public class Stack {

	private ArrayList elementData =new ArrayList();
	
	public void push(Object o){
		
		elementData.add(o);
	}
	
	public Object pop(){
		
		return elementData.remove(size()-1);
		
	}
	
	public boolean isEmpty(){
		return elementData.isEmpty();
		
	}
	
	public Object peek(){
		
		return elementData.get(size()-1);
		
		
	}
	
	public int size(){
		return elementData.size();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
