package TestCollection;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		return elementData.get(elementData.size()-1) ;
	}
	
	public Object peek(){
		elementData.remove(elementData.size()-1);
		return  elementData.get(elementData.size()-1);
	}
	public boolean isEmpty(){
		if(elementData.size()!=0){
		return false;}
		return true;
	}
	public int size(){
		return elementData.size();
	}
	public static void main(String[] args) {
		Stack s=new Stack();
		s.push("111");
		s.push("211");
		s.push("311");
		System.out.println(s.size());//3
		System.out.println(s.pop());
		System.out.println(s.size());
		System.out.println(s.peek());
		System.out.println(s.size());
	}}
