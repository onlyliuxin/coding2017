package basic.dataStructure.stack;

import basic.dataStructure.queue.Queue;

/**
 * 用两个队列实现一个栈
 */
public class StackWithTwoQueues {

    Queue queue1 = null;
    Queue queue2 = null;

    private int data = -1;

    public StackWithTwoQueues(){
        queue1 = new Queue();
        queue2 = new Queue();
    }
    public void push(int data) {       
        queue1.enQueue(data);
    }

    public int pop() {
        move();
        return this.data;
    }


    /**
     * queue1是数据保存队列， queue2是缓存队列
     * 每次取之前将queue1的n-1个数据移动至queue2
     * 取出queue的最后一个数据即可
     */
    private void move(){
        if(queue2.isNotEmpty()){
            throw new RuntimeException("queue2 is not empty, operation aborted");
        }

        int size = queue1.size();
        for(int i = 0; i < size - 1; i++){
            queue2.enQueue(queue1.deQueue());
        }

        data = (Integer)queue1.deQueue();

        for(int i = 0; i < size - 1; i++){
            queue1.enQueue(queue2.deQueue());
        }
    }
}
