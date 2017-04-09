package lesson01;

import java.util.EmptyStackException;

public class Stack<E>{
	
	private ArrayList<E> stack = new ArrayList<E>();
	
	boolean isEmpty(){
		return size() == 0;
	}
	
	E peek(){
		checkEmpty();
		return stack.get(size() - 1);
	}
	
	E pop(){
		checkEmpty();
		return stack.remove(size() - 1);
	}

	private void checkEmpty() {
		if(isEmpty()){
			throw new EmptyStackException();
		}
	}
	
	void push(E e){
		stack.add(e);
	}
	
	public int size(){
		return stack.size();
	}

}
