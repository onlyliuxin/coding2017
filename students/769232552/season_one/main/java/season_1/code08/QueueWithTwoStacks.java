package code08;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列
 * @param <E>
 */
public class QueueWithTwoStacks<E> {
	private Stack<E> stack1;    
    private Stack<E> stack2;    

    
    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }

    

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    
    public int size() {
        return stack1.size() + stack2.size();
    }


    public void enQueue(E item) {
        stack1.push(item);
    }

    public E deQueue() {
        if(!stack2.isEmpty()){
            return stack2.pop();
        }
        if(!stack1.isEmpty()){
            while (!stack1.isEmpty()){
                E e = stack1.pop();
                stack2.push(e);
            }
            return stack2.pop();
        }
        return null;
    }


    public static void main(String[] args) {
        QueueWithTwoStacks q = new QueueWithTwoStacks();
        q.enQueue("a");
        q.enQueue("b");
        q.enQueue("c");
        while (!q.isEmpty()){
            System.out.println(q.deQueue());
        }
        q.enQueue("d");
        q.enQueue("e");
        while (!q.isEmpty()){
            System.out.println(q.deQueue());
        }
    }

 }

