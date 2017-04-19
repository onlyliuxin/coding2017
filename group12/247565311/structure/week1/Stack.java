package structure.week1;

import structure.week1.List;

public class Stack<E> {
    private ArrayList<E> data = new ArrayList<E>();
    public boolean isEmpty(){
    	return data.isEmpty();
    }
    public int size(){
    	return data.size();
    }
    public boolean push(E arg0){
        data.add(arg0);
        return true;
    }
    public E pop(){
    	if(this.isEmpty()) return null;
    	E res = data.get(data.size()-1);
    	data.remove(data.size()-1);
    	return res;
    }
    public E peek(){
    	if(this.isEmpty()) return null;
    	E res = data.get(data.size()-1);
    	return res;
    }
}
