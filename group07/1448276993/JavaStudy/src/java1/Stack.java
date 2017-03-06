package java1;

public class Stack {
	private ArrayList array = new ArrayList();
	
	public void push(Object o){	
		array.add(o);
	}
	
	public Object pop(){
		if(array.size()>0){
			Object pop = array.get(array.size()-1);
			array.remove(array.size()-1);
			return pop;
		}
		return null;
	}
	
	public Object peek(){
		if(array.size()>0){
			return array.get(array.size()-1);
		}
		return null;
	}
	public boolean isEmpty(){
		if(array.size()==0){
			return true;
		}
		return false;
	}
	public int size(){
		return array.size();
	}
}
