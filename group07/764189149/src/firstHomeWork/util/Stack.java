package firstHomeWork.util;

public class Stack<E> {
	private ArrayList<E> elementData = new ArrayList<E>();
	public void push(E e){
		elementData.add(e);
	}
	
	public Object pop(){
		return elementData.remove(elementData.size() - 1);
	}
	
	public Object peek(){
		return elementData.get(elementData.size() - 1);
	}
	public boolean isEmpty(){
		return elementData.isEmpty();
	}
	public int size(){
		return elementData.size();
	}
}
