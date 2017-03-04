package dataStructure;

/**
 * Created by LvZhenxing on 2017/2/22.
 */
public class Stack {

    private LinkedList list = new LinkedList();

    public void push(Object o) {
        list.addFirst(o);
    }

    public Object pop() {
        return list.removeFirst();
    }

    public Object peek() {
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.size() == 0 ? true : false;
    }

    public int size() {
        return list.size();
    }
}
