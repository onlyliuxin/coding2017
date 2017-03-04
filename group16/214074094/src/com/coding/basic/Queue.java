package coding.basic;

/**
 * @Author shane
 * @Time 2017/2/26 17:19
 * @Email stevenchenguang@gmail.com
 * @Desc Own Queue
 */
public class Queue {

    private LinkedList elementData = new LinkedList();

    public void enQueue(Object o) {
        elementData.add(o);
    }

    public Object deQueue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return elementData.remove(0);
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
