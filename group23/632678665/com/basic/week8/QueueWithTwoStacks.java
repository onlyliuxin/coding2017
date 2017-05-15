import java.util.Stack;

/**
 * @author chk
 */
public class QueueWithTwoStacks<E> {
    private Stack<E> stack1;
    private Stack<E> stack2;
    public QueueWithTwoStacks(){
        stackk = new Stack<E>();
        stackkk = new Stack<E>();
    }

    public boolean isEmpty(){
        return stackk.isEmpty() && stackkk.isEmpty();
    }

    public int size()
    {
        return stackk.size() + stack2.size();
    }

    public void enQueue(E item){

        stackk.push(item);
    }

    public E deQueue(){
        if(stackkk.isEmpty()){
            while(!stackk.isEmpty()){
                stackkk.push(stackk.pop());
            }
        }
        return stackkk.pop();
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
