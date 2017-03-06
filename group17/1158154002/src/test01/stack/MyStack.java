package test01.stack;

import java.util.Iterator;

import com.sun.org.apache.bcel.internal.generic.POP;
import com.sun.org.apache.bcel.internal.generic.PUSH;

public class MyStack<T> implements Iterable<T> {
	private static final int DEFAULT_SIZE=10;
	private int size;	
	private T[] item;
	private int top;
	
	public MyStack() {
		clear();
	}

	public void clear(){
		size=0;
		top=-1;
		ensureCapacity(DEFAULT_SIZE);
	}	
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	
	public void trumToSize(){
		ensureCapacity(size());
	}
	
	public void ensureCapacity(int capacity){
		if (capacity<size()) {
			return;
		}
		T[] old=item;
		item=(T[]) new Object[capacity];
		if (old!=null) {
			System.arraycopy(old, 0, item, 0, old.length);		
		}

//        for (int i = 0; i <= top; i++) {  
//            item[i] = old[i];  
//        } 
		size=capacity;
	}
	
	public T top(){
		if (size()==0) {
			throw new NullPointerException();
		}
		return item[top];
	}
	
	public T pop(){
		if (size()==0) {
			throw new NullPointerException();
		}
		return item[top--];
	}
	
	public void push(T x){
		if (top+1==size()) {
			ensureCapacity(size()*2+1);
		}
		item[++top]=x;
	}
	
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new StackIterator();
	}
	
	private class StackIterator implements Iterator<T>{
		private int current=0;
		
		@Override
		public boolean hasNext() {
			return current<=top;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NullPointerException();
			}
			return item[current++];
		}
		
	}

	public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<Integer>();  
        stack.push(1);  
        stack.push(2);  
        stack.push(3);  
        stack.pop();  
        stack.push(4);  
        stack.push(5);  
        Iterator<Integer> iterator = stack.iterator();  
        while (iterator.hasNext()) {  
            System.out.print(iterator.next() + " ");  
        }
	}
}
