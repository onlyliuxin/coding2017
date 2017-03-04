package zavier.week01.basic;

public class Queue {

    private LinkedList list = new LinkedList();

    public void enQueue(Object o) {
        list.add(o);
    }

    public Object deQueue() {
        if (list.size() == 0) {
            return null;
        }
        return list.removeFirst();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }
}
