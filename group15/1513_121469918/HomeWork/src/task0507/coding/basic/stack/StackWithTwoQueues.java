package task0507.coding.basic.stack;

import java.util.ArrayDeque;
import java.util.Queue;

public class StackWithTwoQueues {
	private Queue<Integer> q1 = new ArrayDeque<>();
	private Queue<Integer> q2 = new ArrayDeque<>();

	public void push(int data) {
		if (q1.isEmpty() && q2.isEmpty()) {
			q1.add(data);
		}
		if (!q1.isEmpty()) {
			q1.add(data);
		}
		if (!q2.isEmpty()) {
			q2.add(data);
		}
	}

	public int pop() {
		if(q1.isEmpty()&&q2.isEmpty()){
			throw new RuntimeException("stack is empty");
		}
		if (q1.isEmpty()) {
            while (q2.size()>1) {
                q1.add(q2.poll());
            }
            return q2.poll();
        } 
        
        if (q2.isEmpty()) {
            while (q1.size()>1) {
                q2.add(q1.poll());
            }
            return q1.poll();
        }
        throw new RuntimeException("no queue is empty");
		
	}

}