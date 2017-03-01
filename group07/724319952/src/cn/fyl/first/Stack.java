package cn.fyl.first;

public class Stack {

	private ArrayList elementData = new ArrayList();
	int last;

	public void push(Object o) {
		elementData.add(o);
	}

	public Object pop() {
		return elementData.remove(last-1);
	}

	public Object peek() {
		last = elementData.size()-1;
		return elementData.get(last);
	}

	public boolean isEmpty() {
		if(elementData.size() > 0)
			return false;
		else
			return true;
	}

	public int size() {
		return elementData.size();
	}

	public static void main(String[] args) {
		Stack s = new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		System.out.println(s.peek());
	}
}
