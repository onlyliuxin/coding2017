package datastructure.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		Object peek = peek();
		elementData.remove(elementData.size() - 1);
		return peek;
	}
	
	public Object peek(){
		return elementData.get(elementData.size() - 1);
	}

	public boolean isEmpty(){
		return size() == 0;
	}

	public int size(){
		return elementData.size();
	}

	public static void main(String[] args) {
		Stack stack = new Stack();
		for (int i = 0; i < 6; ++i) {
			stack.push(i);
		}
		for (int i = 0; i < 4; ++i) {
			System.out.print(stack.pop() + " ");
		}
		for (int i = 6; i < 21; ++i) {
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}
}
