package data2_19;

import java.util.EmptyStackException;

public class Stack {
	private ArrayList elementData;
	private int elementCount;
	
	public Stack() {
		this.elementData = new ArrayList();
		this.elementCount = 0;
	}
	public void push(Object o){
		elementData.add(o);
		elementCount++;
	}
	
	public Object pop(){
		Object object = elementData.remove(elementCount-1);
		elementCount--;
		return object;
	}
	
	public Object peek(){
		if(isEmpty()){
			 throw new EmptyStackException();
		}
		return elementData.get(elementCount-1);
	}
	public boolean isEmpty(){
		return elementCount==0;
	}
	public int size(){
		return elementCount;
	}
	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		System.out.println(stack.pop());
		System.out.println(stack.peek());
		
	}
}