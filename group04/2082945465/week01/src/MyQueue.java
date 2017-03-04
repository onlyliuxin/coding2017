package src;

/**
 * Created by Yang on 2/25/2017.
 */
public class MyQueue {
    private MyLinkedList queue = new MyLinkedList();

    public void enQuee(Object obj){
        queue.addLast(obj);
    }

    public Object deQuee(Object obj){
        return queue.removeFirst();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
