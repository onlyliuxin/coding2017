package main.java;

/**
 * Created by yrs on 2017/2/25.
 */
public class Stack {
    private ArrayList elementData = new ArrayList();

    public void push(Object o) {
        elementData.add(o);
    }

    public Object pop() {
        Object o = elementData.remove(elementData.size()-1);
        return o;
    }

    public Object peek() {
        Object o = elementData.get(elementData.size() - 1);
        return o;
    }

    public boolean isEmpty() {
        return elementData.size() == 0;
    }

    public int size() {
        return elementData.size();
    }

    public static void main(String [] args) {
        Stack stack = new Stack();
        stack.push(1);
        System.out.println(stack.size() + " " + stack.peek() + " " + stack.pop() + " " + stack.isEmpty());

    }

}
                