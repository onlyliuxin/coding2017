package stack;

import java.util.EmptyStackException;

/**
 * Created by william on 2017/2/25.
 */
public class Stack<T> extends ArrayList {

    public boolean empty() {
        return isEmpty();
    }

    public T peek() {
        if (size() == 0)
            throw new EmptyStackException();
        return (T) get(0);
    }

    public T pop() {
        if (size() == 0)
            throw new EmptyStackException();
        return (T) remove(0);
    }

    public void push(T ele) {
        add(0, ele);
    }
}
