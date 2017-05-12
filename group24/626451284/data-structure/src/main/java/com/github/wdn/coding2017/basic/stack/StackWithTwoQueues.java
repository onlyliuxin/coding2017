package com.github.wdn.coding2017.basic.stack;


import com.github.wdn.coding2017.basic.Queue;

/**
 * Created by Administrator on 2017/5/6 0006.
 */
public class StackWithTwoQueues<E> {
    Queue queue1 = new Queue();
    Queue queue2 = new Queue();

    public static void main(String[] args) {
        StackWithTwoQueues<Integer> stack = new StackWithTwoQueues<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(stack.pop());
        }
        System.out.println("");
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
    public void push(E data){
        if(queue1.isEmpty()){
            queue2.enQueue(data);
        }else{
            queue1.enQueue(data);
        }
    }
    public E pop(){
        if(isEmpty()){
            throw new RuntimeException("stack is empty");
        }
        if(queue1.isEmpty()){
            int len = queue2.size();
            for (int i = 0; i < len-1; i++) {
                queue1.enQueue(queue2.deQueue());
            }
            return (E)queue2.deQueue();
        }else{
            int len = queue1.size();
            for (int i = 0; i < len-1; i++) {
                queue2.enQueue(queue1.deQueue());
            }
            return (E)queue1.deQueue();
        }
    }
    public boolean isEmpty(){
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
