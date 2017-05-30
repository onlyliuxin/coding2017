import java.util.Stack;

/**
 * Created by lxx on 2017/5/6.
 */
public class QueueWithTwoStacks<E> {
    private Stack<E> stack1;
    private Stack<E> stack2;
    public QueueWithTwoStacks(){
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }

    public boolean isEmpty(){
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public int size()
    {
        return stack1.size() + stack2.size();
    }

    public void enQueue(E item){

        stack1.push(item);
    }

    public E deQueue(){
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
    public static void main(String args[]){
        QueueWithTwoStacks queue = new QueueWithTwoStacks();
        queue.enQueue(1);
        queue.enQueue(5);
        queue.enQueue(4);
        queue.enQueue(0);
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
    }
}
