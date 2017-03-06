public class Stack {
	private LinkedList elementData = new LinkedList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		return	elementData.remove(elementData.size()-1);

	}
	
	public Object peek(){
		return	elementData.get(elementData.size()-1);

	}
	public boolean isEmpty(){
		if (elementData.size()==0)
			return true;
		return false;
	}
	public int size(){
		return elementData.size();
	}

	public String toString(){
		return elementData.toString();
	}

	public static void main(String[] arg){
		Stack a=new Stack();
		a.push(0);
		a.push(1);
		a.push("2");
		a.push("3");
		System.out.println(a);
		System.out.println(a.peek());
		System.out.println(a.pop());
		System.out.println(a);
		System.out.println(a.size());
		System.out.println(a.isEmpty());

		System.out.println(a.pop());
		System.out.println(a.pop());
		System.out.println(a.pop());
		System.out.println(a.isEmpty());
		System.out.println(a.peek());
		System.out.println(a.pop());
		System.out.println(a.isEmpty());


	}
}
