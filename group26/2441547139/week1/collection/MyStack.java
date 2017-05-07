package week1.collection;

/**
 * Created by zndbl on 2017/3/12.
 */
public class MyStack {

    private Object[] data;
    private int top;

    public MyStack() {
        data = new Object[100];
        top = -1;
    }

    public void put(Object t) {
        data[data.length] = t;
        top++;
    }

    public Object pop() {
        if(top < 0) {
            return null;
        }
        Object object = data[top];
        top--;
        return object;
    }
}
