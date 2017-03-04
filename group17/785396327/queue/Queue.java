package queue;

import java.util.NoSuchElementException;

/**
 * Created by william on 2017/2/25.
 */
public class Queue<T> extends LinkedList<T> {

    public boolean add(T ele) {
        return add(ele);
    }

    public T element() {
        if (size() == 0)
            throw new NoSuchElementException("队列中没有元素！");
        return get(0);
    }

    public boolean offer(T ele) {
        return add(ele);
    }

    public T peek() {
        if (size() == 0)
            return null;
        return get(0);
    }

    public T poll() {
        if (size() == 0)
            return null;
        return remove(0);
    }

    public T remove() {
        if (size() == 0)
            throw new NoSuchElementException("队列中没有元素！");
        return remove(0);
    }
}
