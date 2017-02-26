package data_structure;

public class MyStack {
	private MyArrayList elementData = new MyArrayList();
	public void push(Object o){		
		elementData.add(o);
	}
	
	public Object pop(){
		return elementData.remove(elementData.size()-1);
	}
	
	public Object peek(){
		return elementData.get(0);
	}
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	public int size(){
		return elementData.size();
	}
}
