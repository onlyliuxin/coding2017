package task1;

/**
 * Stack 实现
 */
public class MyStack<T> {
    private MyLinkedList<T> elementData = new MyLinkedList<T>();

    public void push(T o) {
        elementData.addFirst(o);
    }

    public T pop() {
        return elementData.removeFirst();
    }

    public T peek() {
        return elementData.get(0);
    }

    public boolean isEmpty() {
        return elementData.size() == 0;
    }

    public int size() {
        return elementData.size();
    }

}
