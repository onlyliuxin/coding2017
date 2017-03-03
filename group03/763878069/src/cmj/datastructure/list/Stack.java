package cmj.datastructure.list;

public class Stack {
	private ArrayList elementData = new ArrayList();

	private int size;

	public void push(Object o) {
		elementData.add(o);
		size++;
	}

	public Object pop() {
		Object pop = elementData.get(elementData.size());
		elementData.remove(size - 1);
		size--;
		return pop;
	}

	public Object peek() {
		if (size == 0) {
			throw new RuntimeException("栈为空");
		}
		return elementData.get(size - 1);
	}

	public boolean isEmpty() {
		return size <= 0 ? true : false;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		return "Stack [elementData=" + elementData + ", size=" + size + "]";
	}

	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.push("a");
		stack.push("b");
		stack.push("c");
		stack.push("d");
		stack.push("e");
		System.out.println(stack);

		stack.pop();
		System.out.println(stack);
		stack.pop();
		System.out.println(stack);
		stack.pop();
		System.out.println(stack);

	}

}