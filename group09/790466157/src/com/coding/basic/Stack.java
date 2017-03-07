package com.coding.basic;
import java.util.Arrays;
import com.coding.basic.MyArrayList;
public class Stack {
	private MyArrayList elementData = new MyArrayList();
	private static final int CAPACITY = 10;
	private static int capacity;
	private static int top = -1;
	Object[] array;
	
	public Stack(){
		this.capacity = CAPACITY;
        array = new Object[capacity];
	}
	public void push(Object o) throws ExceptionStackFull{		
		if(size()== CAPACITY){
            throw new ExceptionStackFull("Stack is full");
        }
        array[++ top] = o;
	}
	
	public Object pop() throws ExceptionStackEmpty{
		  if(isEmpty()){
	            throw new ExceptionStackEmpty("Stack is empty");
	        }
	        return array[top --];
	}
	
	public Object peek() throws ExceptionStackEmpty{
		 if(isEmpty()){
	            throw new ExceptionStackEmpty("Stack is empty");
	        }
	        return array[top];
	}
	
	public boolean isEmpty(){
		return (top < 0);
	}
	
	public int size(){
		if (isEmpty())
			return 0;
		else
		return top + 1;
		
	}
	
	public class ExceptionStackEmpty extends Exception {

	    //Constructor
	    public ExceptionStackEmpty(){

	    }

	    //Define myself exception construct with parameters
	    public ExceptionStackEmpty(String string){
	        super(string);
	    }
	}
	
	public class ExceptionStackFull extends Exception {

	    //Constructor
	        public ExceptionStackFull(){

	        }

	        //Define myself exception construct with parameters
	        public ExceptionStackFull(String string){
	            super(string);
	        }
	}
}