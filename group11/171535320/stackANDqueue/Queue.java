package stackANDqueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dengdechao on 2017/2/22.
 */
public class Queue {

    private LinkedList queue = new LinkedList();

    public void enQueue(Object o){
        queue.add(o);
    }

    public Object deQueue(){
        if(queue.isEmpty()) {
            return null;
        }
        return queue.removeFirst();
    }

    public boolean isEmpty(){
        if(queue.isEmpty()) {
            return true;
        }
        return false;
    }

    public int size(){
        return queue.size();
    }
}
