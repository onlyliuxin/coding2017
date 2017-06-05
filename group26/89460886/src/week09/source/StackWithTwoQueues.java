package stack;

import org.junit.Assert;
import queue.Queue;

/**
 * @author jiaxun
 */
public class StackWithTwoQueues {

    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public StackWithTwoQueues() {
        queue1 = new Queue<>();
        queue2 = new Queue<>();
    }

    public void push(int data) {
        if (queue1.isEmpty() && queue2.isEmpty()) {
            queue1.enQueue(data);
        } else if (queue2.isEmpty()) {
            queue1.enQueue(data);
        } else if (queue1.isEmpty()) {
            queue2.enQueue(data);
        } else {
            Assert.assertTrue("should not go here", false);
        }
    }

    public int pop() {
        if (!queue1.isEmpty() && queue2.isEmpty()) {
            while (queue1.size() > 1) {
                queue2.enQueue(queue1.deQueue());
            }
            return queue1.deQueue();
        }
        if (!queue2.isEmpty() && queue1.isEmpty()) {
            while (queue2.size() > 1) {
                queue1.enQueue(queue2.deQueue());
            }
            return queue2.deQueue();
        }
        Assert.assertTrue("should not go here", false);
        return -1;
    }


}
