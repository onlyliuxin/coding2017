package main;

public class Stack {
	private ArrayList list = new ArrayList();

	public void push(Object o) {
		list.add(o);
	}

	public Object pop() {
		return list.remove(list.size() - 1);
	}

	public Object peek() {
		return list.get(list.size() - 1);
	}

	public boolean isEmpty() {
		return list.size() == 0;
	}

	public int size() {
		return list.size();
	}
}
