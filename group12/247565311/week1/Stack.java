package week1;

import java.util.List;

public class Stack<E> {
    private List<E> data = new ArrayList<E>();
    private int size = 0;
    
    public Stack(){
        
    }
    
    public Stack(int arg0){
        if(arg0 < 0) arg0 = 0;
        size = arg0;
        data = new ArrayList<E>(size);
        
    }
    public boolean isEmpty(){
    	return size==0;
    }
    public boolean push(E arg0){
        size += 1;
        data.add(arg0);
        return true;
    }
    public E pop(){
    	if(this.isEmpty()) return null;
    	size -= 1;
    	E res = data.get(size);
    	data.remove(size);
    	return res;
    }
    public E peek(){
    	if(this.isEmpty()) return null;
    	E res = data.get(size-1);
    	return res;
    }
}
