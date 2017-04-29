package stack;

import java.util.ArrayList;

/**
 * Created by gongxun on 2017/4/12.
 */
public class MyStack<T> {
    private ArrayList<T> elementData = new ArrayList();

    public void push(T o) {
        elementData.add(0, o);
    }

    public T pop() {
        return elementData.remove(0);
    }

    public T peek() {
        return elementData.get(0);
    }

    public boolean isEmpty() {
        return elementData.isEmpty();
    }

    public int size() {
        return elementData.size();
    }

    @Override
    public String toString() {
        return elementData.toString();
    }
}
