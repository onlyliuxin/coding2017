package com.donaldy.basic.stack;


import com.donaldy.basic.queue.Queue;

public class StackWithTwoQueues {
	
    Queue<Integer> queue = new Queue<>();
    Queue<Integer> opQueue = new Queue<>();
    int size = 0;

    public void push(int data) {       

        this.queue.enQueue(data);
        this.size ++;

    }

    public int pop() {

        if (this.size <= 0 ) {
            throw new IndexOutOfBoundsException("size : " + this.size);
        }

        int cnt = this.size;

        int element;

        while (!this.queue.isEmpty()) {

            if (cnt == 1) {
                break;
            }

            this.opQueue.enQueue(this.queue.deQueue());

            cnt --;
        }

        int oldData = this.queue.deQueue();

        while (!this.opQueue.isEmpty()) {
            this.queue.enQueue(this.opQueue.deQueue());
        }

        this.size --;

       return oldData;
    }

    
}
