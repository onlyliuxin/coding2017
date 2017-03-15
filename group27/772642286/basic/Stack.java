package week01.basic;

public class Stack {
	private LinkedList elementData = new LinkedList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		return elementData.remove(0);
	}
	
	public Object peek(){
		return elementData.get(0);
	}
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	public int size(){
		return elementData.size();
	}
}
