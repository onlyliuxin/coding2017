import queue.Queue;

/**
 * Created by william on 2017/5/7.
 * 真正的数据只存在queue中，另一个只是作为数据的暂存中转，没有必要将另一个队列变成第一个队列的逆序
 */
public class StackWithTwoQueues {
    private Queue<Integer> queue = new Queue<Integer>();
    private Queue<Integer> tempQueue = new Queue<Integer>();

    public void push(int data) {
        queue.offer(data);
    }


    public int pop() {
        int size = queue.size();
        int index = 0;
        while (index++ < size - 1) {
            Integer ele = queue.poll();
            tempQueue.offer(ele);
        }
        Integer removeEle = queue.poll();
        while (!tempQueue.isEmpty()) {
            queue.offer(tempQueue.poll());
        }
        return removeEle;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
