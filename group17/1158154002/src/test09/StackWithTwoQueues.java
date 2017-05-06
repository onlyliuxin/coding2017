package test09;

import java.util.ArrayDeque;
import java.util.Queue;

public class StackWithTwoQueues {
	Queue<Integer> queue1 = new ArrayDeque<>();
	Queue<Integer> queue2 = new ArrayDeque<>();

	public void push(int data) {
		if (queue1.isEmpty() && queue2.isEmpty()) {
			queue1.add(data);
			return;
		}

		if (queue1.isEmpty()) {
			queue2.add(data);
			return;
		}

		if (queue2.isEmpty()) {
			queue1.add(data);
			return;
		}
	}

	public int pop() {
		if (queue1.isEmpty() && queue2.isEmpty()) {
			try {
				throw new Exception("stack is empty!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (queue1.isEmpty()) {
			while (queue2.size() > 1) {
				queue1.add(queue2.poll());
			}
			return queue2.poll();
		}

		if (queue2.isEmpty()) {
			while (queue1.size() > 1) {
				queue2.add(queue1.poll());
			}
			return queue1.poll();
		}
		return (Integer) null;
	}
	
    public static void main(String[] args) {
    	StackWithTwoQueues stack = new StackWithTwoQueues();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(5);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
