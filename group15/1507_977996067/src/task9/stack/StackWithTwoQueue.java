package task9.stack;

import java.util.ArrayDeque;
import java.util.Queue;

public class StackWithTwoQueue<T> {

    private Queue<T> queue1 = new ArrayDeque<>();
    private Queue<T> queue2 = new ArrayDeque<>();

    public void push(T data) {
        queue1.add(data);
    }

    public T pop() {
        while (!queue1.isEmpty()) {
            queue2.add(queue1.poll());
        }
        T result = queue2.poll();
        while (!queue2.isEmpty()) {
            queue1.add(queue2.poll());
        }
        return result;
    }
}
