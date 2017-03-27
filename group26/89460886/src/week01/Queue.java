package list;

/**
 * @author jiaxun
 */
public class Queue {

    private SinglyLinkedList linkedList = new SinglyLinkedList();

    public void enQueue(Object object) {
        linkedList.addLast(object);
    }

    public Object deQueue() {
        return linkedList.removeFirst();
    }

    public boolean isEmpty() {
        return linkedList.size() == 0;
    }

    public int size() {
        return linkedList.size();
    }

    @Override
    public String toString() {
        if (size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0, len = size(); i < len; i++) {
                stringBuilder.append("[").append(linkedList.get(i)).append("]");
            }
            return stringBuilder.toString();
        } else {
            return super.toString();
        }
    }
}
