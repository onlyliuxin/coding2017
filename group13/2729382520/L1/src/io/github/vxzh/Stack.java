package io.github.vxzh;

/**
 * Created by vxzh on 24/02/2017.
 */
public class Stack {

    private ArrayList elementData;

    public Stack() {
        this.elementData = new ArrayList();
    }

    public int size() {
        return elementData.size();
    }

    public boolean isEmpty() {
        return elementData.size() == 0;
    }

    public void push(Object o) {
        elementData.add(o);
    }

    public Object pop() {
        Object obj;
        int len = elementData.size();
        obj = peek();
        elementData.remove(len - 1);
        return obj;
    }

    public Object peek() {
        int len = elementData.size();
        if (len == 0)
            throw new RuntimeException("EmptyStackException");
        return elementData.get(len - 1);
    }

}
