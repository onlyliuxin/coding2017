package stack;

import java.util.NoSuchElementException;

import list.ArrayList;
import list.List;

public class Stack {
	
	List list ;
	public Stack(){
		list = new ArrayList();
	}
	
	public void push(Object o) {
		list.add(o);
	}
	public Object poll(){		
		Object remove = list.remove(list.size()-1);
		if(remove==null)
			throw new NoSuchElementException();
		return remove;
	}
	
	public Object peak(){
		if(list.size()==0)
			throw new NoSuchElementException();
		return list.get(list.size()-1);
	}

}
