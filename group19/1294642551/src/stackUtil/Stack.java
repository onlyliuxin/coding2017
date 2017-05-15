package stackUtil;

import java.util.ArrayList;


public class Stack<E> {
	private ArrayList<E> elementData;
	private int size;
	
	public Stack(){
		elementData = new ArrayList<E>();
		this.size = 0;
	}
	
	
	
	public void push(E e){	
		elementData.add(e);
		size++;
	}
	
	public E pop(){
		E o = elementData.remove(size - 1);
		size--;
		return o;
	}
	
	public E peek(){
		return elementData.get(size - 1);
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("[");
		for(int i = 0; i < size; i++){
			sb.append(elementData.get(i));
			if(i < size - 1){
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
